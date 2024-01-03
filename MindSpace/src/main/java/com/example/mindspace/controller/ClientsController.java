package com.example.mindspace.controller;

import com.example.mindspace.api.ClientResponse;
import com.example.mindspace.api.ClientTherapistRelationRequest;
import com.example.mindspace.api.QuestionnaireRequest;
import com.example.mindspace.api.ReservationResponse;
import com.example.mindspace.api.TherapistResponse;
import com.example.mindspace.api.UserRequest;
import com.example.mindspace.security.CurrentUser;
import com.example.mindspace.security.UserPrincipal;
import com.example.mindspace.service.impl.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
     * Gets current client
     */
    @GetMapping("/profile")
    public ResponseEntity<ClientResponse> getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return new ResponseEntity<>(clientService.getClientDetails(userPrincipal.getId()), HttpStatus.OK);
    }

    /**
     * Gets current client
     */
    @PutMapping("/profile/regDone")
    public ResponseEntity<Void> setRegistrationComplete(@CurrentUser UserPrincipal userPrincipal) {
        clientService.setRegistrationComplete(userPrincipal);
        return ResponseEntity.ok().build();
    }

    /**
     * Posts a questionnaire and recommends therapists based on the answers.
     */
    @PostMapping("/questionnaire")
    public ResponseEntity<List<TherapistResponse>> postClientQuestionnaire(
            @CurrentUser UserPrincipal userPrincipal, QuestionnaireRequest questionnaireRequest
    ) {
        return new ResponseEntity<>(clientService.saveQuestionnaire(userPrincipal, questionnaireRequest), HttpStatus.OK);
    }

    /**
     * Set ner therapist
     */
    @PutMapping("/therapist")
    public ResponseEntity<Void> setTherapist(
            @CurrentUser UserPrincipal userPrincipal,
            @RequestBody ClientTherapistRelationRequest request
    ) {
        clientService.setNewTherapist(userPrincipal.getId(), request);
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
