package com.example.mindspace;

import com.example.mindspace.api.CreateReservationResponse;
import com.example.mindspace.api.ReservationRequest;
import com.example.mindspace.exception.EntityNotFoundException;
import com.example.mindspace.model.Reservation;
import com.example.mindspace.model.Therapist;
import com.example.mindspace.model.TimeCell;
import com.example.mindspace.repository.ClientRepository;
import com.example.mindspace.repository.ReservationRepository;
import com.example.mindspace.repository.TherapistRepository;
import com.example.mindspace.repository.TimeCellRepository;
import com.example.mindspace.service.impl.ReservationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {ReservationServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class ReservationServiceImplTest {
//    @Mock
//    private ReservationRepository reservationRepository;
//    @Mock
//    private TherapistRepository therapistRepository;
//    @Mock
//    private ClientRepository clientRepository;
//    @Mock
//    private TimeCellRepository timeCellRepository;
//
//    @InjectMocks
//    private ReservationServiceImpl reservationService;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testCreateReservation_TherapistNotFound() {
//        ReservationRequest reservationRequest = new ReservationRequest(1, 1, 1);
//
//        when(therapistRepository.findById(1)).thenReturn(Optional.empty());
//
//        assertThrows(EntityNotFoundException.class, () -> reservationService.createReservation(reservationRequest));
//    }
//
//    @Test
//    public void testCreateReservation_TimeslotNotAvailable() {
//        ReservationRequest reservationRequest = new ReservationRequest(1, 1, 1);
//        Reservation reservation = new Reservation();
//        TimeCell timeCell = new TimeCell();
//
//        when(therapistRepository.findById(1)).thenReturn(Optional.of(new Therapist()));
//        when(timeCellRepository.findById(1)).thenReturn(Optional.of(timeCell));
//        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);
//
//        assertThrows(IllegalArgumentException.class, () -> reservationService.createReservation(reservationRequest));
//    }
//
//    @Test
//    public void testCreateReservation_Success() {
//        ReservationRequest reservationRequest = new ReservationRequest(1, 1, 1);
//        Reservation reservation = new Reservation();
//        TimeCell timeCell = new TimeCell();
//
//        when(therapistRepository.findById(1)).thenReturn(Optional.of(new Therapist()));
//        when(timeCellRepository.findById(1)).thenReturn(Optional.of(timeCell));
//        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);
//
//        CreateReservationResponse response = reservationService.createReservation(reservationRequest);
//
//        assertNotNull(response);
////        assertNotNull(response.getId());
//    }
}


