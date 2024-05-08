package com.example.hotelfullstack.mapper;

import com.example.hotelfullstack.dtos.PositionDTO;
import com.example.hotelfullstack.models.Position;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface PositionMapper {
    PositionDTO toDto(Position entity);
    Position toEntity(PositionDTO dto);
}
