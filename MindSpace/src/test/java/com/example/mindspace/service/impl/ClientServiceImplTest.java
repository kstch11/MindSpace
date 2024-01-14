package com.example.mindspace.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.mindspace.api.ClientResponse;
import com.example.mindspace.api.ClientTherapistRelationRequest;
import com.example.mindspace.api.LanguageResponse;
import com.example.mindspace.api.QuestionnaireRequest;
import com.example.mindspace.api.ReservationResponse;
import com.example.mindspace.api.TherapistResponse;
import com.example.mindspace.api.UserRequest;
import com.example.mindspace.exception.EntityNotFoundException;
import com.example.mindspace.model.Client;
import com.example.mindspace.model.Reservation;
import com.example.mindspace.model.Schedule;
import com.example.mindspace.model.Therapist;
import com.example.mindspace.model.TimeCell;
import com.example.mindspace.model.User;
import com.example.mindspace.repository.ClientRepository;
import com.example.mindspace.repository.ReservationRepository;
import com.example.mindspace.repository.TherapistRepository;
import com.example.mindspace.repository.TimeCellRepository;
import com.example.mindspace.security.UserPrincipal;

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

@ContextConfiguration(classes = {ClientServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ClientServiceImplTest {
  @MockBean
  private ClientRepository clientRepository;

  @Autowired
  private ClientServiceImpl clientServiceImpl;

  @MockBean
  private ReservationRepository reservationRepository;

  @MockBean
  private TherapistRepository therapistRepository;

  @MockBean
  private TimeCellRepository timeCellRepository;

  /**
   * Method under test: {@link ClientServiceImpl#cancelTherapist(Integer)}
   */
  @Test
  void testCancelTherapist() {
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
    therapist.setUserType(User.UserType.THERAPIST);

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
    therapist2.setUserType(User.UserType.THERAPIST);

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
    client.setTherapist(therapist2);
    client.setUserType(User.UserType.CLIENT);
    Optional<Client> ofResult = Optional.of(client);

    Schedule schedule2 = new Schedule();
    schedule2.setAvailableTimeCells(new ArrayList<>());
    schedule2.setId(1);

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
    therapist3.setUserType(User.UserType.THERAPIST);

    Schedule schedule3 = new Schedule();
    schedule3.setAvailableTimeCells(new ArrayList<>());
    schedule3.setId(1);
    schedule3.setTherapist(therapist3);

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
    therapist4.setSchedule(schedule3);
    therapist4.setSurname("Doe");
    therapist4.setThemes(new ArrayList<>());
    therapist4.setTherapeuticCommunity("Therapeutic Community");
    therapist4.setUserType(User.UserType.CLIENT);

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
    client2.setTherapist(therapist4);
    client2.setUserType(User.UserType.CLIENT);
    when(clientRepository.save(Mockito.<Client>any())).thenReturn(client2);
    when(clientRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

    // Act
    clientServiceImpl.cancelTherapist(1);

    // Assert
    verify(clientRepository).findById(Mockito.<Integer>any());
    verify(clientRepository).save(Mockito.<Client>any());
  }

  /**
   * Method under test: {@link ClientServiceImpl#cancelTherapist(Integer)}
   */
  @Test
  void testCancelTherapist2() {
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
    client.setTherapist(therapist2);
    client.setUserType(User.UserType.CLIENT);
    Optional<Client> ofResult = Optional.of(client);
    when(clientRepository.save(Mockito.<Client>any())).thenThrow(new EntityNotFoundException("An error occurred"));
    when(clientRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

    // Act and Assert
    assertThrows(EntityNotFoundException.class, () -> clientServiceImpl.cancelTherapist(1));
    verify(clientRepository).findById(Mockito.<Integer>any());
    verify(clientRepository).save(Mockito.<Client>any());
  }

  /**
   * Method under test:
   * {@link ClientServiceImpl#setRegistrationComplete(UserPrincipal)}
   */
  @Test
  void testSetRegistrationComplete() {
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
    client.setTherapist(therapist2);
    client.setUserType(User.UserType.CLIENT);
    Optional<Client> ofResult = Optional.of(client);

    Schedule schedule2 = new Schedule();
    schedule2.setAvailableTimeCells(new ArrayList<>());
    schedule2.setId(1);
    schedule2.setTherapist(new Therapist());

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

    Schedule schedule3 = new Schedule();
    schedule3.setAvailableTimeCells(new ArrayList<>());
    schedule3.setId(1);
    schedule3.setTherapist(therapist3);

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
    therapist4.setSchedule(schedule3);
    therapist4.setSurname("Doe");
    therapist4.setThemes(new ArrayList<>());
    therapist4.setTherapeuticCommunity("Therapeutic Community");
    therapist4.setUserType(User.UserType.CLIENT);

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
    client2.setTherapist(therapist4);
    client2.setUserType(User.UserType.CLIENT);
    when(clientRepository.save(Mockito.<Client>any())).thenReturn(client2);
    when(clientRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

    // Act
    clientServiceImpl
            .setRegistrationComplete(new UserPrincipal(1, "jane.doe@example.org", "iloveyou", new ArrayList<>()));

    // Assert
    verify(clientRepository).findById(Mockito.<Integer>any());
    verify(clientRepository).save(Mockito.<Client>any());
  }

  /**
   * Method under test:
   * {@link ClientServiceImpl#setRegistrationComplete(UserPrincipal)}
   */
  @Test
  void testSetRegistrationComplete2() {
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
    client.setTherapist(therapist2);
    client.setUserType(User.UserType.CLIENT);
    Optional<Client> ofResult = Optional.of(client);
    when(clientRepository.save(Mockito.<Client>any())).thenThrow(new EntityNotFoundException("An error occurred"));
    when(clientRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

    // Act and Assert
    assertThrows(EntityNotFoundException.class, () -> clientServiceImpl
            .setRegistrationComplete(new UserPrincipal(1, "jane.doe@example.org", "iloveyou", new ArrayList<>())));
    verify(clientRepository).findById(Mockito.<Integer>any());
    verify(clientRepository).save(Mockito.<Client>any());
  }


  // ok
  @Test
  void testGetClientDetails() {
    // Arrange
    Schedule schedule = new Schedule();
    schedule.setAvailableTimeCells(new ArrayList<>());
    schedule.setId(1);

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
    Optional<Client> ofResult = Optional.of(client);
    when(clientRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

    // Act
    ClientResponse actualClientDetails = clientServiceImpl.getClientDetails(1);

    // Assert
    verify(clientRepository).findById(Mockito.<Integer>any());
    assertEquals("6625550144", actualClientDetails.phone());
    assertEquals("Doe", actualClientDetails.surname());
    assertEquals("Name", actualClientDetails.name());
    assertEquals("jane.doe@example.org", actualClientDetails.email());
    assertEquals(1, actualClientDetails.id().intValue());
    assertEquals(1, actualClientDetails.therapistId().intValue());
    assertFalse(actualClientDetails.isTherapist());
    assertTrue(actualClientDetails.finishedRegistration());
  }

  /**
   * Method under test:
   * {@link ClientServiceImpl#saveQuestionnaire(UserPrincipal, QuestionnaireRequest)}
   */
  @Test
  void testSaveQuestionnaire() {
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
    client.setTherapist(therapist2);
    client.setUserType(User.UserType.CLIENT);
    Optional<Client> ofResult = Optional.of(client);

    Schedule schedule2 = new Schedule();
    schedule2.setAvailableTimeCells(new ArrayList<>());
    schedule2.setId(1);
    schedule2.setTherapist(new Therapist());

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

    Schedule schedule3 = new Schedule();
    schedule3.setAvailableTimeCells(new ArrayList<>());
    schedule3.setId(1);
    schedule3.setTherapist(therapist3);

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
    therapist4.setSchedule(schedule3);
    therapist4.setSurname("Doe");
    therapist4.setThemes(new ArrayList<>());
    therapist4.setTherapeuticCommunity("Therapeutic Community");
    therapist4.setUserType(User.UserType.CLIENT);

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
    client2.setTherapist(therapist4);
    client2.setUserType(User.UserType.CLIENT);
    when(clientRepository.save(Mockito.<Client>any())).thenReturn(client2);
    when(clientRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
    when(therapistRepository.findAll()).thenReturn(new ArrayList<>());
    UserPrincipal userPrincipal = new UserPrincipal(1, "jane.doe@example.org", "iloveyou", new ArrayList<>());

    // Act
    List<TherapistResponse> actualSaveQuestionnaireResult = clientServiceImpl.saveQuestionnaire(userPrincipal,
            new QuestionnaireRequest("Name", "Doe", "6625550144", new ArrayList<>()));

    // Assert
    verify(therapistRepository).findAll();
    verify(clientRepository).findById(Mockito.<Integer>any());
    verify(clientRepository).save(Mockito.<Client>any());
    assertTrue(actualSaveQuestionnaireResult.isEmpty());
  }

  /**
   * Method under test:
   * {@link ClientServiceImpl#saveQuestionnaire(UserPrincipal, QuestionnaireRequest)}
   */
  @Test
  void testSaveQuestionnaire2() {
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
    client.setTherapist(therapist2);
    client.setUserType(User.UserType.CLIENT);
    Optional<Client> ofResult = Optional.of(client);

    Schedule schedule2 = new Schedule();
    schedule2.setAvailableTimeCells(new ArrayList<>());
    schedule2.setId(1);
    schedule2.setTherapist(new Therapist());

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

    Schedule schedule3 = new Schedule();
    schedule3.setAvailableTimeCells(new ArrayList<>());
    schedule3.setId(1);
    schedule3.setTherapist(therapist3);

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
    therapist4.setSchedule(schedule3);
    therapist4.setSurname("Doe");
    therapist4.setThemes(new ArrayList<>());
    therapist4.setTherapeuticCommunity("Therapeutic Community");
    therapist4.setUserType(User.UserType.CLIENT);

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
    client2.setTherapist(therapist4);
    client2.setUserType(User.UserType.CLIENT);
    when(clientRepository.save(Mockito.<Client>any())).thenReturn(client2);
    when(clientRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
    when(therapistRepository.findAll()).thenThrow(new EntityNotFoundException("An error occurred"));
    UserPrincipal userPrincipal = new UserPrincipal(1, "jane.doe@example.org", "iloveyou", new ArrayList<>());

    // Act and Assert
    assertThrows(EntityNotFoundException.class, () -> clientServiceImpl.saveQuestionnaire(userPrincipal,
            new QuestionnaireRequest("Name", "Doe", "6625550144", new ArrayList<>())));
    verify(therapistRepository).findAll();
    verify(clientRepository).findById(Mockito.<Integer>any());
    verify(clientRepository).save(Mockito.<Client>any());
  }

  /**
   * Method under test:
   * {@link ClientServiceImpl#setNewTherapist(Integer, ClientTherapistRelationRequest)}
   */
  @Test
  void testSetNewTherapist() {
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
    client.setTherapist(therapist2);
    client.setUserType(User.UserType.CLIENT);
    Optional<Client> ofResult = Optional.of(client);

    Schedule schedule2 = new Schedule();
    schedule2.setAvailableTimeCells(new ArrayList<>());
    schedule2.setId(1);
    schedule2.setTherapist(new Therapist());

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

    Schedule schedule3 = new Schedule();
    schedule3.setAvailableTimeCells(new ArrayList<>());
    schedule3.setId(1);
    schedule3.setTherapist(therapist3);

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
    therapist4.setSchedule(schedule3);
    therapist4.setSurname("Doe");
    therapist4.setThemes(new ArrayList<>());
    therapist4.setTherapeuticCommunity("Therapeutic Community");
    therapist4.setUserType(User.UserType.CLIENT);

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
    client2.setTherapist(therapist4);
    client2.setUserType(User.UserType.CLIENT);
    when(clientRepository.save(Mockito.<Client>any())).thenReturn(client2);
    when(clientRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

    Schedule schedule4 = new Schedule();
    schedule4.setAvailableTimeCells(new ArrayList<>());
    schedule4.setId(1);
    schedule4.setTherapist(new Therapist());

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
    therapist5.setSchedule(schedule4);
    therapist5.setSurname("Doe");
    therapist5.setThemes(new ArrayList<>());
    therapist5.setTherapeuticCommunity("Therapeutic Community");
    therapist5.setUserType(User.UserType.CLIENT);

    Schedule schedule5 = new Schedule();
    schedule5.setAvailableTimeCells(new ArrayList<>());
    schedule5.setId(1);
    schedule5.setTherapist(therapist5);

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
    therapist6.setSchedule(schedule5);
    therapist6.setSurname("Doe");
    therapist6.setThemes(new ArrayList<>());
    therapist6.setTherapeuticCommunity("Therapeutic Community");
    therapist6.setUserType(User.UserType.CLIENT);
    Optional<Therapist> ofResult2 = Optional.of(therapist6);
    when(therapistRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult2);

    // Act
    clientServiceImpl.setNewTherapist(1, new ClientTherapistRelationRequest(1));

    // Assert
    verify(clientRepository).findById(Mockito.<Integer>any());
    verify(therapistRepository).findById(Mockito.<Integer>any());
    verify(clientRepository).save(Mockito.<Client>any());
  }

  /**
   * Method under test:
   * {@link ClientServiceImpl#setNewTherapist(Integer, ClientTherapistRelationRequest)}
   */
  @Test
  void testSetNewTherapist2() {
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
    client.setTherapist(therapist2);
    client.setUserType(User.UserType.CLIENT);
    Optional<Client> ofResult = Optional.of(client);
    when(clientRepository.save(Mockito.<Client>any())).thenThrow(new EntityNotFoundException("An error occurred"));
    when(clientRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

    Schedule schedule2 = new Schedule();
    schedule2.setAvailableTimeCells(new ArrayList<>());
    schedule2.setId(1);
    schedule2.setTherapist(new Therapist());

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

    Schedule schedule3 = new Schedule();
    schedule3.setAvailableTimeCells(new ArrayList<>());
    schedule3.setId(1);
    schedule3.setTherapist(therapist3);

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
    therapist4.setSchedule(schedule3);
    therapist4.setSurname("Doe");
    therapist4.setThemes(new ArrayList<>());
    therapist4.setTherapeuticCommunity("Therapeutic Community");
    therapist4.setUserType(User.UserType.CLIENT);
    Optional<Therapist> ofResult2 = Optional.of(therapist4);
    when(therapistRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult2);

    // Act and Assert
    assertThrows(EntityNotFoundException.class,
            () -> clientServiceImpl.setNewTherapist(1, new ClientTherapistRelationRequest(1)));
    verify(clientRepository).findById(Mockito.<Integer>any());
    verify(therapistRepository).findById(Mockito.<Integer>any());
    verify(clientRepository).save(Mockito.<Client>any());
  }

  /**
   * Method under test: {@link ClientServiceImpl#findAllReservations(Integer)}
   */
  @Test
  void testFindAllReservations() {
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
    client.setTherapist(therapist2);
    client.setUserType(User.UserType.CLIENT);
    Optional<Client> ofResult = Optional.of(client);
    when(clientRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

    // Act
    List<ReservationResponse> actualFindAllReservationsResult = clientServiceImpl.findAllReservations(1);

    // Assert
    verify(clientRepository).findById(Mockito.<Integer>any());
    assertTrue(actualFindAllReservationsResult.isEmpty());
  }

  /**
   * Method under test: {@link ClientServiceImpl#findAllReservations(Integer)}
   */
  @Test
  void testFindAllReservations2() {
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
    therapist2.setUserType(User.UserType.THERAPIST);

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
    therapist3.setUserType(User.UserType.THERAPIST);

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
    therapist4.setUserType(User.UserType.THERAPIST);

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
    therapist5.setUserType(User.UserType.THERAPIST);

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

    ArrayList<Reservation> reservations = new ArrayList<>();
    reservations.add(reservation2);

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
    therapist6.setUserType(User.UserType.THERAPIST);

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
    therapist7.setUserType(User.UserType.THERAPIST);

    Client client3 = new Client();
    client3.setEmail("jane.doe@example.org");
    client3.setGender(User.Gender.MALE);
    client3.setId(1);
    client3.setName("Name");
    client3.setPassword("iloveyou");
    client3.setPhoneNumber("6625550144");
    client3.setRegistrationFinished(true);
    client3.setReservations(reservations);
    client3.setSurname("Doe");
    client3.setTherapist(therapist7);
    client3.setUserType(User.UserType.CLIENT);
    Optional<Client> ofResult = Optional.of(client3);
    when(clientRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

    // Act
    List<ReservationResponse> actualFindAllReservationsResult = clientServiceImpl.findAllReservations(1);

    // Assert
    verify(clientRepository).findById(Mockito.<Integer>any());
    assertEquals(1, actualFindAllReservationsResult.size());
    ReservationResponse getResult = actualFindAllReservationsResult.get(0);
    assertEquals("1970-01-01T00:00", getResult.start());
    ClientResponse clientResponseResult = getResult.clientResponse();
    assertEquals("6625550144", clientResponseResult.phone());
    TherapistResponse therapistResult = getResult.therapist();
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
    assertEquals(1, getResult.id().intValue());
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
   * Method under test:
   * {@link ClientServiceImpl#updateClient(Integer, UserRequest)}
   */
  @Test
  void testUpdateClient() {
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
    client.setTherapist(therapist2);
    client.setUserType(User.UserType.CLIENT);
    Optional<Client> ofResult = Optional.of(client);

    Schedule schedule2 = new Schedule();
    schedule2.setAvailableTimeCells(new ArrayList<>());
    schedule2.setId(1);
    schedule2.setTherapist(new Therapist());

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

    Schedule schedule3 = new Schedule();
    schedule3.setAvailableTimeCells(new ArrayList<>());
    schedule3.setId(1);
    schedule3.setTherapist(therapist3);

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
    therapist4.setSchedule(schedule3);
    therapist4.setSurname("Doe");
    therapist4.setThemes(new ArrayList<>());
    therapist4.setTherapeuticCommunity("Therapeutic Community");
    therapist4.setUserType(User.UserType.CLIENT);

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
    client2.setTherapist(therapist4);
    client2.setUserType(User.UserType.CLIENT);
    when(clientRepository.save(Mockito.<Client>any())).thenReturn(client2);
    when(clientRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

    // Act
    clientServiceImpl.updateClient(1, new UserRequest("Name", "Doe", "42", "jane.doe@example.org"));

    // Assert
    verify(clientRepository).findById(Mockito.<Integer>any());
    verify(clientRepository).save(Mockito.<Client>any());
  }

  /**
   * Method under test:
   * {@link ClientServiceImpl#updateClient(Integer, UserRequest)}
   */
  @Test
  void testUpdateClient2() {
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
    client.setTherapist(therapist2);
    client.setUserType(User.UserType.CLIENT);
    Optional<Client> ofResult = Optional.of(client);
    when(clientRepository.save(Mockito.<Client>any())).thenThrow(new EntityNotFoundException("An error occurred"));
    when(clientRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

    // Act and Assert
    assertThrows(EntityNotFoundException.class,
            () -> clientServiceImpl.updateClient(1, new UserRequest("Name", "Doe", "42", "jane.doe@example.org")));
    verify(clientRepository).findById(Mockito.<Integer>any());
    verify(clientRepository).save(Mockito.<Client>any());
  }
}
