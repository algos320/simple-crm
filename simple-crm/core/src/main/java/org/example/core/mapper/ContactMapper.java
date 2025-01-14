package org.example.core.mapper;

import org.example.core.dto.ContactDTO;
import org.example.core.entity.Client;
import org.example.core.entity.Contact;
import org.example.core.repository.ClientRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = ClientMapper.class)
public interface ContactMapper {
    @Mapping(source = "client.id", target = "clientId")
    ContactDTO entityToDto(Contact contact);
    @Mapping(source = "clientId", target = "client", qualifiedByName = "mapClient")
    Contact dtoToEntity(ContactDTO contactDTO);

    @Named("mapClient")
    default Client mapClient(Long clientId, @Context ClientRepository clientRepository) {
        if (clientId == null) {
            return null;
        }
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }
}
