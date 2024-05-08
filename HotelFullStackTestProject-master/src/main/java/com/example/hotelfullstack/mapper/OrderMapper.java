package com.example.hotelfullstack.mapper;

import com.example.hotelfullstack.dtos.CarDTO;
import com.example.hotelfullstack.dtos.OrderDTO;
import com.example.hotelfullstack.models.Car;
import com.example.hotelfullstack.models.Order;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "order.client.id", target = "clientId")
    @Mapping(source = "order.car.id", target = "carId")
    @Mapping(source = "order.employee.id", target = "employeeId")
    OrderDTO toDto(Order order);

    @IterableMapping(elementTargetType = OrderDTO.class)
    List<OrderDTO> toListEntity(List<Order> orders);
}
