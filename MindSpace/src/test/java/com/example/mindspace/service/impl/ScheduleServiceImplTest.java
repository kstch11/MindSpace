package com.example.mindspace.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.mindspace.api.ScheduleResponse;
import com.example.mindspace.model.Client;
import com.example.mindspace.model.Reservation;
import com.example.mindspace.model.Schedule;
import com.example.mindspace.model.Therapist;
import com.example.mindspace.model.TimeCell;
import com.example.mindspace.model.User;
import com.example.mindspace.repository.ScheduleRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ScheduleServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ScheduleServiceImplTest {
    @MockBean
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ScheduleServiceImpl scheduleServiceImpl;

    /**
     * Method under test: {@link ScheduleServiceImpl#findAll()}
     */
    @Test
    void testFindAll() {
        // Arrange
        when(scheduleRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<ScheduleResponse> actualFindAllResult = scheduleServiceImpl.findAll();

        // Assert
        verify(scheduleRepository).findAll();
        assertTrue(actualFindAllResult.isEmpty());
    }

    /**
     * Method under test: {@link ScheduleServiceImpl#findAll()}
     */
    @Test
    void testFindAll2() {
        // Arrange
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
        therapist.setSchedule(new Schedule());
        therapist.setSurname("Doe");
        therapist.setThemes(new ArrayList<>());
        therapist.setTherapeuticCommunity("Therapeutic Community");
        therapist.setUserType(User.UserType.CLIENT);

        Schedule schedule = new Schedule();
        schedule.setAvailableTimeCells(new ArrayList<>());
        schedule.setId(1);
        schedule.setTherapist(therapist);

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
        therapist2.setSchedule(schedule);
        therapist2.setSurname("Doe");
        therapist2.setThemes(new ArrayList<>());
        therapist2.setTherapeuticCommunity("Therapeutic Community");
        therapist2.setUserType(User.UserType.CLIENT);

        Schedule schedule2 = new Schedule();
        schedule2.setAvailableTimeCells(new ArrayList<>());
        schedule2.setId(1);
        schedule2.setTherapist(therapist2);

        ArrayList<Schedule> scheduleList = new ArrayList<>();
        scheduleList.add(schedule2);
        when(scheduleRepository.findAll()).thenReturn(scheduleList);

        // Act
        List<ScheduleResponse> actualFindAllResult = scheduleServiceImpl.findAll();

        // Assert
        verify(scheduleRepository).findAll();
        assertEquals(1, actualFindAllResult.size());
        ScheduleResponse getResult = actualFindAllResult.get(0);
        assertEquals(1, getResult.id().intValue());
        assertTrue(getResult.timeCells().isEmpty());
    }

    /**
     * Method under test: {@link ScheduleServiceImpl#findAll()}
     */
    @Test
    void testFindAll3() {
        // Arrange
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
        therapist.setSchedule(new Schedule());
        therapist.setSurname("Doe");
        therapist.setThemes(new ArrayList<>());
        therapist.setTherapeuticCommunity("Therapeutic Community");
        therapist.setUserType(User.UserType.CLIENT);

        Schedule schedule = new Schedule();
        schedule.setAvailableTimeCells(new ArrayList<>());
        schedule.setId(1);
        schedule.setTherapist(therapist);

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
        therapist2.setSchedule(schedule);
        therapist2.setSurname("Doe");
        therapist2.setThemes(new ArrayList<>());
        therapist2.setTherapeuticCommunity("Therapeutic Community");
        therapist2.setUserType(User.UserType.CLIENT);

        Schedule schedule2 = new Schedule();
        schedule2.setAvailableTimeCells(new ArrayList<>());
        schedule2.setId(1);
        schedule2.setTherapist(therapist2);

        Therapist therapist3 = new Therapist();
        therapist3.setApproved(false);
        therapist3.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist3.setCertificates(new ArrayList<>());
        therapist3.setClients(new ArrayList<>());
        therapist3.setDescription("Description");
        therapist3.setEducation("42");
        therapist3.setEmail("john.smith@example.org");
        therapist3.setGender(User.Gender.FEMALE);
        therapist3.setId(2);
        therapist3.setLanguages(new ArrayList<>());
        therapist3.setName("42");
        therapist3.setPassword("Password");
        therapist3.setPersonalTherapy("42");
        therapist3.setPhoneNumber("8605550118");
        therapist3.setPhoto("Photo");
        therapist3.setRegistrationFinished(false);
        therapist3.setReservations(new ArrayList<>());
        therapist3.setSchedule(new Schedule());
        therapist3.setSurname("Surname");
        therapist3.setThemes(new ArrayList<>());
        therapist3.setTherapeuticCommunity("42");
        therapist3.setUserType(User.UserType.THERAPIST);

        Schedule schedule3 = new Schedule();
        schedule3.setAvailableTimeCells(new ArrayList<>());
        schedule3.setId(2);
        schedule3.setTherapist(therapist3);

        Therapist therapist4 = new Therapist();
        therapist4.setApproved(false);
        therapist4.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist4.setCertificates(new ArrayList<>());
        therapist4.setClients(new ArrayList<>());
        therapist4.setDescription("Description");
        therapist4.setEducation("42");
        therapist4.setEmail("john.smith@example.org");
        therapist4.setGender(User.Gender.FEMALE);
        therapist4.setId(2);
        therapist4.setLanguages(new ArrayList<>());
        therapist4.setName("42");
        therapist4.setPassword("Password");
        therapist4.setPersonalTherapy("42");
        therapist4.setPhoneNumber("8605550118");
        therapist4.setPhoto("Photo");
        therapist4.setRegistrationFinished(false);
        therapist4.setReservations(new ArrayList<>());
        therapist4.setSchedule(schedule3);
        therapist4.setSurname("Surname");
        therapist4.setThemes(new ArrayList<>());
        therapist4.setTherapeuticCommunity("42");
        therapist4.setUserType(User.UserType.THERAPIST);

        Schedule schedule4 = new Schedule();
        schedule4.setAvailableTimeCells(new ArrayList<>());
        schedule4.setId(2);
        schedule4.setTherapist(therapist4);

        ArrayList<Schedule> scheduleList = new ArrayList<>();
        scheduleList.add(schedule4);
        scheduleList.add(schedule2);
        when(scheduleRepository.findAll()).thenReturn(scheduleList);

        // Act
        List<ScheduleResponse> actualFindAllResult = scheduleServiceImpl.findAll();

        // Assert
        verify(scheduleRepository).findAll();
        assertEquals(2, actualFindAllResult.size());
        ScheduleResponse getResult = actualFindAllResult.get(1);
        assertEquals(1, getResult.id().intValue());
        ScheduleResponse getResult2 = actualFindAllResult.get(0);
        assertEquals(2, getResult2.id().intValue());
        assertTrue(getResult2.timeCells().isEmpty());
        assertTrue(getResult.timeCells().isEmpty());
    }

    /**
     * Method under test: {@link ScheduleServiceImpl#findAll()}
     */
    @Test
    void testFindAll4() {
        // Arrange
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
        therapist.setSchedule(new Schedule());
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
        therapist2.setSchedule(schedule);
        therapist2.setSurname("Doe");
        therapist2.setThemes(new ArrayList<>());
        therapist2.setTherapeuticCommunity("Therapeutic Community");
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
        therapist3.setSchedule(schedule3);
        therapist3.setSurname("Doe");
        therapist3.setThemes(new ArrayList<>());
        therapist3.setTherapeuticCommunity("Therapeutic Community");
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

        Schedule schedule5 = new Schedule();
        schedule5.setAvailableTimeCells(new ArrayList<>());
        schedule5.setId(1);
        schedule5.setTherapist(therapist4);

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
        therapist5.setSchedule(schedule5);
        therapist5.setSurname("Doe");
        therapist5.setThemes(new ArrayList<>());
        therapist5.setTherapeuticCommunity("Therapeutic Community");
        therapist5.setUserType(User.UserType.CLIENT);

        Schedule schedule6 = new Schedule();
        schedule6.setAvailableTimeCells(availableTimeCells);
        schedule6.setId(1);
        schedule6.setTherapist(therapist5);

        ArrayList<Schedule> scheduleList = new ArrayList<>();
        scheduleList.add(schedule6);
        when(scheduleRepository.findAll()).thenReturn(scheduleList);

        // Act
        List<ScheduleResponse> actualFindAllResult = scheduleServiceImpl.findAll();

        // Assert
        verify(scheduleRepository).findAll();
        assertEquals(1, actualFindAllResult.size());
        ScheduleResponse getResult = actualFindAllResult.get(0);
        assertEquals(1, getResult.id().intValue());
        assertEquals(1, getResult.timeCells().size());
    }

    /**
     * Method under test: {@link ScheduleServiceImpl#findAll()}
     */
    @Test
    void testFindAll5() {
        // Arrange
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
        therapist.setSchedule(new Schedule());
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
        therapist2.setSchedule(schedule);
        therapist2.setSurname("Doe");
        therapist2.setThemes(new ArrayList<>());
        therapist2.setTherapeuticCommunity("Therapeutic Community");
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
        therapist3.setSchedule(schedule3);
        therapist3.setSurname("Doe");
        therapist3.setThemes(new ArrayList<>());
        therapist3.setTherapeuticCommunity("Therapeutic Community");
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

        Therapist therapist4 = new Therapist();
        therapist4.setApproved(false);
        therapist4.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist4.setCertificates(new ArrayList<>());
        therapist4.setClients(new ArrayList<>());
        therapist4.setDescription("Description");
        therapist4.setEducation("42");
        therapist4.setEmail("john.smith@example.org");
        therapist4.setGender(User.Gender.FEMALE);
        therapist4.setId(2);
        therapist4.setLanguages(new ArrayList<>());
        therapist4.setName("42");
        therapist4.setPassword("Password");
        therapist4.setPersonalTherapy("42");
        therapist4.setPhoneNumber("8605550118");
        therapist4.setPhoto("Photo");
        therapist4.setRegistrationFinished(false);
        therapist4.setReservations(new ArrayList<>());
        therapist4.setSchedule(new Schedule());
        therapist4.setSurname("Surname");
        therapist4.setThemes(new ArrayList<>());
        therapist4.setTherapeuticCommunity("42");
        therapist4.setUserType(User.UserType.THERAPIST);

        Client client2 = new Client();
        client2.setEmail("john.smith@example.org");
        client2.setGender(User.Gender.FEMALE);
        client2.setId(2);
        client2.setName("42");
        client2.setPassword("Password");
        client2.setPhoneNumber("8605550118");
        client2.setRegistrationFinished(false);
        client2.setReservations(new ArrayList<>());
        client2.setSurname("Surname");
        client2.setTherapist(therapist4);
        client2.setUserType(User.UserType.THERAPIST);

        Schedule schedule5 = new Schedule();
        schedule5.setAvailableTimeCells(new ArrayList<>());
        schedule5.setId(2);
        schedule5.setTherapist(new Therapist());

        Therapist therapist5 = new Therapist();
        therapist5.setApproved(false);
        therapist5.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist5.setCertificates(new ArrayList<>());
        therapist5.setClients(new ArrayList<>());
        therapist5.setDescription("Description");
        therapist5.setEducation("42");
        therapist5.setEmail("john.smith@example.org");
        therapist5.setGender(User.Gender.FEMALE);
        therapist5.setId(2);
        therapist5.setLanguages(new ArrayList<>());
        therapist5.setName("42");
        therapist5.setPassword("Password");
        therapist5.setPersonalTherapy("42");
        therapist5.setPhoneNumber("8605550118");
        therapist5.setPhoto("Photo");
        therapist5.setRegistrationFinished(false);
        therapist5.setReservations(new ArrayList<>());
        therapist5.setSchedule(schedule5);
        therapist5.setSurname("Surname");
        therapist5.setThemes(new ArrayList<>());
        therapist5.setTherapeuticCommunity("42");
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
        therapist6.setDescription("Description");
        therapist6.setEducation("42");
        therapist6.setEmail("john.smith@example.org");
        therapist6.setGender(User.Gender.FEMALE);
        therapist6.setId(2);
        therapist6.setLanguages(new ArrayList<>());
        therapist6.setName("42");
        therapist6.setPassword("Password");
        therapist6.setPersonalTherapy("42");
        therapist6.setPhoneNumber("8605550118");
        therapist6.setPhoto("Photo");
        therapist6.setRegistrationFinished(false);
        therapist6.setReservations(new ArrayList<>());
        therapist6.setSchedule(schedule7);
        therapist6.setSurname("Surname");
        therapist6.setThemes(new ArrayList<>());
        therapist6.setTherapeuticCommunity("42");
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

        Schedule schedule9 = new Schedule();
        schedule9.setAvailableTimeCells(new ArrayList<>());
        schedule9.setId(1);
        schedule9.setTherapist(therapist7);

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
        therapist8.setSchedule(schedule9);
        therapist8.setSurname("Doe");
        therapist8.setThemes(new ArrayList<>());
        therapist8.setTherapeuticCommunity("Therapeutic Community");
        therapist8.setUserType(User.UserType.CLIENT);

        Schedule schedule10 = new Schedule();
        schedule10.setAvailableTimeCells(availableTimeCells);
        schedule10.setId(1);
        schedule10.setTherapist(therapist8);

        ArrayList<Schedule> scheduleList = new ArrayList<>();
        scheduleList.add(schedule10);
        when(scheduleRepository.findAll()).thenReturn(scheduleList);

        // Act
        List<ScheduleResponse> actualFindAllResult = scheduleServiceImpl.findAll();

        // Assert
        verify(scheduleRepository).findAll();
        assertEquals(1, actualFindAllResult.size());
        ScheduleResponse getResult = actualFindAllResult.get(0);
        assertEquals(1, getResult.id().intValue());
        assertEquals(2, getResult.timeCells().size());
    }
}
