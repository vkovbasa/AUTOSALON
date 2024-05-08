package com.example.hotelfullstack.controllers;

import com.example.hotelfullstack.dtos.EmployeeDTO;
import com.example.hotelfullstack.mapper.EmployeeMapper;
import com.example.hotelfullstack.models.CarCategory;
import com.example.hotelfullstack.models.Employee;
import com.example.hotelfullstack.services.CarCategoryService;
import com.example.hotelfullstack.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeMapper employeeMapper;

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee() {
        List<Employee> employees = employeeService.getAllEmployee();
        return new ResponseEntity<>(employeeMapper.toListEntity(employees), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id") Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            return new ResponseEntity<>(employeeMapper.toDto(employee), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/with-position")
    public ResponseEntity<List<Employee>> getEmployeesWithPosition() {
        List<Employee> employees = employeeService.getEmployeesWithPosition();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Employee>> getEmployeesByFullName(@RequestParam("name1") String name1,
                                                                 @RequestParam("name2") String name2) {
        List<Employee> employees = employeeService.getEmployeesByFullName(name1, name2);
        return ResponseEntity.ok(employees);
    }

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return new ResponseEntity<>(employeeService.saveEmployee(employeeDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> editEmployee(@PathVariable("id") Long id, @RequestBody EmployeeDTO employee) {
        return new ResponseEntity<>(employeeService.editEmployee(id, employee), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
