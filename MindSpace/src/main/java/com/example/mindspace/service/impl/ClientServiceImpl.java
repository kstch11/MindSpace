package com.example.mindspace.service.impl;

import com.example.mindspace.api.ClientTherapistRelationRequest;
import com.example.mindspace.api.ReservationResponse;
import com.example.mindspace.dao.ClientRepository;
import com.example.mindspace.dao.TherapistRepository;
import com.example.mindspace.exception.EntityNotFoundException;
import com.example.mindspace.model.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl {

    private final ClientRepository clientRepository;
    private final TherapistRepository therapistRepository;

    /**
     * Cancels the therapist for a client.
     *
     * @param clientId the ID of the client
     * @throws RuntimeException if the client is not found
     */
    public void cancelTherapist(Integer clientId) {
        var client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        client.setTherapist(null);
        clientRepository.save(client);
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
                .map(reservation -> new ReservationResponse(reservation.getId()))
                .toList(); // TODO
    }

    public void createClient(Client client) {
        clientRepository.save(client);
    }

    public void updateClient(Client client) throws EntityNotFoundException {
        if (client == null) {
            throw new EntityNotFoundException("");
        } else {
            clientRepository.save(client);
        }
    }

    public void deleteClient(Client client) throws EntityNotFoundException {
        if (client == null) {
            throw new EntityNotFoundException("");
        } else {
            clientRepository.delete(client);
        }
    }

    public Client findById(Integer id) throws EntityNotFoundException {
        Client client = clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("net tut takih"));
        return client;
    }
}
