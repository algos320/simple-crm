package org.example.dashboard.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.example.core.dto.ContactDTO;
import org.example.core.service.ContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;

    @Operation(summary = "Create a contact", description = "Creates a new contact based on the provided data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contact successfully created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ContactDTO.class)))
    })
    @PostMapping
    public ContactDTO create(@RequestBody ContactDTO contactDTO) {
        return contactService.save(contactDTO);
    }

    @Operation(summary = "Update a contact", description = "Updates the data of an existing contact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contact successfully updated",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ContactDTO.class)))
    })
    @PutMapping
    public ContactDTO update(@RequestBody ContactDTO contactDTO) {
        return contactService.updateContact(contactDTO);
    }

    @Operation(summary = "Delete a contact", description = "Deletes a contact by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contact successfully deleted")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Parameter(description = "Contact ID") @PathVariable Long id) {
        contactService.deleteContact(id);
        return ResponseEntity.ok().build();
    }
}
