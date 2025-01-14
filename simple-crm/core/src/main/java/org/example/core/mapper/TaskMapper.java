package org.example.core.mapper;

import org.example.core.dto.TaskDTO;
import org.example.core.entity.Client;
import org.example.core.entity.Contact;
import org.example.core.entity.Task;
import org.example.core.repository.ClientRepository;
import org.example.core.repository.ContactRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = ContactMapper.class)
public interface TaskMapper {
    @Mapping(source = "contactId", target = "contact", qualifiedByName = "mapClient")
    Task dtoToEntity(TaskDTO taskDTO);
    @Mapping(source = "contact.id", target = "contact")
    TaskDTO entityToDTO(Task entity);

    @Named("mapContact")
    default Contact mapcontact(Long contactId, @Context ContactRepository contactRepository) {
        if (contactId == null) {
            return null;
        }
        return contactRepository.findById(contactId)
                .orElseThrow(() -> new RuntimeException("Contact not found"));
    }
}
