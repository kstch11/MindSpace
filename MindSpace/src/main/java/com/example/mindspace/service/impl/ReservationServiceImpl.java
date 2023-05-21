package com.example.mindspace.service.impl;

import com.example.mindspace.api.ReservationRequest;
import com.example.mindspace.dao.ClientRepository;
import com.example.mindspace.dao.ReservationRepository;
import com.example.mindspace.dao.TherapistRepository;
import com.example.mindspace.exception.EntityNotFoundException;
import com.example.mindspace.model.Reservation;
import com.example.mindspace.model.TimeCell;
import com.example.mindspace.service.interfaces.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final TherapistRepository therapistRepository;
    private final ClientRepository clientRepository;

    @Override
    public void createReservation(ReservationRequest reservationDto) {
        var therapist = therapistRepository.findById(reservationDto.therapistId())
                .orElseThrow(() -> new EntityNotFoundException("Therapist not found"));

        var timeCellId = reservationDto.timeCellId();
        var schedule = therapist.getSchedule();

        TimeCell requestedTimeslot = schedule.getAvailableTimeCells().stream()
                // check if schedule contains the requested time cell & if it is available to book.
                .filter(cell -> cell.getId().equals(timeCellId) && !cell.isReserved() && !cell.isExpired())
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Requested timeslot is not available"));

        // TODO: fetch clients and add them here
        var reservation = new Reservation(null, requestedTimeslot, therapist);
        reservationRepository.save(reservation);
    }

    @Override // TODO: work with ReservationDto
    public void cancelReservation(Reservation reservation) {
        if (reservation != null) {
            reservation.getTherapist().getReservations().remove(reservation);
            therapistRepository.save(reservation.getTherapist());
//            reservation.getMembers().stream().forEach(member -> {
//                member.getReservations().remove(reservation);
//                clientRepository.save(member);
//            });
            reservationRepository.delete(reservation);
        } else {
            throw new EntityNotFoundException("nelzya");
        }
    }

    @Override
    public void delayReservation(Reservation reservation, LocalDateTime dateTime) throws EntityNotFoundException {
        if (reservation != null && dateTime != null) {
//            reservation.setStartTime(dateTime);
            reservationRepository.save(reservation);
        } else {
            throw new EntityNotFoundException("nelzya");
        }

    }

    @Override
    public Reservation findById(Integer id) {
        return reservationRepository.findById(id).get();
    }
}
