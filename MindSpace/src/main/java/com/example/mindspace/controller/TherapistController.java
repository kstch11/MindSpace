package com.example.mindspace.controller;

import com.example.mindspace.api.ClientResponse;
import com.example.mindspace.api.CreateReviewRequest;
import com.example.mindspace.api.CreateReviewResponse;
import com.example.mindspace.api.ReservationResponse;
import com.example.mindspace.api.ReviewResponse;
import com.example.mindspace.api.TherapistResponse;
import com.example.mindspace.api.UserRequest;
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

@RestController
@RequestMapping("/therapists")
public class TherapistController {

    private final TherapistServiceImpl therapistService;

    @Autowired
    public TherapistController(TherapistServiceImpl therapistService) {
        this.therapistService = therapistService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TherapistResponse> getTherapist(Integer id) {
        return new ResponseEntity<>(therapistService.findTherapist(id), HttpStatus.OK);
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
     * Updates client
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTherapist(@PathVariable Integer id, @RequestBody UserRequest userRequest) {
        therapistService.updateTherapist(id, userRequest);
        return ResponseEntity.noContent().build();
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
