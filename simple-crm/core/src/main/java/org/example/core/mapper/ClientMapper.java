package org.example.core.mapper;

import org.example.core.dto.ClientDTO;
import org.example.core.entity.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDTO entityToDTO(Client client);
    Client dtoToEntity(ClientDTO clientDTO);
}
