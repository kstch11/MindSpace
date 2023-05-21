package com.example.mindspace.service.interfaces;

import com.example.mindspace.exception.EntityNotFoundException;
import com.example.mindspace.model.Client;
import com.example.mindspace.model.Reservation;
import com.example.mindspace.model.Therapist;

import java.util.List;

public interface ClientService {
    public void createClient(Client client);

    public void updateClient(Client client) throws EntityNotFoundException;

    public void deleteClient(Client client) throws EntityNotFoundException;

    public Client findById(Integer id) throws EntityNotFoundException;

    public void deleteTherapist(Client client);

    public Client setNewTherapist(Client client, Therapist therapist);

    public List<Reservation> findAllReservations(Client client);


}
