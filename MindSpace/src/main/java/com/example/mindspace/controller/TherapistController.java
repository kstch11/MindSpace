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

    /**
     * Retrieves the details of a therapist based on their ID.
     *
     * @param id The ID of the therapist.
     * @return ResponseEntity containing the details of the therapist and an HTTP status code.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TherapistResponse> getTherapist(@PathVariable Integer id) {
        LOGGER.info("Request is here");
        return new ResponseEntity<>(therapistService.getTherapistDetails(id), HttpStatus.OK);
    }

    /**
     * Marks the registration of a therapist as complete.
     *
     * @param userPrincipal The principal of the currently authenticated user.
     * @return ResponseEntity indicating the operation's success.
     */
    @PutMapping("/profile/regDone")
    public ResponseEntity<Void> setTherapistRegistrationComplete(@CurrentUser UserPrincipal userPrincipal) {
        LOGGER.info("I am here");
        therapistService.setTherapistRegistrationComplete(userPrincipal);
        return ResponseEntity.ok().build();
    }

    /**
     * Submits and processes a therapist's questionnaire response.
     *
     * @param userPrincipal The principal of the currently authenticated therapist.
     * @param questionnaireRequest The request containing the questionnaire data.
     * @return ResponseEntity with the questionnaire response and an HTTP status code.
     */
    @PostMapping("/therapistQuestionnaire")
    public ResponseEntity<TherapistQuestionnaireResponse> postTherapistQuestionnaire(
            @CurrentUser UserPrincipal userPrincipal,
            @RequestBody TherapistQuestionnaireRequest questionnaireRequest
            ) {
        return new ResponseEntity<>(therapistService.saveTherapistQuestionnaire(userPrincipal, questionnaireRequest), HttpStatus.OK);
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
     * Updates the profile information of a therapist.
     *
     * @param id The ID of the therapist.
     * @param userRequest The request containing the therapist's updated information.
     * @return ResponseEntity indicating the operation's success.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTherapist(@PathVariable Integer id, @RequestBody UserRequest userRequest) {
        therapistService.updateTherapist(id, userRequest);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves all registered therapists.
     *
     * @return ResponseEntity containing a list of all therapists and an HTTP status code.
     */
    @GetMapping("/allTherapists")
    public ResponseEntity<List<TherapistResponse>> getAllTherapists() {
        return new ResponseEntity<>(therapistService.getAllTherapists(), HttpStatus.OK);
    }

    /**
     * Creates a review for a therapist.
     *
     * @param id The ID of the therapist.
     * @param request The request containing details for creating the review.
     * @return ResponseEntity containing the created review response and an HTTP status code.
     */
    @PostMapping("/{id}/reviews")
    public ResponseEntity<CreateReviewResponse> createReview(
            @PathVariable Integer id,
            @RequestBody CreateReviewRequest request
    ) {
        return new ResponseEntity<>(therapistService.createReview(id, request), HttpStatus.CREATED);
    }

    /**
     * Retrieves all reviews for a therapist.
     *
     * @param id The ID of the therapist.
     * @return ResponseEntity containing a list of reviews for the therapist and an HTTP status code.
     */
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
