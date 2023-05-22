package com.example.mindspace.controller;

import com.example.mindspace.api.ClientResponse;
import com.example.mindspace.api.ReservationResponse;
import com.example.mindspace.api.ScheduleResponse;
import com.example.mindspace.service.impl.TherapistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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
     * Get schedule
     * @param id id of therapist
     * @return schedule
     */
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponse> updateTherapist(@PathVariable Integer id) {
        return new ResponseEntity<>(therapistService.getSchedule(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/schedule")
    public ResponseEntity<Void> getSchedule(@PathVariable Integer id) {
        return null;
    }

}
