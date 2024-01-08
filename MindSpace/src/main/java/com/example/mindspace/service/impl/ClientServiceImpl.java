package com.example.mindspace.service.impl;

import com.example.mindspace.api.ClientResponse;
import com.example.mindspace.api.ClientTherapistRelationRequest;
import com.example.mindspace.api.LanguageResponse;
import com.example.mindspace.api.QuestionnaireRequest;
import com.example.mindspace.api.ReservationResponse;
import com.example.mindspace.api.TherapistResponse;
import com.example.mindspace.api.TopicResponse;
import com.example.mindspace.api.UserRequest;
import com.example.mindspace.model.TimeCell;
import com.example.mindspace.repository.ClientRepository;
import com.example.mindspace.repository.ReservationRepository;
import com.example.mindspace.repository.TherapistRepository;
import com.example.mindspace.exception.EntityNotFoundException;
import com.example.mindspace.model.Client;
import com.example.mindspace.repository.TimeCellRepository;
import com.example.mindspace.security.UserPrincipal;
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

    public void setRegistrationComplete(UserPrincipal userPrincipal) {
        Client client = findById(userPrincipal.getId());
        client.setRegistrationFinished(true);
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
                client.getEmail(),
                client.isRegistrationFinished(),
                false
        );
    }

    public List<TherapistResponse> saveQuestionnaire(UserPrincipal userPrincipal, QuestionnaireRequest questionnaireRequest) {
        Client client = findById(userPrincipal.getId());

        client.setName(questionnaireRequest.name());
        client.setSurname(questionnaireRequest.surname());
        client.setPhoneNumber(questionnaireRequest.phoneNumber());
        clientRepository.save(client);
        return therapistRepository.findAll()
                .stream()
                .map(therapist -> new TherapistResponse(
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
                )).toList();
    }

    /**
     * Sets a new therapist for a client.
     *
     * @param clientId the ID of the client
     * @param request  the ClientTherapistRelationRequest object containing the therapist ID
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
                })
                .toList();
    }

    /**
     * Updates a client
     *
     * @param id      client id
     * @param request request id
     */
    public void updateClient(Integer id, UserRequest request) {
        var client = findById(id);

        if (request.name() != null && !request.name().isBlank()) {
            client.setName(request.name());
        }
        if (request.surname() != null && !request.surname().isBlank()) {
            client.setSurname(request.surname());
        }
        if (request.number() != null && !request.number().isBlank()) {
            client.setPhoneNumber(request.number());
        }
        if (request.email() != null && !request.email().isBlank()) {
            client.setEmail(request.email());
        }

        clientRepository.save(client);
    }


    private Client findById(Integer id) {
        return clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client not found"));
    }


}
