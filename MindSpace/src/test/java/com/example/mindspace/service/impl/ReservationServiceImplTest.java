package com.example.mindspace.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.mindspace.api.ClientResponse;
import com.example.mindspace.api.LanguageResponse;
import com.example.mindspace.api.ReservationRequest;
import com.example.mindspace.api.ReservationResponse;
import com.example.mindspace.api.TherapistResponse;
import com.example.mindspace.exception.EntityNotFoundException;
import com.example.mindspace.model.Client;
import com.example.mindspace.model.Reservation;
import com.example.mindspace.model.Schedule;
import com.example.mindspace.model.SpokenLanguage;
import com.example.mindspace.model.Therapist;
import com.example.mindspace.model.TimeCell;
import com.example.mindspace.model.User;
import com.example.mindspace.repository.ClientRepository;
import com.example.mindspace.repository.ReservationRepository;
import com.example.mindspace.repository.TherapistRepository;
import com.example.mindspace.repository.TimeCellRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ReservationServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ReservationServiceImplTest {
    @MockBean
    private ClientRepository clientRepository;

    @MockBean
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationServiceImpl reservationServiceImpl;

    @MockBean
    private TherapistRepository therapistRepository;

    @MockBean
    private TimeCellRepository timeCellRepository;

    /**
     * Method under test:
     * {@link ReservationServiceImpl#createReservation(ReservationRequest)}
     */
    @Test
    void testCreateReservation() {
        // Arrange
        Schedule schedule = new Schedule();
        schedule.setAvailableTimeCells(new ArrayList<>());
        schedule.setId(1);
        schedule.setTherapist(new Therapist());

        Therapist therapist = new Therapist();
        therapist.setApproved(true);
        therapist.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist.setCertificates(new ArrayList<>());
        therapist.setClients(new ArrayList<>());
        therapist.setDescription("The characteristics of someone or something");
        therapist.setEducation("Education");
        therapist.setEmail("jane.doe@example.org");
        therapist.setGender(User.Gender.MALE);
        therapist.setId(1);
        therapist.setLanguages(new ArrayList<>());
        therapist.setName("Name");
        therapist.setPassword("iloveyou");
        therapist.setPersonalTherapy("Personal Therapy");
        therapist.setPhoneNumber("6625550144");
        therapist.setPhoto("alice.liddell@example.org");
        therapist.setRegistrationFinished(true);
        therapist.setReservations(new ArrayList<>());
        therapist.setSchedule(schedule);
        therapist.setSurname("Doe");
        therapist.setThemes(new ArrayList<>());
        therapist.setTherapeuticCommunity("Therapeutic Community");
        therapist.setUserType(User.UserType.THERAPIST);

        Schedule schedule2 = new Schedule();
        schedule2.setAvailableTimeCells(new ArrayList<>());
        schedule2.setId(1);
        schedule2.setTherapist(therapist);

        Therapist therapist2 = new Therapist();
        therapist2.setApproved(true);
        therapist2.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist2.setCertificates(new ArrayList<>());
        therapist2.setClients(new ArrayList<>());
        therapist2.setDescription("The characteristics of someone or something");
        therapist2.setEducation("Education");
        therapist2.setEmail("jane.doe@example.org");
        therapist2.setGender(User.Gender.MALE);
        therapist2.setId(1);
        therapist2.setLanguages(new ArrayList<>());
        therapist2.setName("Name");
        therapist2.setPassword("iloveyou");
        therapist2.setPersonalTherapy("Personal Therapy");
        therapist2.setPhoneNumber("6625550144");
        therapist2.setPhoto("alice.liddell@example.org");
        therapist2.setRegistrationFinished(true);
        therapist2.setReservations(new ArrayList<>());
        therapist2.setSchedule(schedule2);
        therapist2.setSurname("Doe");
        therapist2.setThemes(new ArrayList<>());
        therapist2.setTherapeuticCommunity("Therapeutic Community");
        therapist2.setUserType(User.UserType.THERAPIST);
        Optional<Therapist> ofResult = Optional.of(therapist2);
        when(therapistRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> reservationServiceImpl.createReservation(new ReservationRequest(1, 1, 1)));
        verify(therapistRepository).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test:
     * {@link ReservationServiceImpl#createReservation(ReservationRequest)}
     */
    @Test
    void testCreateReservation2() {
        // Arrange
        Therapist therapist = new Therapist();
        therapist.setApproved(true);
        therapist.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist.setCertificates(new ArrayList<>());
        therapist.setClients(new ArrayList<>());
        therapist.setDescription("The characteristics of someone or something");
        therapist.setEducation("Requested timeslot is not available");
        therapist.setEmail("jane.doe@example.org");
        therapist.setGender(User.Gender.MALE);
        therapist.setId(1);
        therapist.setLanguages(new ArrayList<>());
        therapist.setName("Requested timeslot is not available");
        therapist.setPassword("iloveyou");
        therapist.setPersonalTherapy("Requested timeslot is not available");
        therapist.setPhoneNumber("6625550144");
        therapist.setPhoto("alice.liddell@example.org");
        therapist.setRegistrationFinished(true);
        therapist.setReservations(new ArrayList<>());
        therapist.setSchedule(new Schedule());
        therapist.setSurname("Doe");
        therapist.setThemes(new ArrayList<>());
        therapist.setTherapeuticCommunity("Requested timeslot is not available");
        therapist.setUserType(User.UserType.THERAPIST);

        Client client = new Client();
        client.setEmail("jane.doe@example.org");
        client.setGender(User.Gender.MALE);
        client.setId(1);
        client.setName("Requested timeslot is not available");
        client.setPassword("iloveyou");
        client.setPhoneNumber("6625550144");
        client.setRegistrationFinished(true);
        client.setReservations(new ArrayList<>());
        client.setSurname("Doe");
        client.setTherapist(therapist);
        client.setUserType(User.UserType.CLIENT);

        Schedule schedule = new Schedule();
        schedule.setAvailableTimeCells(new ArrayList<>());
        schedule.setId(1);
        schedule.setTherapist(new Therapist());

        Therapist therapist2 = new Therapist();
        therapist2.setApproved(true);
        therapist2.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist2.setCertificates(new ArrayList<>());
        therapist2.setClients(new ArrayList<>());
        therapist2.setDescription("The characteristics of someone or something");
        therapist2.setEducation("Requested timeslot is not available");
        therapist2.setEmail("jane.doe@example.org");
        therapist2.setGender(User.Gender.MALE);
        therapist2.setId(1);
        therapist2.setLanguages(new ArrayList<>());
        therapist2.setName("Requested timeslot is not available");
        therapist2.setPassword("iloveyou");
        therapist2.setPersonalTherapy("Requested timeslot is not available");
        therapist2.setPhoneNumber("6625550144");
        therapist2.setPhoto("alice.liddell@example.org");
        therapist2.setRegistrationFinished(true);
        therapist2.setReservations(new ArrayList<>());
        therapist2.setSchedule(schedule);
        therapist2.setSurname("Doe");
        therapist2.setThemes(new ArrayList<>());
        therapist2.setTherapeuticCommunity("Requested timeslot is not available");
        therapist2.setUserType(User.UserType.CLIENT);

        Reservation reservation = new Reservation();
        reservation.setClient(new Client());
        reservation.setId(1);
        reservation.setTherapist(new Therapist());
        reservation.setTimeCell(new TimeCell());

        Schedule schedule2 = new Schedule();
        schedule2.setAvailableTimeCells(new ArrayList<>());
        schedule2.setId(1);
        schedule2.setTherapist(new Therapist());

        TimeCell timeCell = new TimeCell();
        timeCell.setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        timeCell.setId(1);
        timeCell.setReservation(reservation);
        timeCell.setSchedule(schedule2);
        timeCell.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Reservation reservation2 = new Reservation();
        reservation2.setClient(client);
        reservation2.setId(1);
        reservation2.setTherapist(therapist2);
        reservation2.setTimeCell(timeCell);

        Schedule schedule3 = new Schedule();
        schedule3.setAvailableTimeCells(new ArrayList<>());
        schedule3.setId(1);
        schedule3.setTherapist(new Therapist());

        Therapist therapist3 = new Therapist();
        therapist3.setApproved(true);
        therapist3.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist3.setCertificates(new ArrayList<>());
        therapist3.setClients(new ArrayList<>());
        therapist3.setDescription("The characteristics of someone or something");
        therapist3.setEducation("Requested timeslot is not available");
        therapist3.setEmail("jane.doe@example.org");
        therapist3.setGender(User.Gender.MALE);
        therapist3.setId(1);
        therapist3.setLanguages(new ArrayList<>());
        therapist3.setName("Requested timeslot is not available");
        therapist3.setPassword("iloveyou");
        therapist3.setPersonalTherapy("Requested timeslot is not available");
        therapist3.setPhoneNumber("6625550144");
        therapist3.setPhoto("alice.liddell@example.org");
        therapist3.setRegistrationFinished(true);
        therapist3.setReservations(new ArrayList<>());
        therapist3.setSchedule(schedule3);
        therapist3.setSurname("Doe");
        therapist3.setThemes(new ArrayList<>());
        therapist3.setTherapeuticCommunity("Requested timeslot is not available");
        therapist3.setUserType(User.UserType.CLIENT);

        Schedule schedule4 = new Schedule();
        schedule4.setAvailableTimeCells(new ArrayList<>());
        schedule4.setId(1);
        schedule4.setTherapist(therapist3);

        TimeCell timeCell2 = new TimeCell();
        timeCell2.setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        timeCell2.setId(1);
        timeCell2.setReservation(reservation2);
        timeCell2.setSchedule(schedule4);
        timeCell2.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        ArrayList<TimeCell> availableTimeCells = new ArrayList<>();
        availableTimeCells.add(timeCell2);

        Schedule schedule5 = new Schedule();
        schedule5.setAvailableTimeCells(new ArrayList<>());
        schedule5.setId(1);
        schedule5.setTherapist(new Therapist());

        Therapist therapist4 = new Therapist();
        therapist4.setApproved(true);
        therapist4.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist4.setCertificates(new ArrayList<>());
        therapist4.setClients(new ArrayList<>());
        therapist4.setDescription("The characteristics of someone or something");
        therapist4.setEducation("Education");
        therapist4.setEmail("jane.doe@example.org");
        therapist4.setGender(User.Gender.MALE);
        therapist4.setId(1);
        therapist4.setLanguages(new ArrayList<>());
        therapist4.setName("Name");
        therapist4.setPassword("iloveyou");
        therapist4.setPersonalTherapy("Personal Therapy");
        therapist4.setPhoneNumber("6625550144");
        therapist4.setPhoto("alice.liddell@example.org");
        therapist4.setRegistrationFinished(true);
        therapist4.setReservations(new ArrayList<>());
        therapist4.setSchedule(schedule5);
        therapist4.setSurname("Doe");
        therapist4.setThemes(new ArrayList<>());
        therapist4.setTherapeuticCommunity("Therapeutic Community");
        therapist4.setUserType(User.UserType.CLIENT);

        Schedule schedule6 = new Schedule();
        schedule6.setAvailableTimeCells(availableTimeCells);
        schedule6.setId(1);
        schedule6.setTherapist(therapist4);

        Therapist therapist5 = new Therapist();
        therapist5.setApproved(true);
        therapist5.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist5.setCertificates(new ArrayList<>());
        therapist5.setClients(new ArrayList<>());
        therapist5.setDescription("The characteristics of someone or something");
        therapist5.setEducation("Education");
        therapist5.setEmail("jane.doe@example.org");
        therapist5.setGender(User.Gender.MALE);
        therapist5.setId(1);
        therapist5.setLanguages(new ArrayList<>());
        therapist5.setName("Name");
        therapist5.setPassword("iloveyou");
        therapist5.setPersonalTherapy("Personal Therapy");
        therapist5.setPhoneNumber("6625550144");
        therapist5.setPhoto("alice.liddell@example.org");
        therapist5.setRegistrationFinished(true);
        therapist5.setReservations(new ArrayList<>());
        therapist5.setSchedule(schedule6);
        therapist5.setSurname("Doe");
        therapist5.setThemes(new ArrayList<>());
        therapist5.setTherapeuticCommunity("Therapeutic Community");
        therapist5.setUserType(User.UserType.CLIENT);
        Optional<Therapist> ofResult = Optional.of(therapist5);
        when(therapistRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> reservationServiceImpl.createReservation(new ReservationRequest(1, 1, 1)));
        verify(therapistRepository).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test:
     * {@link ReservationServiceImpl#createReservation(ReservationRequest)}
     */
    @Test
    void testCreateReservation3() {
        // Arrange
        Therapist therapist = new Therapist();
        therapist.setApproved(true);
        therapist.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist.setCertificates(new ArrayList<>());
        therapist.setClients(new ArrayList<>());
        therapist.setDescription("The characteristics of someone or something");
        therapist.setEducation("Requested timeslot is not available");
        therapist.setEmail("jane.doe@example.org");
        therapist.setGender(User.Gender.MALE);
        therapist.setId(1);
        therapist.setLanguages(new ArrayList<>());
        therapist.setName("Requested timeslot is not available");
        therapist.setPassword("iloveyou");
        therapist.setPersonalTherapy("Requested timeslot is not available");
        therapist.setPhoneNumber("6625550144");
        therapist.setPhoto("alice.liddell@example.org");
        therapist.setRegistrationFinished(true);
        therapist.setReservations(new ArrayList<>());
        therapist.setSchedule(new Schedule());
        therapist.setSurname("Doe");
        therapist.setThemes(new ArrayList<>());
        therapist.setTherapeuticCommunity("Requested timeslot is not available");
        therapist.setUserType(User.UserType.THERAPIST);

        Client client = new Client();
        client.setEmail("jane.doe@example.org");
        client.setGender(User.Gender.MALE);
        client.setId(1);
        client.setName("Requested timeslot is not available");
        client.setPassword("iloveyou");
        client.setPhoneNumber("6625550144");
        client.setRegistrationFinished(true);
        client.setReservations(new ArrayList<>());
        client.setSurname("Doe");
        client.setTherapist(therapist);
        client.setUserType(User.UserType.CLIENT);

        Schedule schedule = new Schedule();
        schedule.setAvailableTimeCells(new ArrayList<>());
        schedule.setId(1);
        schedule.setTherapist(new Therapist());

        Therapist therapist2 = new Therapist();
        therapist2.setApproved(true);
        therapist2.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist2.setCertificates(new ArrayList<>());
        therapist2.setClients(new ArrayList<>());
        therapist2.setDescription("The characteristics of someone or something");
        therapist2.setEducation("Requested timeslot is not available");
        therapist2.setEmail("jane.doe@example.org");
        therapist2.setGender(User.Gender.MALE);
        therapist2.setId(1);
        therapist2.setLanguages(new ArrayList<>());
        therapist2.setName("Requested timeslot is not available");
        therapist2.setPassword("iloveyou");
        therapist2.setPersonalTherapy("Requested timeslot is not available");
        therapist2.setPhoneNumber("6625550144");
        therapist2.setPhoto("alice.liddell@example.org");
        therapist2.setRegistrationFinished(true);
        therapist2.setReservations(new ArrayList<>());
        therapist2.setSchedule(schedule);
        therapist2.setSurname("Doe");
        therapist2.setThemes(new ArrayList<>());
        therapist2.setTherapeuticCommunity("Requested timeslot is not available");
        therapist2.setUserType(User.UserType.THERAPIST);

        Reservation reservation = new Reservation();
        reservation.setClient(new Client());
        reservation.setId(1);
        reservation.setTherapist(new Therapist());
        reservation.setTimeCell(new TimeCell());

        Schedule schedule2 = new Schedule();
        schedule2.setAvailableTimeCells(new ArrayList<>());
        schedule2.setId(1);
        schedule2.setTherapist(new Therapist());

        TimeCell timeCell = new TimeCell();
        timeCell.setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        timeCell.setId(1);
        timeCell.setReservation(reservation);
        timeCell.setSchedule(schedule2);
        timeCell.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Reservation reservation2 = new Reservation();
        reservation2.setClient(client);
        reservation2.setId(1);
        reservation2.setTherapist(therapist2);
        reservation2.setTimeCell(timeCell);

        Schedule schedule3 = new Schedule();
        schedule3.setAvailableTimeCells(new ArrayList<>());
        schedule3.setId(1);
        schedule3.setTherapist(new Therapist());

        Therapist therapist3 = new Therapist();
        therapist3.setApproved(true);
        therapist3.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist3.setCertificates(new ArrayList<>());
        therapist3.setClients(new ArrayList<>());
        therapist3.setDescription("The characteristics of someone or something");
        therapist3.setEducation("Requested timeslot is not available");
        therapist3.setEmail("jane.doe@example.org");
        therapist3.setGender(User.Gender.MALE);
        therapist3.setId(1);
        therapist3.setLanguages(new ArrayList<>());
        therapist3.setName("Requested timeslot is not available");
        therapist3.setPassword("iloveyou");
        therapist3.setPersonalTherapy("Requested timeslot is not available");
        therapist3.setPhoneNumber("6625550144");
        therapist3.setPhoto("alice.liddell@example.org");
        therapist3.setRegistrationFinished(true);
        therapist3.setReservations(new ArrayList<>());
        therapist3.setSchedule(schedule3);
        therapist3.setSurname("Doe");
        therapist3.setThemes(new ArrayList<>());
        therapist3.setTherapeuticCommunity("Requested timeslot is not available");
        therapist3.setUserType(User.UserType.THERAPIST);

        Schedule schedule4 = new Schedule();
        schedule4.setAvailableTimeCells(new ArrayList<>());
        schedule4.setId(1);
        schedule4.setTherapist(therapist3);

        TimeCell timeCell2 = new TimeCell();
        timeCell2.setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        timeCell2.setId(1);
        timeCell2.setReservation(reservation2);
        timeCell2.setSchedule(schedule4);
        timeCell2.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Therapist therapist4 = new Therapist();
        therapist4.setApproved(false);
        therapist4.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist4.setCertificates(new ArrayList<>());
        therapist4.setClients(new ArrayList<>());
        therapist4.setDescription("Requested timeslot is not available");
        therapist4.setEducation("Education");
        therapist4.setEmail("john.smith@example.org");
        therapist4.setGender(User.Gender.FEMALE);
        therapist4.setId(2);
        therapist4.setLanguages(new ArrayList<>());
        therapist4.setName("Name");
        therapist4.setPassword("Requested timeslot is not available");
        therapist4.setPersonalTherapy("Personal Therapy");
        therapist4.setPhoneNumber("8605550118");
        therapist4.setPhoto("Requested timeslot is not available");
        therapist4.setRegistrationFinished(false);
        therapist4.setReservations(new ArrayList<>());
        therapist4.setSchedule(new Schedule());
        therapist4.setSurname("Requested timeslot is not available");
        therapist4.setThemes(new ArrayList<>());
        therapist4.setTherapeuticCommunity("Therapeutic Community");
        therapist4.setUserType(User.UserType.THERAPIST);

        Client client2 = new Client();
        client2.setEmail("john.smith@example.org");
        client2.setGender(User.Gender.FEMALE);
        client2.setId(2);
        client2.setName("Name");
        client2.setPassword("Requested timeslot is not available");
        client2.setPhoneNumber("8605550118");
        client2.setRegistrationFinished(false);
        client2.setReservations(new ArrayList<>());
        client2.setSurname("Requested timeslot is not available");
        client2.setTherapist(therapist4);
        client2.setUserType(User.UserType.CLIENT);

        Schedule schedule5 = new Schedule();
        schedule5.setAvailableTimeCells(new ArrayList<>());
        schedule5.setId(2);
        schedule5.setTherapist(new Therapist());

        Therapist therapist5 = new Therapist();
        therapist5.setApproved(false);
        therapist5.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist5.setCertificates(new ArrayList<>());
        therapist5.setClients(new ArrayList<>());
        therapist5.setDescription("Requested timeslot is not available");
        therapist5.setEducation("Education");
        therapist5.setEmail("john.smith@example.org");
        therapist5.setGender(User.Gender.FEMALE);
        therapist5.setId(2);
        therapist5.setLanguages(new ArrayList<>());
        therapist5.setName("Name");
        therapist5.setPassword("Requested timeslot is not available");
        therapist5.setPersonalTherapy("Personal Therapy");
        therapist5.setPhoneNumber("8605550118");
        therapist5.setPhoto("Requested timeslot is not available");
        therapist5.setRegistrationFinished(false);
        therapist5.setReservations(new ArrayList<>());
        therapist5.setSchedule(schedule5);
        therapist5.setSurname("Requested timeslot is not available");
        therapist5.setThemes(new ArrayList<>());
        therapist5.setTherapeuticCommunity("Therapeutic Community");
        therapist5.setUserType(User.UserType.THERAPIST);

        Reservation reservation3 = new Reservation();
        reservation3.setClient(new Client());
        reservation3.setId(2);
        reservation3.setTherapist(new Therapist());
        reservation3.setTimeCell(new TimeCell());

        Schedule schedule6 = new Schedule();
        schedule6.setAvailableTimeCells(new ArrayList<>());
        schedule6.setId(2);
        schedule6.setTherapist(new Therapist());

        TimeCell timeCell3 = new TimeCell();
        timeCell3.setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        timeCell3.setId(2);
        timeCell3.setReservation(reservation3);
        timeCell3.setSchedule(schedule6);
        timeCell3.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Reservation reservation4 = new Reservation();
        reservation4.setClient(client2);
        reservation4.setId(2);
        reservation4.setTherapist(therapist5);
        reservation4.setTimeCell(timeCell3);

        Schedule schedule7 = new Schedule();
        schedule7.setAvailableTimeCells(new ArrayList<>());
        schedule7.setId(2);
        schedule7.setTherapist(new Therapist());

        Therapist therapist6 = new Therapist();
        therapist6.setApproved(false);
        therapist6.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist6.setCertificates(new ArrayList<>());
        therapist6.setClients(new ArrayList<>());
        therapist6.setDescription("Requested timeslot is not available");
        therapist6.setEducation("Education");
        therapist6.setEmail("john.smith@example.org");
        therapist6.setGender(User.Gender.FEMALE);
        therapist6.setId(2);
        therapist6.setLanguages(new ArrayList<>());
        therapist6.setName("Name");
        therapist6.setPassword("Requested timeslot is not available");
        therapist6.setPersonalTherapy("Personal Therapy");
        therapist6.setPhoneNumber("8605550118");
        therapist6.setPhoto("Requested timeslot is not available");
        therapist6.setRegistrationFinished(false);
        therapist6.setReservations(new ArrayList<>());
        therapist6.setSchedule(schedule7);
        therapist6.setSurname("Requested timeslot is not available");
        therapist6.setThemes(new ArrayList<>());
        therapist6.setTherapeuticCommunity("Therapeutic Community");
        therapist6.setUserType(User.UserType.THERAPIST);

        Schedule schedule8 = new Schedule();
        schedule8.setAvailableTimeCells(new ArrayList<>());
        schedule8.setId(2);
        schedule8.setTherapist(therapist6);

        TimeCell timeCell4 = new TimeCell();
        timeCell4.setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        timeCell4.setId(2);
        timeCell4.setReservation(reservation4);
        timeCell4.setSchedule(schedule8);
        timeCell4.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        ArrayList<TimeCell> availableTimeCells = new ArrayList<>();
        availableTimeCells.add(timeCell4);
        availableTimeCells.add(timeCell2);

        Schedule schedule9 = new Schedule();
        schedule9.setAvailableTimeCells(new ArrayList<>());
        schedule9.setId(1);
        schedule9.setTherapist(new Therapist());

        Therapist therapist7 = new Therapist();
        therapist7.setApproved(true);
        therapist7.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist7.setCertificates(new ArrayList<>());
        therapist7.setClients(new ArrayList<>());
        therapist7.setDescription("The characteristics of someone or something");
        therapist7.setEducation("Education");
        therapist7.setEmail("jane.doe@example.org");
        therapist7.setGender(User.Gender.MALE);
        therapist7.setId(1);
        therapist7.setLanguages(new ArrayList<>());
        therapist7.setName("Name");
        therapist7.setPassword("iloveyou");
        therapist7.setPersonalTherapy("Personal Therapy");
        therapist7.setPhoneNumber("6625550144");
        therapist7.setPhoto("alice.liddell@example.org");
        therapist7.setRegistrationFinished(true);
        therapist7.setReservations(new ArrayList<>());
        therapist7.setSchedule(schedule9);
        therapist7.setSurname("Doe");
        therapist7.setThemes(new ArrayList<>());
        therapist7.setTherapeuticCommunity("Therapeutic Community");
        therapist7.setUserType(User.UserType.THERAPIST);

        Schedule schedule10 = new Schedule();
        schedule10.setAvailableTimeCells(availableTimeCells);
        schedule10.setId(1);
        schedule10.setTherapist(therapist7);

        Therapist therapist8 = new Therapist();
        therapist8.setApproved(true);
        therapist8.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist8.setCertificates(new ArrayList<>());
        therapist8.setClients(new ArrayList<>());
        therapist8.setDescription("The characteristics of someone or something");
        therapist8.setEducation("Education");
        therapist8.setEmail("jane.doe@example.org");
        therapist8.setGender(User.Gender.MALE);
        therapist8.setId(1);
        therapist8.setLanguages(new ArrayList<>());
        therapist8.setName("Name");
        therapist8.setPassword("iloveyou");
        therapist8.setPersonalTherapy("Personal Therapy");
        therapist8.setPhoneNumber("6625550144");
        therapist8.setPhoto("alice.liddell@example.org");
        therapist8.setRegistrationFinished(true);
        therapist8.setReservations(new ArrayList<>());
        therapist8.setSchedule(schedule10);
        therapist8.setSurname("Doe");
        therapist8.setThemes(new ArrayList<>());
        therapist8.setTherapeuticCommunity("Therapeutic Community");
        therapist8.setUserType(User.UserType.THERAPIST);
        Optional<Therapist> ofResult = Optional.of(therapist8);
        when(therapistRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> reservationServiceImpl.createReservation(new ReservationRequest(1, 1, 1)));
        verify(therapistRepository).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test:
     * {@link ReservationServiceImpl#createReservation(ReservationRequest)}
     */
    @Test
    void testCreateReservation4() {
        // Arrange
        Optional<Therapist> emptyResult = Optional.empty();
        when(therapistRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(EntityNotFoundException.class,
                () -> reservationServiceImpl.createReservation(new ReservationRequest(1, 1, 1)));
        verify(therapistRepository).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link ReservationServiceImpl#cancelReservation(Integer)}
     */
    @Test
    void testCancelReservation() {
        // Arrange
        Schedule schedule = new Schedule();
        schedule.setAvailableTimeCells(new ArrayList<>());
        schedule.setId(1);
        schedule.setTherapist(new Therapist());

        Therapist therapist = new Therapist();
        therapist.setApproved(true);
        therapist.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist.setCertificates(new ArrayList<>());
        therapist.setClients(new ArrayList<>());
        therapist.setDescription("The characteristics of someone or something");
        therapist.setEducation("Education");
        therapist.setEmail("jane.doe@example.org");
        therapist.setGender(User.Gender.MALE);
        therapist.setId(1);
        therapist.setLanguages(new ArrayList<>());
        therapist.setName("Name");
        therapist.setPassword("iloveyou");
        therapist.setPersonalTherapy("Personal Therapy");
        therapist.setPhoneNumber("6625550144");
        therapist.setPhoto("alice.liddell@example.org");
        therapist.setRegistrationFinished(true);
        therapist.setReservations(new ArrayList<>());
        therapist.setSchedule(schedule);
        therapist.setSurname("Doe");
        therapist.setThemes(new ArrayList<>());
        therapist.setTherapeuticCommunity("Therapeutic Community");
        therapist.setUserType(User.UserType.CLIENT);

        Client client = new Client();
        client.setEmail("jane.doe@example.org");
        client.setGender(User.Gender.MALE);
        client.setId(1);
        client.setName("Name");
        client.setPassword("iloveyou");
        client.setPhoneNumber("6625550144");
        client.setRegistrationFinished(true);
        client.setReservations(new ArrayList<>());
        client.setSurname("Doe");
        client.setTherapist(therapist);
        client.setUserType(User.UserType.CLIENT);

        Therapist therapist2 = new Therapist();
        therapist2.setApproved(true);
        therapist2.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist2.setCertificates(new ArrayList<>());
        therapist2.setClients(new ArrayList<>());
        therapist2.setDescription("The characteristics of someone or something");
        therapist2.setEducation("Education");
        therapist2.setEmail("jane.doe@example.org");
        therapist2.setGender(User.Gender.MALE);
        therapist2.setId(1);
        therapist2.setLanguages(new ArrayList<>());
        therapist2.setName("Name");
        therapist2.setPassword("iloveyou");
        therapist2.setPersonalTherapy("Personal Therapy");
        therapist2.setPhoneNumber("6625550144");
        therapist2.setPhoto("alice.liddell@example.org");
        therapist2.setRegistrationFinished(true);
        therapist2.setReservations(new ArrayList<>());
        therapist2.setSchedule(new Schedule());
        therapist2.setSurname("Doe");
        therapist2.setThemes(new ArrayList<>());
        therapist2.setTherapeuticCommunity("Therapeutic Community");
        therapist2.setUserType(User.UserType.CLIENT);

        Schedule schedule2 = new Schedule();
        schedule2.setAvailableTimeCells(new ArrayList<>());
        schedule2.setId(1);
        schedule2.setTherapist(therapist2);

        Therapist therapist3 = new Therapist();
        therapist3.setApproved(true);
        therapist3.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist3.setCertificates(new ArrayList<>());
        therapist3.setClients(new ArrayList<>());
        therapist3.setDescription("The characteristics of someone or something");
        therapist3.setEducation("Education");
        therapist3.setEmail("jane.doe@example.org");
        therapist3.setGender(User.Gender.MALE);
        therapist3.setId(1);
        therapist3.setLanguages(new ArrayList<>());
        therapist3.setName("Name");
        therapist3.setPassword("iloveyou");
        therapist3.setPersonalTherapy("Personal Therapy");
        therapist3.setPhoneNumber("6625550144");
        therapist3.setPhoto("alice.liddell@example.org");
        therapist3.setRegistrationFinished(true);
        therapist3.setReservations(new ArrayList<>());
        therapist3.setSchedule(schedule2);
        therapist3.setSurname("Doe");
        therapist3.setThemes(new ArrayList<>());
        therapist3.setTherapeuticCommunity("Therapeutic Community");
        therapist3.setUserType(User.UserType.CLIENT);

        Client client2 = new Client();
        client2.setEmail("jane.doe@example.org");
        client2.setGender(User.Gender.MALE);
        client2.setId(1);
        client2.setName("Name");
        client2.setPassword("iloveyou");
        client2.setPhoneNumber("6625550144");
        client2.setRegistrationFinished(true);
        client2.setReservations(new ArrayList<>());
        client2.setSurname("Doe");
        client2.setTherapist(new Therapist());
        client2.setUserType(User.UserType.CLIENT);

        Therapist therapist4 = new Therapist();
        therapist4.setApproved(true);
        therapist4.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist4.setCertificates(new ArrayList<>());
        therapist4.setClients(new ArrayList<>());
        therapist4.setDescription("The characteristics of someone or something");
        therapist4.setEducation("Education");
        therapist4.setEmail("jane.doe@example.org");
        therapist4.setGender(User.Gender.MALE);
        therapist4.setId(1);
        therapist4.setLanguages(new ArrayList<>());
        therapist4.setName("Name");
        therapist4.setPassword("iloveyou");
        therapist4.setPersonalTherapy("Personal Therapy");
        therapist4.setPhoneNumber("6625550144");
        therapist4.setPhoto("alice.liddell@example.org");
        therapist4.setRegistrationFinished(true);
        therapist4.setReservations(new ArrayList<>());
        therapist4.setSchedule(new Schedule());
        therapist4.setSurname("Doe");
        therapist4.setThemes(new ArrayList<>());
        therapist4.setTherapeuticCommunity("Therapeutic Community");
        therapist4.setUserType(User.UserType.CLIENT);

        TimeCell timeCell = new TimeCell();
        timeCell.setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        timeCell.setId(1);
        timeCell.setReservation(new Reservation());
        timeCell.setSchedule(new Schedule());
        timeCell.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Reservation reservation = new Reservation();
        reservation.setClient(client2);
        reservation.setId(1);
        reservation.setTherapist(therapist4);
        reservation.setTimeCell(timeCell);

        Therapist therapist5 = new Therapist();
        therapist5.setApproved(true);
        therapist5.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist5.setCertificates(new ArrayList<>());
        therapist5.setClients(new ArrayList<>());
        therapist5.setDescription("The characteristics of someone or something");
        therapist5.setEducation("Education");
        therapist5.setEmail("jane.doe@example.org");
        therapist5.setGender(User.Gender.MALE);
        therapist5.setId(1);
        therapist5.setLanguages(new ArrayList<>());
        therapist5.setName("Name");
        therapist5.setPassword("iloveyou");
        therapist5.setPersonalTherapy("Personal Therapy");
        therapist5.setPhoneNumber("6625550144");
        therapist5.setPhoto("alice.liddell@example.org");
        therapist5.setRegistrationFinished(true);
        therapist5.setReservations(new ArrayList<>());
        therapist5.setSchedule(new Schedule());
        therapist5.setSurname("Doe");
        therapist5.setThemes(new ArrayList<>());
        therapist5.setTherapeuticCommunity("Therapeutic Community");
        therapist5.setUserType(User.UserType.CLIENT);

        Schedule schedule3 = new Schedule();
        schedule3.setAvailableTimeCells(new ArrayList<>());
        schedule3.setId(1);
        schedule3.setTherapist(therapist5);

        TimeCell timeCell2 = new TimeCell();
        timeCell2.setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        timeCell2.setId(1);
        timeCell2.setReservation(reservation);
        timeCell2.setSchedule(schedule3);
        timeCell2.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Reservation reservation2 = new Reservation();
        reservation2.setClient(client);
        reservation2.setId(1);
        reservation2.setTherapist(therapist3);
        reservation2.setTimeCell(timeCell2);
        Optional<Reservation> ofResult = Optional.of(reservation2);
        doNothing().when(reservationRepository).delete(Mockito.<Reservation>any());
        when(reservationRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        Schedule schedule4 = new Schedule();
        schedule4.setAvailableTimeCells(new ArrayList<>());
        schedule4.setId(1);
        schedule4.setTherapist(new Therapist());

        Therapist therapist6 = new Therapist();
        therapist6.setApproved(true);
        therapist6.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist6.setCertificates(new ArrayList<>());
        therapist6.setClients(new ArrayList<>());
        therapist6.setDescription("The characteristics of someone or something");
        therapist6.setEducation("Education");
        therapist6.setEmail("jane.doe@example.org");
        therapist6.setGender(User.Gender.MALE);
        therapist6.setId(1);
        therapist6.setLanguages(new ArrayList<>());
        therapist6.setName("Name");
        therapist6.setPassword("iloveyou");
        therapist6.setPersonalTherapy("Personal Therapy");
        therapist6.setPhoneNumber("6625550144");
        therapist6.setPhoto("alice.liddell@example.org");
        therapist6.setRegistrationFinished(true);
        therapist6.setReservations(new ArrayList<>());
        therapist6.setSchedule(schedule4);
        therapist6.setSurname("Doe");
        therapist6.setThemes(new ArrayList<>());
        therapist6.setTherapeuticCommunity("Therapeutic Community");
        therapist6.setUserType(User.UserType.CLIENT);

        Client client3 = new Client();
        client3.setEmail("jane.doe@example.org");
        client3.setGender(User.Gender.MALE);
        client3.setId(1);
        client3.setName("Name");
        client3.setPassword("iloveyou");
        client3.setPhoneNumber("6625550144");
        client3.setRegistrationFinished(true);
        client3.setReservations(new ArrayList<>());
        client3.setSurname("Doe");
        client3.setTherapist(therapist6);
        client3.setUserType(User.UserType.CLIENT);

        Therapist therapist7 = new Therapist();
        therapist7.setApproved(true);
        therapist7.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist7.setCertificates(new ArrayList<>());
        therapist7.setClients(new ArrayList<>());
        therapist7.setDescription("The characteristics of someone or something");
        therapist7.setEducation("Education");
        therapist7.setEmail("jane.doe@example.org");
        therapist7.setGender(User.Gender.MALE);
        therapist7.setId(1);
        therapist7.setLanguages(new ArrayList<>());
        therapist7.setName("Name");
        therapist7.setPassword("iloveyou");
        therapist7.setPersonalTherapy("Personal Therapy");
        therapist7.setPhoneNumber("6625550144");
        therapist7.setPhoto("alice.liddell@example.org");
        therapist7.setRegistrationFinished(true);
        therapist7.setReservations(new ArrayList<>());
        therapist7.setSchedule(new Schedule());
        therapist7.setSurname("Doe");
        therapist7.setThemes(new ArrayList<>());
        therapist7.setTherapeuticCommunity("Therapeutic Community");
        therapist7.setUserType(User.UserType.CLIENT);

        Schedule schedule5 = new Schedule();
        schedule5.setAvailableTimeCells(new ArrayList<>());
        schedule5.setId(1);
        schedule5.setTherapist(therapist7);

        Therapist therapist8 = new Therapist();
        therapist8.setApproved(true);
        therapist8.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist8.setCertificates(new ArrayList<>());
        therapist8.setClients(new ArrayList<>());
        therapist8.setDescription("The characteristics of someone or something");
        therapist8.setEducation("Education");
        therapist8.setEmail("jane.doe@example.org");
        therapist8.setGender(User.Gender.MALE);
        therapist8.setId(1);
        therapist8.setLanguages(new ArrayList<>());
        therapist8.setName("Name");
        therapist8.setPassword("iloveyou");
        therapist8.setPersonalTherapy("Personal Therapy");
        therapist8.setPhoneNumber("6625550144");
        therapist8.setPhoto("alice.liddell@example.org");
        therapist8.setRegistrationFinished(true);
        therapist8.setReservations(new ArrayList<>());
        therapist8.setSchedule(schedule5);
        therapist8.setSurname("Doe");
        therapist8.setThemes(new ArrayList<>());
        therapist8.setTherapeuticCommunity("Therapeutic Community");
        therapist8.setUserType(User.UserType.CLIENT);

        Client client4 = new Client();
        client4.setEmail("jane.doe@example.org");
        client4.setGender(User.Gender.MALE);
        client4.setId(1);
        client4.setName("Name");
        client4.setPassword("iloveyou");
        client4.setPhoneNumber("6625550144");
        client4.setRegistrationFinished(true);
        client4.setReservations(new ArrayList<>());
        client4.setSurname("Doe");
        client4.setTherapist(new Therapist());
        client4.setUserType(User.UserType.CLIENT);

        Therapist therapist9 = new Therapist();
        therapist9.setApproved(true);
        therapist9.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist9.setCertificates(new ArrayList<>());
        therapist9.setClients(new ArrayList<>());
        therapist9.setDescription("The characteristics of someone or something");
        therapist9.setEducation("Education");
        therapist9.setEmail("jane.doe@example.org");
        therapist9.setGender(User.Gender.MALE);
        therapist9.setId(1);
        therapist9.setLanguages(new ArrayList<>());
        therapist9.setName("Name");
        therapist9.setPassword("iloveyou");
        therapist9.setPersonalTherapy("Personal Therapy");
        therapist9.setPhoneNumber("6625550144");
        therapist9.setPhoto("alice.liddell@example.org");
        therapist9.setRegistrationFinished(true);
        therapist9.setReservations(new ArrayList<>());
        therapist9.setSchedule(new Schedule());
        therapist9.setSurname("Doe");
        therapist9.setThemes(new ArrayList<>());
        therapist9.setTherapeuticCommunity("Therapeutic Community");
        therapist9.setUserType(User.UserType.CLIENT);

        TimeCell timeCell3 = new TimeCell();
        timeCell3.setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        timeCell3.setId(1);
        timeCell3.setReservation(new Reservation());
        timeCell3.setSchedule(new Schedule());
        timeCell3.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Reservation reservation3 = new Reservation();
        reservation3.setClient(client4);
        reservation3.setId(1);
        reservation3.setTherapist(therapist9);
        reservation3.setTimeCell(timeCell3);

        Therapist therapist10 = new Therapist();
        therapist10.setApproved(true);
        therapist10.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist10.setCertificates(new ArrayList<>());
        therapist10.setClients(new ArrayList<>());
        therapist10.setDescription("The characteristics of someone or something");
        therapist10.setEducation("Education");
        therapist10.setEmail("jane.doe@example.org");
        therapist10.setGender(User.Gender.MALE);
        therapist10.setId(1);
        therapist10.setLanguages(new ArrayList<>());
        therapist10.setName("Name");
        therapist10.setPassword("iloveyou");
        therapist10.setPersonalTherapy("Personal Therapy");
        therapist10.setPhoneNumber("6625550144");
        therapist10.setPhoto("alice.liddell@example.org");
        therapist10.setRegistrationFinished(true);
        therapist10.setReservations(new ArrayList<>());
        therapist10.setSchedule(new Schedule());
        therapist10.setSurname("Doe");
        therapist10.setThemes(new ArrayList<>());
        therapist10.setTherapeuticCommunity("Therapeutic Community");
        therapist10.setUserType(User.UserType.CLIENT);

        Schedule schedule6 = new Schedule();
        schedule6.setAvailableTimeCells(new ArrayList<>());
        schedule6.setId(1);
        schedule6.setTherapist(therapist10);

        TimeCell timeCell4 = new TimeCell();
        timeCell4.setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        timeCell4.setId(1);
        timeCell4.setReservation(reservation3);
        timeCell4.setSchedule(schedule6);
        timeCell4.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Reservation reservation4 = new Reservation();
        reservation4.setClient(client3);
        reservation4.setId(1);
        reservation4.setTherapist(therapist8);
        reservation4.setTimeCell(timeCell4);

        Therapist therapist11 = new Therapist();
        therapist11.setApproved(true);
        therapist11.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist11.setCertificates(new ArrayList<>());
        therapist11.setClients(new ArrayList<>());
        therapist11.setDescription("The characteristics of someone or something");
        therapist11.setEducation("Education");
        therapist11.setEmail("jane.doe@example.org");
        therapist11.setGender(User.Gender.MALE);
        therapist11.setId(1);
        therapist11.setLanguages(new ArrayList<>());
        therapist11.setName("Name");
        therapist11.setPassword("iloveyou");
        therapist11.setPersonalTherapy("Personal Therapy");
        therapist11.setPhoneNumber("6625550144");
        therapist11.setPhoto("alice.liddell@example.org");
        therapist11.setRegistrationFinished(true);
        therapist11.setReservations(new ArrayList<>());
        therapist11.setSchedule(new Schedule());
        therapist11.setSurname("Doe");
        therapist11.setThemes(new ArrayList<>());
        therapist11.setTherapeuticCommunity("Therapeutic Community");
        therapist11.setUserType(User.UserType.CLIENT);

        Schedule schedule7 = new Schedule();
        schedule7.setAvailableTimeCells(new ArrayList<>());
        schedule7.setId(1);
        schedule7.setTherapist(therapist11);

        Therapist therapist12 = new Therapist();
        therapist12.setApproved(true);
        therapist12.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist12.setCertificates(new ArrayList<>());
        therapist12.setClients(new ArrayList<>());
        therapist12.setDescription("The characteristics of someone or something");
        therapist12.setEducation("Education");
        therapist12.setEmail("jane.doe@example.org");
        therapist12.setGender(User.Gender.MALE);
        therapist12.setId(1);
        therapist12.setLanguages(new ArrayList<>());
        therapist12.setName("Name");
        therapist12.setPassword("iloveyou");
        therapist12.setPersonalTherapy("Personal Therapy");
        therapist12.setPhoneNumber("6625550144");
        therapist12.setPhoto("alice.liddell@example.org");
        therapist12.setRegistrationFinished(true);
        therapist12.setReservations(new ArrayList<>());
        therapist12.setSchedule(schedule7);
        therapist12.setSurname("Doe");
        therapist12.setThemes(new ArrayList<>());
        therapist12.setTherapeuticCommunity("Therapeutic Community");
        therapist12.setUserType(User.UserType.CLIENT);

        Schedule schedule8 = new Schedule();
        schedule8.setAvailableTimeCells(new ArrayList<>());
        schedule8.setId(1);
        schedule8.setTherapist(therapist12);

        TimeCell timeCell5 = new TimeCell();
        timeCell5.setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        timeCell5.setId(1);
        timeCell5.setReservation(reservation4);
        timeCell5.setSchedule(schedule8);
        timeCell5.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(timeCellRepository.save(Mockito.<TimeCell>any())).thenReturn(timeCell5);

        // Act
        reservationServiceImpl.cancelReservation(1);

        // Assert
        verify(reservationRepository).delete(Mockito.<Reservation>any());
        verify(reservationRepository).findById(Mockito.<Integer>any());
        verify(timeCellRepository).save(Mockito.<TimeCell>any());
    }

    /**
     * Method under test: {@link ReservationServiceImpl#cancelReservation(Integer)}
     */
    @Test
    void testCancelReservation2() {
        // Arrange
        Schedule schedule = new Schedule();
        schedule.setAvailableTimeCells(new ArrayList<>());
        schedule.setId(1);
        schedule.setTherapist(new Therapist());

        Therapist therapist = new Therapist();
        therapist.setApproved(true);
        therapist.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist.setCertificates(new ArrayList<>());
        therapist.setClients(new ArrayList<>());
        therapist.setDescription("The characteristics of someone or something");
        therapist.setEducation("Education");
        therapist.setEmail("jane.doe@example.org");
        therapist.setGender(User.Gender.MALE);
        therapist.setId(1);
        therapist.setLanguages(new ArrayList<>());
        therapist.setName("Name");
        therapist.setPassword("iloveyou");
        therapist.setPersonalTherapy("Personal Therapy");
        therapist.setPhoneNumber("6625550144");
        therapist.setPhoto("alice.liddell@example.org");
        therapist.setRegistrationFinished(true);
        therapist.setReservations(new ArrayList<>());
        therapist.setSchedule(schedule);
        therapist.setSurname("Doe");
        therapist.setThemes(new ArrayList<>());
        therapist.setTherapeuticCommunity("Therapeutic Community");
        therapist.setUserType(User.UserType.CLIENT);

        Client client = new Client();
        client.setEmail("jane.doe@example.org");
        client.setGender(User.Gender.MALE);
        client.setId(1);
        client.setName("Name");
        client.setPassword("iloveyou");
        client.setPhoneNumber("6625550144");
        client.setRegistrationFinished(true);
        client.setReservations(new ArrayList<>());
        client.setSurname("Doe");
        client.setTherapist(therapist);
        client.setUserType(User.UserType.CLIENT);

        Therapist therapist2 = new Therapist();
        therapist2.setApproved(true);
        therapist2.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist2.setCertificates(new ArrayList<>());
        therapist2.setClients(new ArrayList<>());
        therapist2.setDescription("The characteristics of someone or something");
        therapist2.setEducation("Education");
        therapist2.setEmail("jane.doe@example.org");
        therapist2.setGender(User.Gender.MALE);
        therapist2.setId(1);
        therapist2.setLanguages(new ArrayList<>());
        therapist2.setName("Name");
        therapist2.setPassword("iloveyou");
        therapist2.setPersonalTherapy("Personal Therapy");
        therapist2.setPhoneNumber("6625550144");
        therapist2.setPhoto("alice.liddell@example.org");
        therapist2.setRegistrationFinished(true);
        therapist2.setReservations(new ArrayList<>());
        therapist2.setSchedule(new Schedule());
        therapist2.setSurname("Doe");
        therapist2.setThemes(new ArrayList<>());
        therapist2.setTherapeuticCommunity("Therapeutic Community");
        therapist2.setUserType(User.UserType.CLIENT);

        Schedule schedule2 = new Schedule();
        schedule2.setAvailableTimeCells(new ArrayList<>());
        schedule2.setId(1);
        schedule2.setTherapist(therapist2);

        Therapist therapist3 = new Therapist();
        therapist3.setApproved(true);
        therapist3.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist3.setCertificates(new ArrayList<>());
        therapist3.setClients(new ArrayList<>());
        therapist3.setDescription("The characteristics of someone or something");
        therapist3.setEducation("Education");
        therapist3.setEmail("jane.doe@example.org");
        therapist3.setGender(User.Gender.MALE);
        therapist3.setId(1);
        therapist3.setLanguages(new ArrayList<>());
        therapist3.setName("Name");
        therapist3.setPassword("iloveyou");
        therapist3.setPersonalTherapy("Personal Therapy");
        therapist3.setPhoneNumber("6625550144");
        therapist3.setPhoto("alice.liddell@example.org");
        therapist3.setRegistrationFinished(true);
        therapist3.setReservations(new ArrayList<>());
        therapist3.setSchedule(schedule2);
        therapist3.setSurname("Doe");
        therapist3.setThemes(new ArrayList<>());
        therapist3.setTherapeuticCommunity("Therapeutic Community");
        therapist3.setUserType(User.UserType.CLIENT);

        Client client2 = new Client();
        client2.setEmail("jane.doe@example.org");
        client2.setGender(User.Gender.MALE);
        client2.setId(1);
        client2.setName("Name");
        client2.setPassword("iloveyou");
        client2.setPhoneNumber("6625550144");
        client2.setRegistrationFinished(true);
        client2.setReservations(new ArrayList<>());
        client2.setSurname("Doe");
        client2.setTherapist(new Therapist());
        client2.setUserType(User.UserType.CLIENT);

        Therapist therapist4 = new Therapist();
        therapist4.setApproved(true);
        therapist4.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist4.setCertificates(new ArrayList<>());
        therapist4.setClients(new ArrayList<>());
        therapist4.setDescription("The characteristics of someone or something");
        therapist4.setEducation("Education");
        therapist4.setEmail("jane.doe@example.org");
        therapist4.setGender(User.Gender.MALE);
        therapist4.setId(1);
        therapist4.setLanguages(new ArrayList<>());
        therapist4.setName("Name");
        therapist4.setPassword("iloveyou");
        therapist4.setPersonalTherapy("Personal Therapy");
        therapist4.setPhoneNumber("6625550144");
        therapist4.setPhoto("alice.liddell@example.org");
        therapist4.setRegistrationFinished(true);
        therapist4.setReservations(new ArrayList<>());
        therapist4.setSchedule(new Schedule());
        therapist4.setSurname("Doe");
        therapist4.setThemes(new ArrayList<>());
        therapist4.setTherapeuticCommunity("Therapeutic Community");
        therapist4.setUserType(User.UserType.CLIENT);

        TimeCell timeCell = new TimeCell();
        timeCell.setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        timeCell.setId(1);
        timeCell.setReservation(new Reservation());
        timeCell.setSchedule(new Schedule());
        timeCell.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Reservation reservation = new Reservation();
        reservation.setClient(client2);
        reservation.setId(1);
        reservation.setTherapist(therapist4);
        reservation.setTimeCell(timeCell);

        Therapist therapist5 = new Therapist();
        therapist5.setApproved(true);
        therapist5.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist5.setCertificates(new ArrayList<>());
        therapist5.setClients(new ArrayList<>());
        therapist5.setDescription("The characteristics of someone or something");
        therapist5.setEducation("Education");
        therapist5.setEmail("jane.doe@example.org");
        therapist5.setGender(User.Gender.MALE);
        therapist5.setId(1);
        therapist5.setLanguages(new ArrayList<>());
        therapist5.setName("Name");
        therapist5.setPassword("iloveyou");
        therapist5.setPersonalTherapy("Personal Therapy");
        therapist5.setPhoneNumber("6625550144");
        therapist5.setPhoto("alice.liddell@example.org");
        therapist5.setRegistrationFinished(true);
        therapist5.setReservations(new ArrayList<>());
        therapist5.setSchedule(new Schedule());
        therapist5.setSurname("Doe");
        therapist5.setThemes(new ArrayList<>());
        therapist5.setTherapeuticCommunity("Therapeutic Community");
        therapist5.setUserType(User.UserType.CLIENT);

        Schedule schedule3 = new Schedule();
        schedule3.setAvailableTimeCells(new ArrayList<>());
        schedule3.setId(1);
        schedule3.setTherapist(therapist5);

        TimeCell timeCell2 = new TimeCell();
        timeCell2.setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        timeCell2.setId(1);
        timeCell2.setReservation(reservation);
        timeCell2.setSchedule(schedule3);
        timeCell2.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Reservation reservation2 = new Reservation();
        reservation2.setClient(client);
        reservation2.setId(1);
        reservation2.setTherapist(therapist3);
        reservation2.setTimeCell(timeCell2);
        Optional<Reservation> ofResult = Optional.of(reservation2);
        when(reservationRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        when(timeCellRepository.save(Mockito.<TimeCell>any())).thenThrow(new IllegalArgumentException("foo"));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> reservationServiceImpl.cancelReservation(1));
        verify(reservationRepository).findById(Mockito.<Integer>any());
        verify(timeCellRepository).save(Mockito.<TimeCell>any());
    }

    /**
     * Method under test: {@link ReservationServiceImpl#getReservation(Integer)}
     */
    @Test
    void testGetReservation() {
        // Arrange
        Schedule schedule = new Schedule();
        schedule.setAvailableTimeCells(new ArrayList<>());
        schedule.setId(1);
        schedule.setTherapist(new Therapist());

        Therapist therapist = new Therapist();
        therapist.setApproved(true);
        therapist.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist.setCertificates(new ArrayList<>());
        therapist.setClients(new ArrayList<>());
        therapist.setDescription("The characteristics of someone or something");
        therapist.setEducation("Education");
        therapist.setEmail("jane.doe@example.org");
        therapist.setGender(User.Gender.MALE);
        therapist.setId(1);
        therapist.setLanguages(new ArrayList<>());
        therapist.setName("Name");
        therapist.setPassword("iloveyou");
        therapist.setPersonalTherapy("Personal Therapy");
        therapist.setPhoneNumber("6625550144");
        therapist.setPhoto("alice.liddell@example.org");
        therapist.setRegistrationFinished(true);
        therapist.setReservations(new ArrayList<>());
        therapist.setSchedule(schedule);
        therapist.setSurname("Doe");
        therapist.setThemes(new ArrayList<>());
        therapist.setTherapeuticCommunity("Therapeutic Community");
        therapist.setUserType(User.UserType.CLIENT);

        Client client = new Client();
        client.setEmail("jane.doe@example.org");
        client.setGender(User.Gender.MALE);
        client.setId(1);
        client.setName("Name");
        client.setPassword("iloveyou");
        client.setPhoneNumber("6625550144");
        client.setRegistrationFinished(true);
        client.setReservations(new ArrayList<>());
        client.setSurname("Doe");
        client.setTherapist(therapist);
        client.setUserType(User.UserType.CLIENT);

        Therapist therapist2 = new Therapist();
        therapist2.setApproved(true);
        therapist2.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist2.setCertificates(new ArrayList<>());
        therapist2.setClients(new ArrayList<>());
        therapist2.setDescription("The characteristics of someone or something");
        therapist2.setEducation("Education");
        therapist2.setEmail("jane.doe@example.org");
        therapist2.setGender(User.Gender.MALE);
        therapist2.setId(1);
        therapist2.setLanguages(new ArrayList<>());
        therapist2.setName("Name");
        therapist2.setPassword("iloveyou");
        therapist2.setPersonalTherapy("Personal Therapy");
        therapist2.setPhoneNumber("6625550144");
        therapist2.setPhoto("alice.liddell@example.org");
        therapist2.setRegistrationFinished(true);
        therapist2.setReservations(new ArrayList<>());
        therapist2.setSchedule(new Schedule());
        therapist2.setSurname("Doe");
        therapist2.setThemes(new ArrayList<>());
        therapist2.setTherapeuticCommunity("Therapeutic Community");
        therapist2.setUserType(User.UserType.CLIENT);

        Schedule schedule2 = new Schedule();
        schedule2.setAvailableTimeCells(new ArrayList<>());
        schedule2.setId(1);
        schedule2.setTherapist(therapist2);

        Therapist therapist3 = new Therapist();
        therapist3.setApproved(true);
        therapist3.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist3.setCertificates(new ArrayList<>());
        therapist3.setClients(new ArrayList<>());
        therapist3.setDescription("The characteristics of someone or something");
        therapist3.setEducation("Education");
        therapist3.setEmail("jane.doe@example.org");
        therapist3.setGender(User.Gender.MALE);
        therapist3.setId(1);
        therapist3.setLanguages(new ArrayList<>());
        therapist3.setName("Name");
        therapist3.setPassword("iloveyou");
        therapist3.setPersonalTherapy("Personal Therapy");
        therapist3.setPhoneNumber("6625550144");
        therapist3.setPhoto("alice.liddell@example.org");
        therapist3.setRegistrationFinished(true);
        therapist3.setReservations(new ArrayList<>());
        therapist3.setSchedule(schedule2);
        therapist3.setSurname("Doe");
        therapist3.setThemes(new ArrayList<>());
        therapist3.setTherapeuticCommunity("Therapeutic Community");
        therapist3.setUserType(User.UserType.CLIENT);

        Client client2 = new Client();
        client2.setEmail("jane.doe@example.org");
        client2.setGender(User.Gender.MALE);
        client2.setId(1);
        client2.setName("Name");
        client2.setPassword("iloveyou");
        client2.setPhoneNumber("6625550144");
        client2.setRegistrationFinished(true);
        client2.setReservations(new ArrayList<>());
        client2.setSurname("Doe");
        client2.setTherapist(new Therapist());
        client2.setUserType(User.UserType.CLIENT);

        Therapist therapist4 = new Therapist();
        therapist4.setApproved(true);
        therapist4.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist4.setCertificates(new ArrayList<>());
        therapist4.setClients(new ArrayList<>());
        therapist4.setDescription("The characteristics of someone or something");
        therapist4.setEducation("Education");
        therapist4.setEmail("jane.doe@example.org");
        therapist4.setGender(User.Gender.MALE);
        therapist4.setId(1);
        therapist4.setLanguages(new ArrayList<>());
        therapist4.setName("Name");
        therapist4.setPassword("iloveyou");
        therapist4.setPersonalTherapy("Personal Therapy");
        therapist4.setPhoneNumber("6625550144");
        therapist4.setPhoto("alice.liddell@example.org");
        therapist4.setRegistrationFinished(true);
        therapist4.setReservations(new ArrayList<>());
        therapist4.setSchedule(new Schedule());
        therapist4.setSurname("Doe");
        therapist4.setThemes(new ArrayList<>());
        therapist4.setTherapeuticCommunity("Therapeutic Community");
        therapist4.setUserType(User.UserType.CLIENT);

        TimeCell timeCell = new TimeCell();
        timeCell.setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        timeCell.setId(1);
        timeCell.setReservation(new Reservation());
        timeCell.setSchedule(new Schedule());
        timeCell.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Reservation reservation = new Reservation();
        reservation.setClient(client2);
        reservation.setId(1);
        reservation.setTherapist(therapist4);
        reservation.setTimeCell(timeCell);

        Therapist therapist5 = new Therapist();
        therapist5.setApproved(true);
        therapist5.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist5.setCertificates(new ArrayList<>());
        therapist5.setClients(new ArrayList<>());
        therapist5.setDescription("The characteristics of someone or something");
        therapist5.setEducation("Education");
        therapist5.setEmail("jane.doe@example.org");
        therapist5.setGender(User.Gender.MALE);
        therapist5.setId(1);
        therapist5.setLanguages(new ArrayList<>());
        therapist5.setName("Name");
        therapist5.setPassword("iloveyou");
        therapist5.setPersonalTherapy("Personal Therapy");
        therapist5.setPhoneNumber("6625550144");
        therapist5.setPhoto("alice.liddell@example.org");
        therapist5.setRegistrationFinished(true);
        therapist5.setReservations(new ArrayList<>());
        therapist5.setSchedule(new Schedule());
        therapist5.setSurname("Doe");
        therapist5.setThemes(new ArrayList<>());
        therapist5.setTherapeuticCommunity("Therapeutic Community");
        therapist5.setUserType(User.UserType.CLIENT);

        Schedule schedule3 = new Schedule();
        schedule3.setAvailableTimeCells(new ArrayList<>());
        schedule3.setId(1);
        schedule3.setTherapist(therapist5);

        TimeCell timeCell2 = new TimeCell();
        timeCell2.setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        timeCell2.setId(1);
        timeCell2.setReservation(reservation);
        timeCell2.setSchedule(schedule3);
        timeCell2.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Reservation reservation2 = new Reservation();
        reservation2.setClient(client);
        reservation2.setId(1);
        reservation2.setTherapist(therapist3);
        reservation2.setTimeCell(timeCell2);
        Optional<Reservation> ofResult = Optional.of(reservation2);
        when(reservationRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        ReservationResponse actualReservation = reservationServiceImpl.getReservation(1);

        // Assert
        verify(reservationRepository).findById(Mockito.<Integer>any());
        assertEquals("12:00 01/01/1970", actualReservation.date());
        ClientResponse clientResponseResult = actualReservation.clientResponse();
        assertEquals("6625550144", clientResponseResult.phone());
        TherapistResponse therapistResult = actualReservation.therapist();
        assertEquals("6625550144", therapistResult.phone());
        assertEquals("Doe", clientResponseResult.surname());
        assertEquals("Doe", therapistResult.surname());
        assertEquals("Education", therapistResult.education());
        assertEquals("Name", clientResponseResult.name());
        assertEquals("Name", therapistResult.name());
        assertEquals("Personal Therapy", therapistResult.personalTherapy());
        assertEquals("The characteristics of someone or something", therapistResult.description());
        assertEquals("Therapeutic Community", therapistResult.therapeuticCommunity());
        assertEquals("alice.liddell@example.org", therapistResult.photo());
        assertEquals("jane.doe@example.org", clientResponseResult.email());
        assertEquals("jane.doe@example.org", therapistResult.email());
        assertEquals(1, clientResponseResult.id().intValue());
        assertEquals(1, clientResponseResult.therapistId().intValue());
        assertEquals(1, actualReservation.id().intValue());
        assertEquals(1, therapistResult.id().intValue());
        assertFalse(clientResponseResult.isTherapist());
        assertTrue(clientResponseResult.finishedRegistration());
        assertTrue(therapistResult.approved());
        assertTrue(therapistResult.finishedRegistration());
        assertTrue(therapistResult.isTherapist());
        List<LanguageResponse> languagesResult = therapistResult.languages();
        assertTrue(languagesResult.isEmpty());
        assertSame(languagesResult, therapistResult.topics());
    }

    /**
     * Method under test: {@link ReservationServiceImpl#getReservation(Integer)}
     */
    @Test
    void testGetReservation2() {
        // Arrange
        Schedule schedule = new Schedule();
        schedule.setAvailableTimeCells(new ArrayList<>());
        schedule.setId(1);
        schedule.setTherapist(new Therapist());

        Therapist therapist = new Therapist();
        therapist.setApproved(true);
        therapist.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist.setCertificates(new ArrayList<>());
        therapist.setClients(new ArrayList<>());
        therapist.setDescription("The characteristics of someone or something");
        therapist.setEducation("Education");
        therapist.setEmail("jane.doe@example.org");
        therapist.setGender(User.Gender.MALE);
        therapist.setId(1);
        therapist.setLanguages(new ArrayList<>());
        therapist.setName("Name");
        therapist.setPassword("iloveyou");
        therapist.setPersonalTherapy("Personal Therapy");
        therapist.setPhoneNumber("6625550144");
        therapist.setPhoto("alice.liddell@example.org");
        therapist.setRegistrationFinished(true);
        therapist.setReservations(new ArrayList<>());
        therapist.setSchedule(schedule);
        therapist.setSurname("Doe");
        therapist.setThemes(new ArrayList<>());
        therapist.setTherapeuticCommunity("Therapeutic Community");
        therapist.setUserType(User.UserType.CLIENT);

        Client client = new Client();
        client.setEmail("jane.doe@example.org");
        client.setGender(User.Gender.MALE);
        client.setId(1);
        client.setName("Name");
        client.setPassword("iloveyou");
        client.setPhoneNumber("6625550144");
        client.setRegistrationFinished(true);
        client.setReservations(new ArrayList<>());
        client.setSurname("Doe");
        client.setTherapist(therapist);
        client.setUserType(User.UserType.CLIENT);

        Therapist therapist2 = new Therapist();
        therapist2.setApproved(true);
        therapist2.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist2.setCertificates(new ArrayList<>());
        therapist2.setClients(new ArrayList<>());
        therapist2.setDescription("The characteristics of someone or something");
        therapist2.setEducation("hh:mm dd/MM/yyyy");
        therapist2.setEmail("jane.doe@example.org");
        therapist2.setGender(User.Gender.MALE);
        therapist2.setId(1);
        therapist2.setLanguages(new ArrayList<>());
        therapist2.setName("hh:mm dd/MM/yyyy");
        therapist2.setPassword("iloveyou");
        therapist2.setPersonalTherapy("hh:mm dd/MM/yyyy");
        therapist2.setPhoneNumber("6625550144");
        therapist2.setPhoto("alice.liddell@example.org");
        therapist2.setRegistrationFinished(true);
        therapist2.setReservations(new ArrayList<>());
        therapist2.setSchedule(new Schedule());
        therapist2.setSurname("Doe");
        therapist2.setThemes(new ArrayList<>());
        therapist2.setTherapeuticCommunity("hh:mm dd/MM/yyyy");
        therapist2.setUserType(User.UserType.CLIENT);

        Schedule schedule2 = new Schedule();
        schedule2.setAvailableTimeCells(new ArrayList<>());
        schedule2.setId(1);
        schedule2.setTherapist(therapist2);

        Therapist therapist3 = new Therapist();
        therapist3.setApproved(true);
        therapist3.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist3.setCertificates(new ArrayList<>());
        therapist3.setClients(new ArrayList<>());
        therapist3.setDescription("The characteristics of someone or something");
        therapist3.setEducation("hh:mm dd/MM/yyyy");
        therapist3.setEmail("jane.doe@example.org");
        therapist3.setGender(User.Gender.MALE);
        therapist3.setId(1);
        therapist3.setLanguages(new ArrayList<>());
        therapist3.setName("hh:mm dd/MM/yyyy");
        therapist3.setPassword("iloveyou");
        therapist3.setPersonalTherapy("hh:mm dd/MM/yyyy");
        therapist3.setPhoneNumber("6625550144");
        therapist3.setPhoto("alice.liddell@example.org");
        therapist3.setRegistrationFinished(true);
        therapist3.setReservations(new ArrayList<>());
        therapist3.setSchedule(schedule2);
        therapist3.setSurname("Doe");
        therapist3.setThemes(new ArrayList<>());
        therapist3.setTherapeuticCommunity("hh:mm dd/MM/yyyy");
        therapist3.setUserType(User.UserType.CLIENT);

        SpokenLanguage spokenLanguage = new SpokenLanguage();
        spokenLanguage.setId(1);
        spokenLanguage.setName("hh:mm dd/MM/yyyy");
        spokenLanguage.setTherapist(therapist3);

        ArrayList<SpokenLanguage> languages = new ArrayList<>();
        languages.add(spokenLanguage);

        Therapist therapist4 = new Therapist();
        therapist4.setApproved(true);
        therapist4.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist4.setCertificates(new ArrayList<>());
        therapist4.setClients(new ArrayList<>());
        therapist4.setDescription("The characteristics of someone or something");
        therapist4.setEducation("Education");
        therapist4.setEmail("jane.doe@example.org");
        therapist4.setGender(User.Gender.MALE);
        therapist4.setId(1);
        therapist4.setLanguages(new ArrayList<>());
        therapist4.setName("Name");
        therapist4.setPassword("iloveyou");
        therapist4.setPersonalTherapy("Personal Therapy");
        therapist4.setPhoneNumber("6625550144");
        therapist4.setPhoto("alice.liddell@example.org");
        therapist4.setRegistrationFinished(true);
        therapist4.setReservations(new ArrayList<>());
        therapist4.setSchedule(new Schedule());
        therapist4.setSurname("Doe");
        therapist4.setThemes(new ArrayList<>());
        therapist4.setTherapeuticCommunity("Therapeutic Community");
        therapist4.setUserType(User.UserType.CLIENT);

        Schedule schedule3 = new Schedule();
        schedule3.setAvailableTimeCells(new ArrayList<>());
        schedule3.setId(1);
        schedule3.setTherapist(therapist4);

        Therapist therapist5 = new Therapist();
        therapist5.setApproved(true);
        therapist5.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist5.setCertificates(new ArrayList<>());
        therapist5.setClients(new ArrayList<>());
        therapist5.setDescription("The characteristics of someone or something");
        therapist5.setEducation("Education");
        therapist5.setEmail("jane.doe@example.org");
        therapist5.setGender(User.Gender.MALE);
        therapist5.setId(1);
        therapist5.setLanguages(languages);
        therapist5.setName("Name");
        therapist5.setPassword("iloveyou");
        therapist5.setPersonalTherapy("Personal Therapy");
        therapist5.setPhoneNumber("6625550144");
        therapist5.setPhoto("alice.liddell@example.org");
        therapist5.setRegistrationFinished(true);
        therapist5.setReservations(new ArrayList<>());
        therapist5.setSchedule(schedule3);
        therapist5.setSurname("Doe");
        therapist5.setThemes(new ArrayList<>());
        therapist5.setTherapeuticCommunity("Therapeutic Community");
        therapist5.setUserType(User.UserType.CLIENT);

        Client client2 = new Client();
        client2.setEmail("jane.doe@example.org");
        client2.setGender(User.Gender.MALE);
        client2.setId(1);
        client2.setName("Name");
        client2.setPassword("iloveyou");
        client2.setPhoneNumber("6625550144");
        client2.setRegistrationFinished(true);
        client2.setReservations(new ArrayList<>());
        client2.setSurname("Doe");
        client2.setTherapist(new Therapist());
        client2.setUserType(User.UserType.CLIENT);

        Therapist therapist6 = new Therapist();
        therapist6.setApproved(true);
        therapist6.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist6.setCertificates(new ArrayList<>());
        therapist6.setClients(new ArrayList<>());
        therapist6.setDescription("The characteristics of someone or something");
        therapist6.setEducation("Education");
        therapist6.setEmail("jane.doe@example.org");
        therapist6.setGender(User.Gender.MALE);
        therapist6.setId(1);
        therapist6.setLanguages(new ArrayList<>());
        therapist6.setName("Name");
        therapist6.setPassword("iloveyou");
        therapist6.setPersonalTherapy("Personal Therapy");
        therapist6.setPhoneNumber("6625550144");
        therapist6.setPhoto("alice.liddell@example.org");
        therapist6.setRegistrationFinished(true);
        therapist6.setReservations(new ArrayList<>());
        therapist6.setSchedule(new Schedule());
        therapist6.setSurname("Doe");
        therapist6.setThemes(new ArrayList<>());
        therapist6.setTherapeuticCommunity("Therapeutic Community");
        therapist6.setUserType(User.UserType.CLIENT);

        TimeCell timeCell = new TimeCell();
        timeCell.setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        timeCell.setId(1);
        timeCell.setReservation(new Reservation());
        timeCell.setSchedule(new Schedule());
        timeCell.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Reservation reservation = new Reservation();
        reservation.setClient(client2);
        reservation.setId(1);
        reservation.setTherapist(therapist6);
        reservation.setTimeCell(timeCell);

        Therapist therapist7 = new Therapist();
        therapist7.setApproved(true);
        therapist7.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist7.setCertificates(new ArrayList<>());
        therapist7.setClients(new ArrayList<>());
        therapist7.setDescription("The characteristics of someone or something");
        therapist7.setEducation("Education");
        therapist7.setEmail("jane.doe@example.org");
        therapist7.setGender(User.Gender.MALE);
        therapist7.setId(1);
        therapist7.setLanguages(new ArrayList<>());
        therapist7.setName("Name");
        therapist7.setPassword("iloveyou");
        therapist7.setPersonalTherapy("Personal Therapy");
        therapist7.setPhoneNumber("6625550144");
        therapist7.setPhoto("alice.liddell@example.org");
        therapist7.setRegistrationFinished(true);
        therapist7.setReservations(new ArrayList<>());
        therapist7.setSchedule(new Schedule());
        therapist7.setSurname("Doe");
        therapist7.setThemes(new ArrayList<>());
        therapist7.setTherapeuticCommunity("Therapeutic Community");
        therapist7.setUserType(User.UserType.CLIENT);

        Schedule schedule4 = new Schedule();
        schedule4.setAvailableTimeCells(new ArrayList<>());
        schedule4.setId(1);
        schedule4.setTherapist(therapist7);

        TimeCell timeCell2 = new TimeCell();
        timeCell2.setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        timeCell2.setId(1);
        timeCell2.setReservation(reservation);
        timeCell2.setSchedule(schedule4);
        timeCell2.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Reservation reservation2 = new Reservation();
        reservation2.setClient(client);
        reservation2.setId(1);
        reservation2.setTherapist(therapist5);
        reservation2.setTimeCell(timeCell2);
        Optional<Reservation> ofResult = Optional.of(reservation2);
        when(reservationRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        ReservationResponse actualReservation = reservationServiceImpl.getReservation(1);

        // Assert
        verify(reservationRepository).findById(Mockito.<Integer>any());
        assertEquals("12:00 01/01/1970", actualReservation.date());
        ClientResponse clientResponseResult = actualReservation.clientResponse();
        assertEquals("6625550144", clientResponseResult.phone());
        TherapistResponse therapistResult = actualReservation.therapist();
        assertEquals("6625550144", therapistResult.phone());
        assertEquals("Doe", clientResponseResult.surname());
        assertEquals("Doe", therapistResult.surname());
        assertEquals("Education", therapistResult.education());
        assertEquals("Name", clientResponseResult.name());
        assertEquals("Name", therapistResult.name());
        assertEquals("Personal Therapy", therapistResult.personalTherapy());
        assertEquals("The characteristics of someone or something", therapistResult.description());
        assertEquals("Therapeutic Community", therapistResult.therapeuticCommunity());
        assertEquals("alice.liddell@example.org", therapistResult.photo());
        List<LanguageResponse> languagesResult = therapistResult.languages();
        assertEquals(1, languagesResult.size());
        LanguageResponse getResult = languagesResult.get(0);
        assertEquals("hh:mm dd/MM/yyyy", getResult.name());
        assertEquals("jane.doe@example.org", clientResponseResult.email());
        assertEquals("jane.doe@example.org", therapistResult.email());
        assertEquals(1, clientResponseResult.id().intValue());
        assertEquals(1, clientResponseResult.therapistId().intValue());
        assertEquals(1, getResult.id().intValue());
        assertEquals(1, actualReservation.id().intValue());
        assertEquals(1, therapistResult.id().intValue());
        assertFalse(clientResponseResult.isTherapist());
        assertTrue(clientResponseResult.finishedRegistration());
        assertTrue(therapistResult.approved());
        assertTrue(therapistResult.finishedRegistration());
        assertTrue(therapistResult.isTherapist());
        assertTrue(therapistResult.topics().isEmpty());
    }

    /**
     * Method under test:
     * {@link ReservationServiceImpl#delayReservation(Integer, Integer)}
     */
    @Test
    void testDelayReservation() {
        // Arrange
        Schedule schedule = new Schedule();
        schedule.setAvailableTimeCells(new ArrayList<>());
        schedule.setId(1);
        schedule.setTherapist(new Therapist());

        Therapist therapist = new Therapist();
        therapist.setApproved(true);
        therapist.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist.setCertificates(new ArrayList<>());
        therapist.setClients(new ArrayList<>());
        therapist.setDescription("The characteristics of someone or something");
        therapist.setEducation("Education");
        therapist.setEmail("jane.doe@example.org");
        therapist.setGender(User.Gender.MALE);
        therapist.setId(1);
        therapist.setLanguages(new ArrayList<>());
        therapist.setName("Name");
        therapist.setPassword("iloveyou");
        therapist.setPersonalTherapy("Personal Therapy");
        therapist.setPhoneNumber("6625550144");
        therapist.setPhoto("alice.liddell@example.org");
        therapist.setRegistrationFinished(true);
        therapist.setReservations(new ArrayList<>());
        therapist.setSchedule(schedule);
        therapist.setSurname("Doe");
        therapist.setThemes(new ArrayList<>());
        therapist.setTherapeuticCommunity("Therapeutic Community");
        therapist.setUserType(User.UserType.CLIENT);

        Client client = new Client();
        client.setEmail("jane.doe@example.org");
        client.setGender(User.Gender.MALE);
        client.setId(1);
        client.setName("Name");
        client.setPassword("iloveyou");
        client.setPhoneNumber("6625550144");
        client.setRegistrationFinished(true);
        client.setReservations(new ArrayList<>());
        client.setSurname("Doe");
        client.setTherapist(therapist);
        client.setUserType(User.UserType.CLIENT);

        Therapist therapist2 = new Therapist();
        therapist2.setApproved(true);
        therapist2.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist2.setCertificates(new ArrayList<>());
        therapist2.setClients(new ArrayList<>());
        therapist2.setDescription("The characteristics of someone or something");
        therapist2.setEducation("Education");
        therapist2.setEmail("jane.doe@example.org");
        therapist2.setGender(User.Gender.MALE);
        therapist2.setId(1);
        therapist2.setLanguages(new ArrayList<>());
        therapist2.setName("Name");
        therapist2.setPassword("iloveyou");
        therapist2.setPersonalTherapy("Personal Therapy");
        therapist2.setPhoneNumber("6625550144");
        therapist2.setPhoto("alice.liddell@example.org");
        therapist2.setRegistrationFinished(true);
        therapist2.setReservations(new ArrayList<>());
        therapist2.setSchedule(new Schedule());
        therapist2.setSurname("Doe");
        therapist2.setThemes(new ArrayList<>());
        therapist2.setTherapeuticCommunity("Therapeutic Community");
        therapist2.setUserType(User.UserType.CLIENT);

        Schedule schedule2 = new Schedule();
        schedule2.setAvailableTimeCells(new ArrayList<>());
        schedule2.setId(1);
        schedule2.setTherapist(therapist2);

        Therapist therapist3 = new Therapist();
        therapist3.setApproved(true);
        therapist3.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist3.setCertificates(new ArrayList<>());
        therapist3.setClients(new ArrayList<>());
        therapist3.setDescription("The characteristics of someone or something");
        therapist3.setEducation("Education");
        therapist3.setEmail("jane.doe@example.org");
        therapist3.setGender(User.Gender.MALE);
        therapist3.setId(1);
        therapist3.setLanguages(new ArrayList<>());
        therapist3.setName("Name");
        therapist3.setPassword("iloveyou");
        therapist3.setPersonalTherapy("Personal Therapy");
        therapist3.setPhoneNumber("6625550144");
        therapist3.setPhoto("alice.liddell@example.org");
        therapist3.setRegistrationFinished(true);
        therapist3.setReservations(new ArrayList<>());
        therapist3.setSchedule(schedule2);
        therapist3.setSurname("Doe");
        therapist3.setThemes(new ArrayList<>());
        therapist3.setTherapeuticCommunity("Therapeutic Community");
        therapist3.setUserType(User.UserType.CLIENT);

        Client client2 = new Client();
        client2.setEmail("jane.doe@example.org");
        client2.setGender(User.Gender.MALE);
        client2.setId(1);
        client2.setName("Name");
        client2.setPassword("iloveyou");
        client2.setPhoneNumber("6625550144");
        client2.setRegistrationFinished(true);
        client2.setReservations(new ArrayList<>());
        client2.setSurname("Doe");
        client2.setTherapist(new Therapist());
        client2.setUserType(User.UserType.CLIENT);

        Therapist therapist4 = new Therapist();
        therapist4.setApproved(true);
        therapist4.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist4.setCertificates(new ArrayList<>());
        therapist4.setClients(new ArrayList<>());
        therapist4.setDescription("The characteristics of someone or something");
        therapist4.setEducation("Education");
        therapist4.setEmail("jane.doe@example.org");
        therapist4.setGender(User.Gender.MALE);
        therapist4.setId(1);
        therapist4.setLanguages(new ArrayList<>());
        therapist4.setName("Name");
        therapist4.setPassword("iloveyou");
        therapist4.setPersonalTherapy("Personal Therapy");
        therapist4.setPhoneNumber("6625550144");
        therapist4.setPhoto("alice.liddell@example.org");
        therapist4.setRegistrationFinished(true);
        therapist4.setReservations(new ArrayList<>());
        therapist4.setSchedule(new Schedule());
        therapist4.setSurname("Doe");
        therapist4.setThemes(new ArrayList<>());
        therapist4.setTherapeuticCommunity("Therapeutic Community");
        therapist4.setUserType(User.UserType.CLIENT);

        TimeCell timeCell = new TimeCell();
        timeCell.setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        timeCell.setId(1);
        timeCell.setReservation(new Reservation());
        timeCell.setSchedule(new Schedule());
        timeCell.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Reservation reservation = new Reservation();
        reservation.setClient(client2);
        reservation.setId(1);
        reservation.setTherapist(therapist4);
        reservation.setTimeCell(timeCell);

        Therapist therapist5 = new Therapist();
        therapist5.setApproved(true);
        therapist5.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist5.setCertificates(new ArrayList<>());
        therapist5.setClients(new ArrayList<>());
        therapist5.setDescription("The characteristics of someone or something");
        therapist5.setEducation("Education");
        therapist5.setEmail("jane.doe@example.org");
        therapist5.setGender(User.Gender.MALE);
        therapist5.setId(1);
        therapist5.setLanguages(new ArrayList<>());
        therapist5.setName("Name");
        therapist5.setPassword("iloveyou");
        therapist5.setPersonalTherapy("Personal Therapy");
        therapist5.setPhoneNumber("6625550144");
        therapist5.setPhoto("alice.liddell@example.org");
        therapist5.setRegistrationFinished(true);
        therapist5.setReservations(new ArrayList<>());
        therapist5.setSchedule(new Schedule());
        therapist5.setSurname("Doe");
        therapist5.setThemes(new ArrayList<>());
        therapist5.setTherapeuticCommunity("Therapeutic Community");
        therapist5.setUserType(User.UserType.CLIENT);

        Schedule schedule3 = new Schedule();
        schedule3.setAvailableTimeCells(new ArrayList<>());
        schedule3.setId(1);
        schedule3.setTherapist(therapist5);

        TimeCell timeCell2 = new TimeCell();
        timeCell2.setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        timeCell2.setId(1);
        timeCell2.setReservation(reservation);
        timeCell2.setSchedule(schedule3);
        timeCell2.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Reservation reservation2 = new Reservation();
        reservation2.setClient(client);
        reservation2.setId(1);
        reservation2.setTherapist(therapist3);
        reservation2.setTimeCell(timeCell2);
        Optional<Reservation> ofResult = Optional.of(reservation2);
        when(reservationRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        Therapist therapist6 = new Therapist();
        therapist6.setApproved(true);
        therapist6.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist6.setCertificates(new ArrayList<>());
        therapist6.setClients(new ArrayList<>());
        therapist6.setDescription("The characteristics of someone or something");
        therapist6.setEducation("Education");
        therapist6.setEmail("jane.doe@example.org");
        therapist6.setGender(User.Gender.MALE);
        therapist6.setId(1);
        therapist6.setLanguages(new ArrayList<>());
        therapist6.setName("Name");
        therapist6.setPassword("iloveyou");
        therapist6.setPersonalTherapy("Personal Therapy");
        therapist6.setPhoneNumber("6625550144");
        therapist6.setPhoto("alice.liddell@example.org");
        therapist6.setRegistrationFinished(true);
        therapist6.setReservations(new ArrayList<>());
        therapist6.setSchedule(new Schedule());
        therapist6.setSurname("Doe");
        therapist6.setThemes(new ArrayList<>());
        therapist6.setTherapeuticCommunity("Therapeutic Community");
        therapist6.setUserType(User.UserType.CLIENT);

        Client client3 = new Client();
        client3.setEmail("jane.doe@example.org");
        client3.setGender(User.Gender.MALE);
        client3.setId(1);
        client3.setName("Name");
        client3.setPassword("iloveyou");
        client3.setPhoneNumber("6625550144");
        client3.setRegistrationFinished(true);
        client3.setReservations(new ArrayList<>());
        client3.setSurname("Doe");
        client3.setTherapist(therapist6);
        client3.setUserType(User.UserType.CLIENT);

        Schedule schedule4 = new Schedule();
        schedule4.setAvailableTimeCells(new ArrayList<>());
        schedule4.setId(1);
        schedule4.setTherapist(new Therapist());

        Therapist therapist7 = new Therapist();
        therapist7.setApproved(true);
        therapist7.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist7.setCertificates(new ArrayList<>());
        therapist7.setClients(new ArrayList<>());
        therapist7.setDescription("The characteristics of someone or something");
        therapist7.setEducation("Education");
        therapist7.setEmail("jane.doe@example.org");
        therapist7.setGender(User.Gender.MALE);
        therapist7.setId(1);
        therapist7.setLanguages(new ArrayList<>());
        therapist7.setName("Name");
        therapist7.setPassword("iloveyou");
        therapist7.setPersonalTherapy("Personal Therapy");
        therapist7.setPhoneNumber("6625550144");
        therapist7.setPhoto("alice.liddell@example.org");
        therapist7.setRegistrationFinished(true);
        therapist7.setReservations(new ArrayList<>());
        therapist7.setSchedule(schedule4);
        therapist7.setSurname("Doe");
        therapist7.setThemes(new ArrayList<>());
        therapist7.setTherapeuticCommunity("Therapeutic Community");
        therapist7.setUserType(User.UserType.CLIENT);

        Reservation reservation3 = new Reservation();
        reservation3.setClient(new Client());
        reservation3.setId(1);
        reservation3.setTherapist(new Therapist());
        reservation3.setTimeCell(new TimeCell());

        Schedule schedule5 = new Schedule();
        schedule5.setAvailableTimeCells(new ArrayList<>());
        schedule5.setId(1);
        schedule5.setTherapist(new Therapist());

        TimeCell timeCell3 = new TimeCell();
        timeCell3.setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        timeCell3.setId(1);
        timeCell3.setReservation(reservation3);
        timeCell3.setSchedule(schedule5);
        timeCell3.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Reservation reservation4 = new Reservation();
        reservation4.setClient(client3);
        reservation4.setId(1);
        reservation4.setTherapist(therapist7);
        reservation4.setTimeCell(timeCell3);

        Schedule schedule6 = new Schedule();
        schedule6.setAvailableTimeCells(new ArrayList<>());
        schedule6.setId(1);
        schedule6.setTherapist(new Therapist());

        Therapist therapist8 = new Therapist();
        therapist8.setApproved(true);
        therapist8.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist8.setCertificates(new ArrayList<>());
        therapist8.setClients(new ArrayList<>());
        therapist8.setDescription("The characteristics of someone or something");
        therapist8.setEducation("Education");
        therapist8.setEmail("jane.doe@example.org");
        therapist8.setGender(User.Gender.MALE);
        therapist8.setId(1);
        therapist8.setLanguages(new ArrayList<>());
        therapist8.setName("Name");
        therapist8.setPassword("iloveyou");
        therapist8.setPersonalTherapy("Personal Therapy");
        therapist8.setPhoneNumber("6625550144");
        therapist8.setPhoto("alice.liddell@example.org");
        therapist8.setRegistrationFinished(true);
        therapist8.setReservations(new ArrayList<>());
        therapist8.setSchedule(schedule6);
        therapist8.setSurname("Doe");
        therapist8.setThemes(new ArrayList<>());
        therapist8.setTherapeuticCommunity("Therapeutic Community");
        therapist8.setUserType(User.UserType.CLIENT);

        Schedule schedule7 = new Schedule();
        schedule7.setAvailableTimeCells(new ArrayList<>());
        schedule7.setId(1);
        schedule7.setTherapist(therapist8);

        TimeCell timeCell4 = new TimeCell();
        timeCell4.setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        timeCell4.setId(1);
        timeCell4.setReservation(reservation4);
        timeCell4.setSchedule(schedule7);
        timeCell4.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        Optional<TimeCell> ofResult2 = Optional.of(timeCell4);
        when(timeCellRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult2);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> reservationServiceImpl.delayReservation(1, 1));
        verify(reservationRepository).findById(Mockito.<Integer>any());
        verify(timeCellRepository).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test:
     * {@link ReservationServiceImpl#delayReservation(Integer, Integer)}
     */
    @Test
    void testDelayReservation2() {
        // Arrange
        Schedule schedule = new Schedule();
        schedule.setAvailableTimeCells(new ArrayList<>());
        schedule.setId(1);
        schedule.setTherapist(new Therapist());

        Therapist therapist = new Therapist();
        therapist.setApproved(true);
        therapist.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist.setCertificates(new ArrayList<>());
        therapist.setClients(new ArrayList<>());
        therapist.setDescription("The characteristics of someone or something");
        therapist.setEducation("Education");
        therapist.setEmail("jane.doe@example.org");
        therapist.setGender(User.Gender.MALE);
        therapist.setId(1);
        therapist.setLanguages(new ArrayList<>());
        therapist.setName("Name");
        therapist.setPassword("iloveyou");
        therapist.setPersonalTherapy("Personal Therapy");
        therapist.setPhoneNumber("6625550144");
        therapist.setPhoto("alice.liddell@example.org");
        therapist.setRegistrationFinished(true);
        therapist.setReservations(new ArrayList<>());
        therapist.setSchedule(schedule);
        therapist.setSurname("Doe");
        therapist.setThemes(new ArrayList<>());
        therapist.setTherapeuticCommunity("Therapeutic Community");
        therapist.setUserType(User.UserType.CLIENT);

        Client client = new Client();
        client.setEmail("jane.doe@example.org");
        client.setGender(User.Gender.MALE);
        client.setId(1);
        client.setName("Name");
        client.setPassword("iloveyou");
        client.setPhoneNumber("6625550144");
        client.setRegistrationFinished(true);
        client.setReservations(new ArrayList<>());
        client.setSurname("Doe");
        client.setTherapist(therapist);
        client.setUserType(User.UserType.CLIENT);

        Therapist therapist2 = new Therapist();
        therapist2.setApproved(true);
        therapist2.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist2.setCertificates(new ArrayList<>());
        therapist2.setClients(new ArrayList<>());
        therapist2.setDescription("The characteristics of someone or something");
        therapist2.setEducation("Education");
        therapist2.setEmail("jane.doe@example.org");
        therapist2.setGender(User.Gender.MALE);
        therapist2.setId(1);
        therapist2.setLanguages(new ArrayList<>());
        therapist2.setName("Name");
        therapist2.setPassword("iloveyou");
        therapist2.setPersonalTherapy("Personal Therapy");
        therapist2.setPhoneNumber("6625550144");
        therapist2.setPhoto("alice.liddell@example.org");
        therapist2.setRegistrationFinished(true);
        therapist2.setReservations(new ArrayList<>());
        therapist2.setSchedule(new Schedule());
        therapist2.setSurname("Doe");
        therapist2.setThemes(new ArrayList<>());
        therapist2.setTherapeuticCommunity("Therapeutic Community");
        therapist2.setUserType(User.UserType.CLIENT);

        Schedule schedule2 = new Schedule();
        schedule2.setAvailableTimeCells(new ArrayList<>());
        schedule2.setId(1);
        schedule2.setTherapist(therapist2);

        Therapist therapist3 = new Therapist();
        therapist3.setApproved(true);
        therapist3.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist3.setCertificates(new ArrayList<>());
        therapist3.setClients(new ArrayList<>());
        therapist3.setDescription("The characteristics of someone or something");
        therapist3.setEducation("Education");
        therapist3.setEmail("jane.doe@example.org");
        therapist3.setGender(User.Gender.MALE);
        therapist3.setId(1);
        therapist3.setLanguages(new ArrayList<>());
        therapist3.setName("Name");
        therapist3.setPassword("iloveyou");
        therapist3.setPersonalTherapy("Personal Therapy");
        therapist3.setPhoneNumber("6625550144");
        therapist3.setPhoto("alice.liddell@example.org");
        therapist3.setRegistrationFinished(true);
        therapist3.setReservations(new ArrayList<>());
        therapist3.setSchedule(schedule2);
        therapist3.setSurname("Doe");
        therapist3.setThemes(new ArrayList<>());
        therapist3.setTherapeuticCommunity("Therapeutic Community");
        therapist3.setUserType(User.UserType.CLIENT);

        Client client2 = new Client();
        client2.setEmail("jane.doe@example.org");
        client2.setGender(User.Gender.MALE);
        client2.setId(1);
        client2.setName("Name");
        client2.setPassword("iloveyou");
        client2.setPhoneNumber("6625550144");
        client2.setRegistrationFinished(true);
        client2.setReservations(new ArrayList<>());
        client2.setSurname("Doe");
        client2.setTherapist(new Therapist());
        client2.setUserType(User.UserType.CLIENT);

        Therapist therapist4 = new Therapist();
        therapist4.setApproved(true);
        therapist4.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist4.setCertificates(new ArrayList<>());
        therapist4.setClients(new ArrayList<>());
        therapist4.setDescription("The characteristics of someone or something");
        therapist4.setEducation("Education");
        therapist4.setEmail("jane.doe@example.org");
        therapist4.setGender(User.Gender.MALE);
        therapist4.setId(1);
        therapist4.setLanguages(new ArrayList<>());
        therapist4.setName("Name");
        therapist4.setPassword("iloveyou");
        therapist4.setPersonalTherapy("Personal Therapy");
        therapist4.setPhoneNumber("6625550144");
        therapist4.setPhoto("alice.liddell@example.org");
        therapist4.setRegistrationFinished(true);
        therapist4.setReservations(new ArrayList<>());
        therapist4.setSchedule(new Schedule());
        therapist4.setSurname("Doe");
        therapist4.setThemes(new ArrayList<>());
        therapist4.setTherapeuticCommunity("Therapeutic Community");
        therapist4.setUserType(User.UserType.CLIENT);

        TimeCell timeCell = new TimeCell();
        timeCell.setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        timeCell.setId(1);
        timeCell.setReservation(new Reservation());
        timeCell.setSchedule(new Schedule());
        timeCell.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Reservation reservation = new Reservation();
        reservation.setClient(client2);
        reservation.setId(1);
        reservation.setTherapist(therapist4);
        reservation.setTimeCell(timeCell);

        Therapist therapist5 = new Therapist();
        therapist5.setApproved(true);
        therapist5.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist5.setCertificates(new ArrayList<>());
        therapist5.setClients(new ArrayList<>());
        therapist5.setDescription("The characteristics of someone or something");
        therapist5.setEducation("Education");
        therapist5.setEmail("jane.doe@example.org");
        therapist5.setGender(User.Gender.MALE);
        therapist5.setId(1);
        therapist5.setLanguages(new ArrayList<>());
        therapist5.setName("Name");
        therapist5.setPassword("iloveyou");
        therapist5.setPersonalTherapy("Personal Therapy");
        therapist5.setPhoneNumber("6625550144");
        therapist5.setPhoto("alice.liddell@example.org");
        therapist5.setRegistrationFinished(true);
        therapist5.setReservations(new ArrayList<>());
        therapist5.setSchedule(new Schedule());
        therapist5.setSurname("Doe");
        therapist5.setThemes(new ArrayList<>());
        therapist5.setTherapeuticCommunity("Therapeutic Community");
        therapist5.setUserType(User.UserType.CLIENT);

        Schedule schedule3 = new Schedule();
        schedule3.setAvailableTimeCells(new ArrayList<>());
        schedule3.setId(1);
        schedule3.setTherapist(therapist5);

        TimeCell timeCell2 = new TimeCell();
        timeCell2.setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        timeCell2.setId(1);
        timeCell2.setReservation(reservation);
        timeCell2.setSchedule(schedule3);
        timeCell2.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Reservation reservation2 = new Reservation();
        reservation2.setClient(client);
        reservation2.setId(1);
        reservation2.setTherapist(therapist3);
        reservation2.setTimeCell(timeCell2);
        Optional<Reservation> ofResult = Optional.of(reservation2);
        when(reservationRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        when(timeCellRepository.findById(Mockito.<Integer>any()))
                .thenThrow(new IllegalArgumentException("time cell not found"));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> reservationServiceImpl.delayReservation(1, 1));
        verify(reservationRepository).findById(Mockito.<Integer>any());
        verify(timeCellRepository).findById(Mockito.<Integer>any());
    }
}
