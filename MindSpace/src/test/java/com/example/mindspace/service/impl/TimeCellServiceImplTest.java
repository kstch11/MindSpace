package com.example.mindspace.service.impl;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.mindspace.model.Client;
import com.example.mindspace.model.Reservation;
import com.example.mindspace.model.Schedule;
import com.example.mindspace.model.Therapist;
import com.example.mindspace.model.TimeCell;
import com.example.mindspace.model.User;
import com.example.mindspace.repository.TimeCellRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {TimeCellServiceImpl.class})
@ExtendWith(SpringExtension.class)
class TimeCellServiceImplTest {
    @MockBean
    private TimeCellRepository timeCellRepository;

    @Autowired
    private TimeCellServiceImpl timeCellServiceImpl;

    /**
     * Method under test: {@link TimeCellServiceImpl#generateTimeCells(Schedule)}
     */
    @Test
    void testGenerateTimeCells() {
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

        Schedule schedule4 = new Schedule();
        schedule4.setAvailableTimeCells(new ArrayList<>());
        schedule4.setId(1);
        schedule4.setTherapist(therapist6);

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

        Schedule schedule5 = new Schedule();
        schedule5.setAvailableTimeCells(new ArrayList<>());
        schedule5.setId(1);
        schedule5.setTherapist(therapist7);

        TimeCell timeCell3 = new TimeCell();
        timeCell3.setEndTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        timeCell3.setId(1);
        timeCell3.setReservation(reservation2);
        timeCell3.setSchedule(schedule5);
        timeCell3.setStartTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(timeCellRepository.save(Mockito.<TimeCell>any())).thenReturn(timeCell3);

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
        therapist8.setSchedule(new Schedule());
        therapist8.setSurname("Doe");
        therapist8.setThemes(new ArrayList<>());
        therapist8.setTherapeuticCommunity("Therapeutic Community");
        therapist8.setUserType(User.UserType.CLIENT);

        Schedule schedule6 = new Schedule();
        schedule6.setAvailableTimeCells(new ArrayList<>());
        schedule6.setId(1);
        schedule6.setTherapist(therapist8);

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
        therapist9.setSchedule(schedule6);
        therapist9.setSurname("Doe");
        therapist9.setThemes(new ArrayList<>());
        therapist9.setTherapeuticCommunity("Therapeutic Community");
        therapist9.setUserType(User.UserType.CLIENT);

        Schedule schedule7 = new Schedule();
        schedule7.setAvailableTimeCells(new ArrayList<>());
        schedule7.setId(1);
        schedule7.setTherapist(therapist9);

        // Act
        timeCellServiceImpl.generateTimeCells(schedule7);

        // Assert
        verify(timeCellRepository, atLeast(1)).save(Mockito.<TimeCell>any());
    }

    /**
     * Method under test: {@link TimeCellServiceImpl#deleteExpiredTimeCells()}
     */
    @Test
    void testDeleteExpiredTimeCells() {
        // Arrange
        when(timeCellRepository.findByEndTimeBefore(Mockito.<LocalDateTime>any())).thenReturn(new ArrayList<>());
        doNothing().when(timeCellRepository).deleteAll(Mockito.<Iterable<TimeCell>>any());

        // Act
        timeCellServiceImpl.deleteExpiredTimeCells();

        // Assert that nothing has changed
        verify(timeCellRepository).findByEndTimeBefore(Mockito.<LocalDateTime>any());
        verify(timeCellRepository).deleteAll(Mockito.<Iterable<TimeCell>>any());
    }
}
