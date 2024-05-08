package com.example.hotelfullstack.mapper;

import com.example.hotelfullstack.dtos.CarDTO;
import com.example.hotelfullstack.dtos.EmployeeDTO;
import com.example.hotelfullstack.models.Car;
import com.example.hotelfullstack.models.Employee;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    @Mapping(source = "employee.position.id", target = "positionID")
    EmployeeDTO toDto(Employee employee);

    @IterableMapping(elementTargetType = EmployeeDTO.class)
    List<EmployeeDTO> toListEntity(List<Employee> employees);
}
