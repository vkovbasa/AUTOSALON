package com.example.hotelfullstack.mapper;

import com.example.hotelfullstack.dtos.ClientDTO;
import com.example.hotelfullstack.dtos.EmployeeDTO;
import com.example.hotelfullstack.models.Client;
import com.example.hotelfullstack.models.Employee;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDTO toDto(Client client);
    @IterableMapping(elementTargetType = ClientDTO.class)
    List<ClientDTO> toListEntity(List<Client> clients);

}
