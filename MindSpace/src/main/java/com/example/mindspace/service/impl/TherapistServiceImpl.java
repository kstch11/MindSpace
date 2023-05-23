package com.example.mindspace.service.impl;

import com.example.mindspace.api.ClientResponse;
import com.example.mindspace.api.ReservationResponse;
import com.example.mindspace.api.ScheduleResponse;
import com.example.mindspace.dao.ThemeRepository;
import com.example.mindspace.dao.TherapistRepository;
import com.example.mindspace.exception.EntityNotFoundException;
import com.example.mindspace.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TherapistServiceImpl {

    private final TherapistRepository therapistRepository;
    private final ThemeRepository themeRepository;

    /**
     * Find all reservations
     * @param id id of therapist
     * @return reservations list
     */
    public List<ReservationResponse> findAllReservations(Integer id) {
        return findById(id).getReservations().stream().map(r -> new ReservationResponse(r.getId())).toList();
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
                r.getEmail()
        )).toList();
    }

    /**
     * Find all reservations
     * @param id id of therapist
     * @return schedule
     */
    public ScheduleResponse getSchedule(Integer id) {
        var schedule = findById(id).getSchedule();
        return new ScheduleResponse();
    }


    public Therapist findByName(String name) throws EntityNotFoundException {
        Therapist therapist = therapistRepository.findByName(name);
        if (therapist == null) {
            throw new EntityNotFoundException("ty loh");
        } else {
            return therapist;
        }
    }

    public Therapist findBySurname(String surname) throws EntityNotFoundException {
        Therapist therapist = therapistRepository.findBySurname(surname);
        if (therapist == null) {
            throw new EntityNotFoundException("ty loh");
        } else {
            return therapist;
        }
    }

    public List<Theme> findAllThemes(Therapist therapist) {
        return therapist.getThemes();
    }

    public void createTherapist(Therapist therapist) {
        therapistRepository.save(therapist);
    }

    public void updateTherapist(Therapist therapist) throws EntityNotFoundException {
        if (therapist == null) {
            throw new EntityNotFoundException("nelzya obnovit");
        } else {
            therapistRepository.save(therapist);
        }

    }

    public void deleteTherapist(Therapist therapist) throws EntityNotFoundException {
        if (therapist == null) {
            throw new EntityNotFoundException("ty dolboeb ty zachem nesuschestvuyuschego terapevta udalyaesh?");
        } else {
            therapistRepository.delete(therapist);
        }
    }

    public void addNewTheme(Theme theme, Therapist therapist) {
        therapist.getThemes().add(theme);
        theme.getTherapists().add(therapist);
        therapistRepository.save(therapist);
        themeRepository.save(theme);
    }

    private Therapist findById(Integer id) {
        return therapistRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Therapist with id " + id + "does not exist"));
    }
}
