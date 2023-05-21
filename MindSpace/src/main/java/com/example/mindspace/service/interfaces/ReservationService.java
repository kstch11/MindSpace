package com.example.mindspace.service.interfaces;

import com.example.mindspace.api.ReservationRequest;
import com.example.mindspace.exception.EntityNotFoundException;
import com.example.mindspace.model.Reservation;

import java.time.LocalDateTime;

public interface ReservationService {
    public void createReservation(ReservationRequest reservation) throws EntityNotFoundException;

    public void cancelReservation(Reservation reservation) throws EntityNotFoundException;

    public void delayReservation(Reservation reservation, LocalDateTime dateTime) throws EntityNotFoundException;

    Reservation findById(Integer id);
}
