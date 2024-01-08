package com.example.mindspace.controller;

import com.example.mindspace.api.*;
import com.example.mindspace.security.CurrentUser;
import com.example.mindspace.security.UserPrincipal;
import com.example.mindspace.service.impl.TherapistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/therapists")
public class TherapistController {
    private final Logger LOGGER = Logger.getLogger(TherapistController.class.getName());

    private final TherapistServiceImpl therapistService;



    @Autowired
    public TherapistController(TherapistServiceImpl therapistService) {
        this.therapistService = therapistService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TherapistResponse> getTherapist(@PathVariable Integer id) {
        LOGGER.info("Request is here");
        return new ResponseEntity<>(therapistService.getTherapistDetails(id), HttpStatus.OK);
    }

    @PutMapping("/profile/regDone")
    public ResponseEntity<Void> setTherapistRegistrationComplete(@CurrentUser UserPrincipal userPrincipal) {
        LOGGER.info("I am here");
        therapistService.setTherapistRegistrationComplete(userPrincipal);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/therapistQuestionnaire")
    public ResponseEntity<Void> postTherapistQuestionnaire(
            @CurrentUser UserPrincipal userPrincipal,
            @RequestBody TherapistQuestionnaireRequest questionnaireRequest
            ) {
        LOGGER.info("Now I am here");
        therapistService.saveTherapistQuestionnaire(userPrincipal, questionnaireRequest);
        return ResponseEntity.ok().build();
    }

    /**
     * Get all reservations
     * @param id therapist id
     * @return list of reservations
     */
    @GetMapping("/{id}/reservations")
    public ResponseEntity<List<ReservationResponse>> getAllReservations(@PathVariable Integer id) {
        return new ResponseEntity<>(therapistService.findAllReservations(id), HttpStatus.OK);
    }

    /**
     * Get all clients
     * @param id therapist id
     * @return list of clients
     */
    @GetMapping("/{id}/clients")
    public ResponseEntity<List<ClientResponse>> getAllTherapistClients(@PathVariable Integer id) {
        return new ResponseEntity<>(therapistService.findAllClients(id), HttpStatus.OK);
    }

    /**
     * Updates therapist
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTherapist(@PathVariable Integer id, @RequestBody UserRequest userRequest) {
        therapistService.updateTherapist(id, userRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/allTherapists")
    public ResponseEntity<List<TherapistResponse>> getAllTherapists() {
        return new ResponseEntity<>(therapistService.getAllTherapists(), HttpStatus.OK);
    }

    @PostMapping("/{id}/reviews")
    public ResponseEntity<CreateReviewResponse> createReview(
            @PathVariable Integer id,
            @RequestBody CreateReviewRequest request
    ) {
        return new ResponseEntity<>(therapistService.createReview(id, request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<List<ReviewResponse>> getReviews(
            @PathVariable Integer id
    ) {
        return new ResponseEntity<>(therapistService.getAllReviews(id), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/schedule")
    public ResponseEntity<Void> getSchedule(@PathVariable Integer id) {
        return null;
    }

}
