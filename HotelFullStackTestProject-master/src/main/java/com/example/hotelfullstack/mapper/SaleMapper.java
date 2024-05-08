package com.example.hotelfullstack.mapper;

import com.example.hotelfullstack.dtos.OrderDTO;
import com.example.hotelfullstack.dtos.SaleDTO;
import com.example.hotelfullstack.models.Order;
import com.example.hotelfullstack.models.Sale;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SaleMapper {
    @Mapping(source = "sale.order.id", target = "orderId")
    SaleDTO toDto(Sale sale);

    @IterableMapping(elementTargetType = SaleDTO.class)
    List<SaleDTO> toListEntity(List<Sale> sales);
}
