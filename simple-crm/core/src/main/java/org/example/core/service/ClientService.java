package org.example.core.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.core.dto.ClientDTO;
import org.example.core.entity.Client;
import org.example.core.mapper.ClientMapper;
import org.example.core.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService {
    private final ClientRepository repository;
    private final ClientMapper mapper;

    public ClientDTO save(ClientDTO client) {
        log.info("Saving client");
        log.debug("Client {}", client);

        Client entity = mapper.dtoToEntity(client);
        Client savedClient = repository.save(entity);
        return mapper.entityToDTO(savedClient);
    }

    public ClientDTO updateClient(ClientDTO clientDTO) {
        log.info("Updating client");
        log.debug("Client {}", clientDTO);

        Client client = repository.findById(clientDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Client with ID " + clientDTO.getId() + " not found"));

        client.setCompanyName(clientDTO.getCompanyName());
        client.setBranch(clientDTO.getBranch());
        client.setAddress(clientDTO.getAddress());
        Client updatedClient = repository.save(client);

        return mapper.entityToDTO(updatedClient);
    }

    public void deleteClient(Long id) {
        log.info("Deleting client");
        log.debug("Client id {}", id);

        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Client with ID " + id + " not found");
        }
        repository.deleteById(id);
    }
}
