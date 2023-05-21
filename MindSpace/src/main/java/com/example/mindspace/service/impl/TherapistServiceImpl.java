package com.example.mindspace.service.impl;

import com.example.mindspace.dao.ThemeRepository;
import com.example.mindspace.dao.TherapistRepository;
import com.example.mindspace.exception.EntityNotFoundException;
import com.example.mindspace.model.*;
import com.example.mindspace.service.interfaces.TherapistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TherapistServiceImpl implements TherapistService {
    private final TherapistRepository therapistRepository;

    private final ThemeRepository themeRepository;

    @Override
    public List<Reservation> findAllReservations(Therapist therapist) {
        List<Reservation> reservations = therapist.getReservations();
        return reservations;
    }

    @Override
    public List<Client> findAllClients(Therapist therapist) {
        return therapist.getClients();
    }

    @Override
    public List<TimeCell> findAllUnreservedTimeCells(Therapist therapist) {
//        List<TimeCell> timeCells = therapist.getTimeCells().stream()
//                .filter(timeCell -> !timeCell.isReserved())
//                .toList();
        return List.of();
    }

    @Override
    public Therapist findByName(String name) throws EntityNotFoundException {
        Therapist therapist = therapistRepository.findByName(name);
        if (therapist == null) {
            throw new EntityNotFoundException("ty loh");
        } else {
            return therapist;
        }
    }

    @Override
    public Therapist findBySurname(String surname) throws EntityNotFoundException {
        Therapist therapist = therapistRepository.findBySurname(surname);
        if (therapist == null) {
            throw new EntityNotFoundException("ty loh");
        } else {
            return therapist;
        }
    }

    @Override
    public List<Theme> findAllThemes(Therapist therapist) {
        return therapist.getThemes();
    }

    @Override
    public void createTherapist(Therapist therapist) {
        therapistRepository.save(therapist);
    }

    @Override
    public void updateTherapist(Therapist therapist) throws EntityNotFoundException {
        if (therapist == null) {
            throw new EntityNotFoundException("nelzya obnovit");
        } else {
            therapistRepository.save(therapist);
        }

    }

    @Override
    public void deleteTherapist(Therapist therapist) throws EntityNotFoundException {
        if (therapist == null) {
            throw new EntityNotFoundException("ty dolboeb ty zachem nesuschestvuyuschego terapevta udalyaesh?");
        } else {
            therapistRepository.delete(therapist);
        }
    }

    @Override
    public Therapist findById(Integer id) throws EntityNotFoundException {
        Therapist therapist = therapistRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Therapist with id " + id + "does not exist"));
        return therapist;
    }

    @Override
    public void addNewTheme(Theme theme, Therapist therapist) {
        therapist.getThemes().add(theme);
        theme.getTherapists().add(therapist);
        therapistRepository.save(therapist);
        themeRepository.save(theme);
    }
}
