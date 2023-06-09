package com.example.mindspace.controller;

import com.example.mindspace.api.ClientResponse;
import com.example.mindspace.api.ClientTherapistRelationRequest;
import com.example.mindspace.api.ReservationResponse;
import com.example.mindspace.api.UserRequest;
import com.example.mindspace.service.impl.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientsController {

    private final ClientServiceImpl clientService;

    @Autowired
    public ClientsController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    /**
     * Get client details
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> getDetails(@PathVariable Integer id) {
        return new ResponseEntity<>(clientService.getClientDetails(id), HttpStatus.OK);
    }

    /**
     * Set ner therapist
     */
    @PutMapping("/{id}/therapist")
    public ResponseEntity<Void> setTherapist(
            @PathVariable Integer id,
            @RequestBody ClientTherapistRelationRequest request
    ) {
        clientService.setNewTherapist(id, request);
        return ResponseEntity.ok().build();
    }

    /**
     * Cancel therapist
     */
    @DeleteMapping("/{id}/therapist")
    public ResponseEntity<Void> cancelTherapist(@PathVariable Integer id) {
        clientService.cancelTherapist(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Find all reservations.
     */
    @GetMapping("/{id}/reservations")
    public ResponseEntity<List<ReservationResponse>> getAllReservations(@PathVariable Integer id) {
        return new ResponseEntity<>(clientService.findAllReservations(id), HttpStatus.OK);
    }

    /**
     * Updates client
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateClient(@PathVariable Integer id, @RequestBody UserRequest userRequest) {
        clientService.updateClient(id, userRequest);
        return ResponseEntity.noContent().build();
    }
}
