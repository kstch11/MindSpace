package com.example.mindspace.controller;

import com.example.mindspace.api.ReservationRequest;
import com.example.mindspace.api.CreateReservationResponse;
import com.example.mindspace.api.ReservationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    // create
    @PostMapping
    public ResponseEntity<CreateReservationResponse> createReservation(@RequestBody ReservationRequest request) {
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    // get
    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponse> getReservation(@PathVariable Integer id) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    // delay

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Integer id) {
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
