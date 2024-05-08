package com.example.hotelfullstack.services;

import com.example.hotelfullstack.dtos.EmployeeDTO;
import com.example.hotelfullstack.exceptions.ResourceNotFoundException;
import com.example.hotelfullstack.models.CarCategory;
import com.example.hotelfullstack.models.Employee;
import com.example.hotelfullstack.models.Position;
import com.example.hotelfullstack.repositories.CarCategoryRepository;
import com.example.hotelfullstack.repositories.EmployeeRepository;
import com.example.hotelfullstack.repositories.PositionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PositionRepository positionRepository;
    private ModelMapper modelMapper = new ModelMapper();

    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    public List<Employee> getEmployeesByFullName(String name1, String name2) {
        return employeeRepository.findByFullNameContainingOrFullNameContaining(name1, name2);
    }

    public List<Employee> getEmployeesWithPosition() {
        return employeeRepository.findEmployeesWithPosition();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee saveEmployee(EmployeeDTO employeeDTO) {
        Position position = positionRepository.findById(employeeDTO.getPositionID()).orElseThrow(
                () -> new ResourceNotFoundException("Employee not found id : " + employeeDTO.getPositionID())
        );

        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        employee.setPosition(position);
        return employeeRepository.save(employee);
    }
    public Employee editEmployee(Long id, EmployeeDTO employee) {
        Employee updateEmployee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee not found id : " + id)
        );

        Position position = positionRepository.findById(employee.getPositionID()).orElseThrow(
                () -> new ResourceNotFoundException("Position not found id : " + employee.getPositionID())
        );

        updateEmployee.setPhone(employee.getPhone());
        updateEmployee.setFullName(employee.getFullName());
        updateEmployee.setDateOfBirth(employee.getDateOfBirth());
        updateEmployee.setPosition(position);

        return employeeRepository.save(updateEmployee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
