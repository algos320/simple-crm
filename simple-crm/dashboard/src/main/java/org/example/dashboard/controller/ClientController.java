package org.example.dashboard.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.example.core.dto.ClientDTO;
import org.example.core.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @PostMapping
    @Operation(summary = "Create a client", description = "Creates a new client based on the provided data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client successfully created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClientDTO.class)))
    })
    public ClientDTO create(@RequestBody ClientDTO clientDTO) {
        return clientService.save(clientDTO);
    }

    @Operation(summary = "Update a client", description = "Updates the data of an existing client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client successfully updated",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClientDTO.class)))
    })
    @PutMapping
    public ClientDTO update(@RequestBody ClientDTO clientDTO) {
        return clientService.updateClient(clientDTO);
    }

    @Operation(summary = "Delete a client", description = "Deletes a client by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client successfully deleted")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok().build();
    }
}
