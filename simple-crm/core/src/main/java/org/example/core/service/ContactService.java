package org.example.core.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.core.dto.ContactDTO;
import org.example.core.entity.Contact;
import org.example.core.mapper.ContactMapper;
import org.example.core.repository.ContactRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactService {
    private final ContactRepository repository;
    private final ContactMapper contactMapper;

    public ContactDTO save(ContactDTO contactDTO) {
        log.info("Saving contact");
        log.debug("Client {}", contactDTO);

        Contact contact = contactMapper.dtoToEntity(contactDTO);
        contact = repository.save(contact);
        return contactMapper.entityToDto(contact);
    }

    @Transactional
    public ContactDTO updateContact(ContactDTO contactDTO) {
        log.info("Updating contact");
        log.debug("Contact {}", contactDTO);

        Optional<Contact> optionalContact = repository.findById(contactDTO.getId());
        if (optionalContact.isPresent()) {
            Contact contact = optionalContact.get();
            contact.setFirstName(contactDTO.getFirstName());
            contact.setLastName(contactDTO.getLastName());
            contact.setEmail(contactDTO.getEmail());
            contact.setPhone(contactDTO.getPhone());

            contact = repository.save(contact);

            return contactMapper.entityToDto(contact);
        } else {
            throw new EntityNotFoundException("Contact not found with id: " + contactDTO.getId());
        }
    }

    public void deleteContact(Long id) {
        log.info("Deleting contact");
        log.debug("Contact id {}", id);

        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Contact not found with id: " + id);
        }

        repository.deleteById(id);
    }

    public Contact findContactByEmail(String email) {
        return repository
                .findContactByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Contact not found with email " + email));
    }
}
