package com.example.mindspace.service.impl;

import com.example.mindspace.api.ClientResponse;
import com.example.mindspace.api.ClientTherapistRelationRequest;
import com.example.mindspace.api.ReservationResponse;
import com.example.mindspace.api.TherapistResponse;
import com.example.mindspace.api.UserRequest;
import com.example.mindspace.model.Reservation;
import com.example.mindspace.model.TimeCell;
import com.example.mindspace.repository.ClientRepository;
import com.example.mindspace.repository.ReservationRepository;
import com.example.mindspace.repository.TherapistRepository;
import com.example.mindspace.exception.EntityNotFoundException;
import com.example.mindspace.model.Client;
import com.example.mindspace.repository.TimeCellRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl {

    private final ClientRepository clientRepository;
    private final TherapistRepository therapistRepository;
    private final ReservationRepository reservationRepository;
    private final TimeCellRepository timeCellRepository;

    /**
     * Cancels the therapist for a client.
     *
     * @param clientId the ID of the client
     * @throws RuntimeException if the client is not found
     */
    public void cancelTherapist(Integer clientId) {
        var client = clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));

        client.setTherapist(null);
        client.getReservations().forEach(reservation -> {
            reservation.setClient(null);
            reservation.setTherapist(null);
            TimeCell timeCell = reservation.getTimeCell();
            timeCell.setReservation(null);
            timeCellRepository.save(timeCell);
            reservation.setTimeCell(null);
            reservationRepository.save(reservation);
        });
        client.setReservations(null);
        clientRepository.save(client);
    }

    /**
     * get client id
     *
     * @param clientId the ID of the client
     */
    public ClientResponse getClientDetails(Integer clientId) {
        var client = findById(clientId);
        return new ClientResponse(
                client.getId(),
                client.getTherapist() == null ? null : client.getTherapist().getId(),
                client.getName(),
                client.getSurname(),
                client.getPhoneNumber(),
                client.getEmail()
        );
    }

    /**
     * Sets a new therapist for a client.
     *
     * @param clientId the ID of the client
     * @param request the ClientTherapistRelationRequest object containing the therapist ID
     * @throws RuntimeException if the client or therapist is not found
     */
    public void setNewTherapist(Integer clientId, ClientTherapistRelationRequest request) {
        var client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        var therapist = therapistRepository.findById(request.therapistId())
                .orElseThrow(() -> new RuntimeException("Therapist not found"));

        client.setTherapist(therapist);
        clientRepository.save(client);
    }


    /**
     * Find all reservations.
     */
    public List<ReservationResponse> findAllReservations(Integer clientId) {
        var client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        return client.getReservations().stream()
                .map(reservation -> {
                    var therapist = reservation.getTherapist();

                    return new ReservationResponse(
                            reservation.getId(),
                            reservation.getTimeCell().getStartTime()
                                    .format(DateTimeFormatter.ofPattern("hh:mm dd/MM/yyyy")),
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
                })
                .toList();
    }

    /**
     * Updates a client
     *
     * @param id client id
     * @param request request id
     */
    public void updateClient(Integer id, UserRequest request) {
        var client = findById(id);

        if (request.name() != null && request.name().isBlank()) {
            client.setName(request.name());
        }
        if (request.surname() != null && request.surname().isBlank()) {
            client.setSurname(request.surname());
        }
        if (request.number() != null && request.number().isBlank()) {
            client.setPhoneNumber(request.number());
        }
        if (request.email() != null && request.email().isBlank()) {
            client.setEmail(request.email());
        }

        clientRepository.save(client);
    }

    public void createClient(Client client) {
        clientRepository.save(client);
    }

    public void deleteClient(Client client) {
        if (client == null) {
            throw new EntityNotFoundException("");
        } else {
            clientRepository.delete(client);
        }
    }

    public Client findById(Integer id) {
        return clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client not found"));
    }
}
