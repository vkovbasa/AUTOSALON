package com.example.hotelfullstack.mapper;

import com.example.hotelfullstack.dtos.CarDTO;
import com.example.hotelfullstack.models.Car;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {
    @Mapping(source = "car.category.id", target = "categoryId")
    CarDTO toDto(Car car);

    @IterableMapping(elementTargetType = CarDTO.class)
    List<CarDTO> toListEntity(List<Car> cars);
}
