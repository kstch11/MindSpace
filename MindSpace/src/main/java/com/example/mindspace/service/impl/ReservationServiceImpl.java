package com.example.mindspace.service.impl;

import com.example.mindspace.api.ClientResponse;
import com.example.mindspace.api.CreateReservationResponse;
import com.example.mindspace.api.ReservationRequest;
import com.example.mindspace.api.ReservationResponse;
import com.example.mindspace.api.TherapistResponse;
import com.example.mindspace.dao.ClientRepository;
import com.example.mindspace.dao.ReservationRepository;
import com.example.mindspace.dao.TherapistRepository;
import com.example.mindspace.dao.TimeCellRepository;
import com.example.mindspace.exception.EntityNotFoundException;
import com.example.mindspace.model.Reservation;
import com.example.mindspace.model.TimeCell;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl {
    private final ReservationRepository reservationRepository;
    private final TherapistRepository therapistRepository;
    private final ClientRepository clientRepository;
    private final TimeCellRepository timeCellRepository;

    /**
     * Create a reservation
     * @param reservationDto reservation req
     * @return create response dto
     */
    public CreateReservationResponse createReservation(ReservationRequest reservationDto) {
        var therapist = therapistRepository.findById(reservationDto.therapistId())
                .orElseThrow(() -> new EntityNotFoundException("Therapist not found"));

        var timeCellId = reservationDto.timeCellId();
        var schedule = therapist.getSchedule();

        TimeCell requestedTimeslot = schedule.getAvailableTimeCells().stream()
                // check if schedule contains the requested time cell & if it is available to book.
                .filter(cell -> cell.getId().equals(timeCellId) && !(cell.isReserved() || cell.isExpired()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Requested timeslot is not available"));

        var client = clientRepository.findById(reservationDto.clientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));
        var reservation = new Reservation(client, requestedTimeslot, therapist);
        requestedTimeslot.setReservation(reservation);
        Reservation saved = reservationRepository.save(reservation);
        timeCellRepository.save(requestedTimeslot);
        return new CreateReservationResponse(saved.getId());
    }

    /**
     * Cancels reservation.
     * @param reservationId the id of reservation
     */
    public void cancelReservation(Integer reservationId) {
        var reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("reservation not found"));

        TimeCell timeCell = reservation.getTimeCell();
        timeCell.setReservation(null);
        timeCellRepository.save(timeCell);

        reservationRepository.delete(reservation);
    }

    /**
     * get reservation
     * @param reservationId id of reservation
     * @return reservation dto
     */
    public ReservationResponse getReservation(Integer reservationId) {
        var reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("reservation not found"));

        var client = reservation.getClient();
        var therapist = reservation.getTherapist();

        return new ReservationResponse(
                reservationId,
                reservation.getTimeCell().getStartTime().format(DateTimeFormatter.ofPattern("hh:mm dd/MM/yyyy")),
                new ClientResponse(
                        client.getId(),
                        client.getTherapist().getId(),
                        client.getName(),
                        client.getSurname(),
                        client.getPhoneNumber(),
                        client.getEmail()
                ),
                new TherapistResponse(
                        therapist.getId(),
                        therapist.getName(),
                        therapist.getSurname(),
                        therapist.getPhoneNumber(),
                        therapist.getEmail()
                )
        );
    }

    public void delayReservation(Reservation reservation, LocalDateTime dateTime) throws EntityNotFoundException {
        if (reservation != null && dateTime != null) {
//            reservation.setStartTime(dateTime);
            reservationRepository.save(reservation);
        } else {
            throw new EntityNotFoundException("nelzya");
        }

    }

//    public Reservation findById(Integer id) {
//        return reservationRepository.findById(id).get();
//    }
}
