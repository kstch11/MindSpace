package com.example.mindspace.service.impl;

import com.example.mindspace.dao.ClientRepository;
import com.example.mindspace.dao.ReservationRepository;
import com.example.mindspace.dao.TherapistRepository;
import com.example.mindspace.exception.EntityNotFoundException;
import com.example.mindspace.model.Reservation;
import com.example.mindspace.service.interfaces.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final TherapistRepository therapistRepository;
    private final ClientRepository clientRepository;

    @Override
    public void createReservation(Reservation reservation) throws EntityNotFoundException {
        if (reservation != null) {
            reservationRepository.save(reservation);
            reservation.getTherapist().getSchedule().getTimeCells().stream()
                    .filter(timeCell -> timeCell.getTime() == reservation.getStartTime())
                    .findFirst().get().setReserved(true);
        } else {
            throw new EntityNotFoundException("loh");
        }
    }

    @Override
    public void cancelReservation(Reservation reservation) throws EntityNotFoundException {
        if (reservation != null) {
            reservation.getTherapist().getReservations().remove(reservation);
            therapistRepository.save(reservation.getTherapist());
            reservation.getMembers().stream().forEach(member -> {
                member.getReservations().remove(reservation);
                clientRepository.save(member);
            });
            reservationRepository.delete(reservation);
        } else {
            throw new EntityNotFoundException("nelzya");
        }
    }

    @Override
    public void delayReservation(Reservation reservation, LocalDateTime dateTime) throws EntityNotFoundException {
        if (reservation != null && dateTime != null) {
            reservation.setStartTime(dateTime);
            reservationRepository.save(reservation);
        } else {
            throw new EntityNotFoundException("nelzya");
        }

    }

    @Override
    public Reservation findById(Integer id) {
        return reservationRepository.findById(id).get();
    }
}
