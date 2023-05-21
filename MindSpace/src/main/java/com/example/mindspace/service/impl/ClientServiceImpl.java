package com.example.mindspace.service.impl;

import com.example.mindspace.dao.ClientRepository;
import com.example.mindspace.dao.TherapistRepository;
import com.example.mindspace.exception.EntityNotFoundException;
import com.example.mindspace.model.Client;
import com.example.mindspace.model.Reservation;
import com.example.mindspace.model.Therapist;
import com.example.mindspace.service.interfaces.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final TherapistRepository therapistRepository;

    @Override
    public void deleteTherapist(Client client) {
        client.getTherapist().getClients().remove(client);
        therapistRepository.save(client.getTherapist());
        client.setTherapist(null);
        clientRepository.save(client);
    }

    @Override
    public Client setNewTherapist(Client client, Therapist therapist) {
        client.getTherapist().getClients().remove(client);
        therapistRepository.save(client.getTherapist());
        client.setTherapist(therapist);
        clientRepository.save(client);
        return client;
    }

    @Override
    public List<Reservation> findAllReservations(Client client) {
        return client.getReservations();
    }

    @Override
    public void createClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public void updateClient(Client client) throws EntityNotFoundException {
        if (client == null) {
            throw new EntityNotFoundException("");
        } else {
            clientRepository.save(client);
        }
    }

    @Override
    public void deleteClient(Client client) throws EntityNotFoundException {
        if (client == null) {
            throw new EntityNotFoundException("");
        } else {
            clientRepository.delete(client);
        }
    }

    @Override
    public Client findById(Integer id) throws EntityNotFoundException {
        Client client = clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("net tut takih"));
        return client;
    }
}
