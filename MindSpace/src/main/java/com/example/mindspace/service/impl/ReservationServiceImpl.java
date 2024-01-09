package com.example.mindspace.service.impl;

import com.example.mindspace.api.ClientResponse;
import com.example.mindspace.api.CreateReservationResponse;
import com.example.mindspace.api.LanguageResponse;
import com.example.mindspace.api.ReservationRequest;
import com.example.mindspace.api.ReservationResponse;
import com.example.mindspace.api.TherapistResponse;
import com.example.mindspace.api.TopicResponse;
import com.example.mindspace.repository.ClientRepository;
import com.example.mindspace.repository.ReservationRepository;
import com.example.mindspace.repository.TherapistRepository;
import com.example.mindspace.repository.TimeCellRepository;
import com.example.mindspace.exception.EntityNotFoundException;
import com.example.mindspace.model.Reservation;
import com.example.mindspace.model.TimeCell;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
                        client.getEmail(),
                        client.isRegistrationFinished(),
                        false
                ),
                new TherapistResponse(
                        therapist.getId(),
                        therapist.getName(),
                        therapist.getSurname(),
                        therapist.getPhoneNumber(),
                        therapist.getEmail(),
                        therapist.isRegistrationFinished(),
                        therapist.getDescription(),
                        therapist.getEducation(),
                        therapist.getLanguages().stream().map(lang -> new LanguageResponse(lang.getId(), lang.getName())).toList(),
                        therapist.getPersonalTherapy(),
                        therapist.getPhoto(),
                        therapist.getTherapeuticCommunity(),
                        therapist.isApproved(),
                        true,
                        therapist.getThemes().stream().map(theme -> new TopicResponse(theme.getId(), theme.getName())).toList(),
                        therapist.getExperience()
                )
        );
    }

    /**
     * Delays an existing reservation to a new timeslot.
     *
     * @param reservationId The ID of the reservation to be delayed.
     * @param timeCellId The ID of the new timeslot for the reservation.
     * @throws EntityNotFoundException if the reservation or the new timeslot is not found.
     * @throws IllegalArgumentException if the new timeslot is not available.
     */
    public void delayReservation(Integer reservationId, Integer timeCellId) {
        var reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("reservation not found"));
        var timecCell = timeCellRepository.findById(timeCellId)
                .orElseThrow(() -> new EntityNotFoundException("time cell not found"));

        if (timecCell.isReserved() || timecCell.isExpired()) {
            throw new IllegalArgumentException("Requested timeslot is not available");
        }

        TimeCell exTimeCell = reservation.getTimeCell();
        exTimeCell.setReservation(null);
        timeCellRepository.save(exTimeCell);

        timecCell.setReservation(reservation);
        TimeCell save = timeCellRepository.save(timecCell);

        reservation.setTimeCell(save);
        reservationRepository.save(reservation);
    }
    
}
