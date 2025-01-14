package org.example.core.repository;

import org.example.core.entity.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {
    Optional<Contact> findContactByEmail(String email);
}
