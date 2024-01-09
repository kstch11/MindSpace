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
import java.util.logging.Logger;

@RestController
@RequestMapping("/clients")
public class ClientsController {
    private final ClientServiceImpl clientService;

    @Autowired
    public ClientsController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    /**
     * Retrieves the details of a specific client.
     *
     * @param id The ID of the client.
     * @return ResponseEntity containing the client's details and an HTTP status code.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> getDetails(@PathVariable Integer id) {
        return new ResponseEntity<>(clientService.getClientDetails(id), HttpStatus.OK);
    }

    /**
     * Retrieves the profile information of the currently authenticated client.
     *
     * @param userPrincipal The principal of the currently authenticated client.
     * @return ResponseEntity containing the client's profile information and an HTTP status code.
     */
    @GetMapping("/profile")
    public ResponseEntity<ClientResponse> getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return new ResponseEntity<>(clientService.getClientDetails(userPrincipal.getId()), HttpStatus.OK);
    }

    /**
     * Marks the registration of a client as complete.
     *
     * @param userPrincipal The principal of the currently authenticated client.
     * @return ResponseEntity indicating the operation's success.
     */
    @PutMapping("/profile/regDone")
    public ResponseEntity<Void> setRegistrationComplete(@CurrentUser UserPrincipal userPrincipal) {
        clientService.setRegistrationComplete(userPrincipal);
        return ResponseEntity.ok().build();
    }

    /**
     * Processes a questionnaire submitted by a client and returns a list of recommended therapists.
     *
     * @param userPrincipal The principal of the currently authenticated client.
     * @param questionnaireRequest The request containing the questionnaire data.
     * @return ResponseEntity containing a list of recommended therapists and an HTTP status code.
     */
    @PostMapping("/questionnaire")
    public ResponseEntity<List<TherapistResponse>> postClientQuestionnaire(
            @CurrentUser UserPrincipal userPrincipal, @RequestBody QuestionnaireRequest questionnaireRequest
    ) {
        return new ResponseEntity<>(clientService.saveQuestionnaire(userPrincipal, questionnaireRequest), HttpStatus.OK);
    }

    /**
     * Assigns a new therapist to a client based on the provided request.
     *
     * @param userPrincipal The principal of the currently authenticated client.
     * @param request The request object containing the ID of the therapist to be assigned.
     * @return ResponseEntity indicating the operation's success.
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
     * Retrieves the assigned therapist for the current client.
     *
     * @param userPrincipal The principal of the currently authenticated client.
     * @param request The request object containing the details for retrieving the therapist.
     * @return ResponseEntity indicating the operation's success.
     */
    @GetMapping("/therapist")
    public ResponseEntity<Void> getTherapist(
            @CurrentUser UserPrincipal userPrincipal,
            @RequestBody ClientTherapistRelationRequest request
    ) {
        clientService.setNewTherapist(userPrincipal.getId(), request);
        return ResponseEntity.ok().build();
    }

    /**
     * Cancels the association between a client and their therapist.
     *
     * @param id The ID of the client.
     * @return ResponseEntity indicating the operation's success.
     */
    @DeleteMapping("/{id}/therapist")
    public ResponseEntity<Void> cancelTherapist(@PathVariable Integer id) {
        clientService.cancelTherapist(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves all reservations associated with a specific client.
     *
     * @param id The ID of the client.
     * @return ResponseEntity containing a list of the client's reservations and an HTTP status code.
     */
    @GetMapping("/{id}/reservations")
    public ResponseEntity<List<ReservationResponse>> getAllReservations(@PathVariable Integer id) {
        return new ResponseEntity<>(clientService.findAllReservations(id), HttpStatus.OK);
    }

    /**
     * Updates the profile information of a client.
     *
     * @param id The ID of the client.
     * @param userRequest The request containing the client's updated information.
     * @return ResponseEntity indicating the operation's success.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateClient(@PathVariable Integer id, @RequestBody UserRequest userRequest) {
        clientService.updateClient(id, userRequest);
        return ResponseEntity.noContent().build();
    }
}
