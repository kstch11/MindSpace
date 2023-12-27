package com.example.mindspace.service.interfaces;

import com.example.mindspace.exception.EntityNotFoundException;
import com.example.mindspace.model.*;

import java.util.List;

public interface TherapistService {
    public void createTherapist(Therapist therapist);

    public void updateTherapist(Therapist therapist) throws EntityNotFoundException;

    public void deleteTherapist(Therapist therapist) throws EntityNotFoundException;

    public Therapist findById(Integer id) throws EntityNotFoundException;
    public List<Reservation> findAllReservations(Therapist therapist);

    public List<Client> findAllClients(Therapist therapist);

    public List<TimeCell> findAllUnreservedTimeCells(Therapist therapist);

    public Therapist findByName(String name) throws EntityNotFoundException;

    public Therapist findBySurname(String surname) throws EntityNotFoundException;

    public List<Theme> findAllThemes(Therapist therapist);

    public void addNewTheme(Theme theme, Therapist therapist);

    public List<Reservation> getHistory(Integer id);
}
