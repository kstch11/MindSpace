package com.example.mindspace.controller;

import com.example.mindspace.api.CancelReservationResponse;
import com.example.mindspace.api.ReservationRequest;
import com.example.mindspace.api.CreateReservationResponse;
import com.example.mindspace.api.ReservationResponse;
import com.example.mindspace.service.impl.ReservationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private final Logger LOGGER = Logger.getLogger(ReservationController.class.getName());

    private final ReservationServiceImpl reservationService;

    public ReservationController(ReservationServiceImpl reservationService) {
        this.reservationService = reservationService;
    }

    /**
     * Creates a new reservation based on the provided request.
     *
     * @param request The reservation request containing all necessary information to create a reservation.
     * @return ResponseEntity containing the response of the created reservation and an HTTP status code.
     */
    @PostMapping
    public ResponseEntity<CreateReservationResponse> createReservation(@RequestBody ReservationRequest request) {
        return new ResponseEntity<>(reservationService.createReservation(request), HttpStatus.CREATED);
    }

    /**
     * Retrieves the details of a specific reservation.
     *
     * @param id The ID of the reservation.
     * @return ResponseEntity containing the details of the reservation and an HTTP status code.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponse> getReservation(@PathVariable Integer id) {
        return new ResponseEntity<>(reservationService.getReservation(id), HttpStatus.OK);
    }

    /**
     * Cancels an existing reservation.
     *
     * @param id The ID of the reservation to be canceled.
     * @return ResponseEntity indicating the operation's success.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<CancelReservationResponse> cancelReservation(@PathVariable Integer id) {
        return new ResponseEntity<>(reservationService.cancelReservation(id), HttpStatus.OK);
    }

    /**
     * Delays an existing reservation to a new timeslot.
     *
     * @param reservationId The ID of the reservation to be delayed.
     * @param timeCellId The ID of the new timeslot for the reservation.
     * @return ResponseEntity indicating the operation's success.
     */
    @PutMapping("/{reservationId}/delay/{timeCellId}")
    public ResponseEntity<Void> delayReservation(
            @PathVariable Integer reservationId,
            @PathVariable Integer timeCellId
    ) {
        reservationService.delayReservation(reservationId, timeCellId);
        return ResponseEntity.noContent().build();
    }
}
