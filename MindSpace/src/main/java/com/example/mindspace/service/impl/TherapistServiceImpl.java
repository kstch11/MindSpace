package com.example.mindspace.service.impl;

import com.example.mindspace.api.ClientResponse;
import com.example.mindspace.api.CreateReviewRequest;
import com.example.mindspace.api.CreateReviewResponse;
import com.example.mindspace.api.LanguageResponse;
import com.example.mindspace.api.ReservationResponse;
import com.example.mindspace.api.ReviewResponse;
import com.example.mindspace.api.TherapistResponse;
import com.example.mindspace.api.TopicResponse;
import com.example.mindspace.api.UserRequest;
import com.example.mindspace.repository.ClientRepository;
import com.example.mindspace.repository.ReviewRepository;
import com.example.mindspace.repository.ThemeRepository;
import com.example.mindspace.repository.TherapistRepository;
import com.example.mindspace.exception.EntityNotFoundException;
import com.example.mindspace.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TherapistServiceImpl {

    private final TherapistRepository therapistRepository;
    private final ThemeRepository themeRepository;
    private final ClientRepository clientRepository;
    private final ReviewRepository reviewRepository;

    public TherapistResponse getTherapistDetails(Integer id) {
        Therapist therapist = findById(id);

        return new TherapistResponse(
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
                therapist.getThemes().stream().map(theme -> new TopicResponse(theme.getId(), theme.getName())).toList()
        );
    }

    /**
     * Find all reservations
     * @param id id of therapist
     * @return reservations list
     */
    public List<ReservationResponse> findAllReservations(Integer id) {
        Therapist therapist = findById(id);
        return therapist.getReservations().stream()
                .map(reservation -> {
                    Client client = reservation.getClient();

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
                                    therapist.getThemes().stream().map(theme -> new TopicResponse(theme.getId(), theme.getName())).toList()
                            )
                    );
                })
                .toList();
    }

    /**
     * Find all reservations
     * @param id id of therapist
     * @return reservations list
     */
    public List<ClientResponse> findAllClients(Integer id) {
        return findById(id).getClients().stream().map(r -> new ClientResponse(
                r.getId(),
                r.getTherapist() == null ? null : r.getTherapist().getId(),
                r.getName(),
                r.getSurname(),
                r.getPhoneNumber(),
                r.getEmail(),
                r.isRegistrationFinished(),
                false
        )).toList();
    }

    /**
     * Find all reservations
     * @param id id of therapist
     * @return schedule
     */
//    public ScheduleResponse getSchedule(Integer id) {
//        var schedule = findById(id).getSchedule();
//        return new ScheduleResponse();
//    }

    /**
     * Updates a client
     *
     * @param id client id
     * @param request request id
     */
    public void updateTherapist(Integer id, UserRequest request) {
        var therapist = findById(id);

        if (request.name() != null && request.name().isBlank()) {
            therapist.setName(request.name());
        }
        if (request.surname() != null && request.surname().isBlank()) {
            therapist.setSurname(request.surname());
        }
        if (request.number() != null && request.number().isBlank()) {
            therapist.setPhoneNumber(request.number());
        }
        if (request.email() != null && request.email().isBlank()) {
            therapist.setEmail(request.email());
        }

        therapistRepository.save(therapist);
    }

    public CreateReviewResponse createReview(Integer therapistId, CreateReviewRequest request) {
        var therapist = findById(therapistId);
        var client = clientRepository.findById(request.author())
                .orElseThrow(() -> new EntityNotFoundException("No such client"));

        var review = new Review(request.text(), client, therapist);
        Review save = reviewRepository.save(review);
        return new CreateReviewResponse(save.getId());
    }

    public List<ReviewResponse> getAllReviews(Integer therapistId) {
        var therapist = findById(therapistId);

        return reviewRepository.findReviewsByRecipient(therapist).stream()
                .map(review -> new ReviewResponse(review.getAuthor().getId(), therapist.getId(), review.getText()))
                .toList();
    }

    public List<TherapistResponse> getAllTherapists() {
        return therapistRepository.findAll().stream().map(t -> new TherapistResponse(
                t.getId(),
                t.getName(),
                t.getSurname(),
                t.getPhoneNumber(),
                t.getEmail(),
                t.isRegistrationFinished(),
                t.getDescription(),
                t.getEducation(),
                t.getLanguages().stream().map(lang -> new LanguageResponse(lang.getId(), lang.getName())).toList(),
                t.getPersonalTherapy(),
                t.getPhoto(),
                t.getTherapeuticCommunity(),
                t.isApproved(),
                true,
                t.getThemes().stream().map(theme -> new TopicResponse(theme.getId(), theme.getName())).toList()
        )).toList();
    }


    private Therapist findById(Integer id) {
        return therapistRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Therapist with id " + id + "does not exist"));
    }

    public List<Reservation> getHistory(Therapist therapist) {
        return null;
    }
}
