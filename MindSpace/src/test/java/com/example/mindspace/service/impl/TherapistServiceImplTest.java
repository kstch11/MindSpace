package com.example.mindspace.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.mindspace.api.ClientResponse;
import com.example.mindspace.api.CreateReviewRequest;
import com.example.mindspace.api.CreateReviewResponse;
import com.example.mindspace.api.LanguageResponse;
import com.example.mindspace.api.ReservationResponse;
import com.example.mindspace.api.ReviewResponse;
import com.example.mindspace.api.TherapistResponse;
import com.example.mindspace.api.TopicResponse;
import com.example.mindspace.api.UserRequest;
import com.example.mindspace.exception.EntityNotFoundException;
import com.example.mindspace.model.Client;
import com.example.mindspace.model.Reservation;
import com.example.mindspace.model.Review;
import com.example.mindspace.model.Schedule;
import com.example.mindspace.model.SpokenLanguage;
import com.example.mindspace.model.Theme;
import com.example.mindspace.model.Therapist;
import com.example.mindspace.model.TimeCell;
import com.example.mindspace.model.User;
import com.example.mindspace.repository.ClientRepository;
import com.example.mindspace.repository.ReviewRepository;
import com.example.mindspace.repository.ThemeRepository;
import com.example.mindspace.repository.TherapistRepository;

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

@ContextConfiguration(classes = {TherapistServiceImpl.class})
@ExtendWith(SpringExtension.class)
class TherapistServiceImplTest {
    @MockBean
    private ClientRepository clientRepository;

    @MockBean
    private ReviewRepository reviewRepository;

    @MockBean
    private ThemeRepository themeRepository;

    @MockBean
    private TherapistRepository therapistRepository;

    @Autowired
    private TherapistServiceImpl therapistServiceImpl;

    /**
     * Method under test: {@link TherapistServiceImpl#getTherapistDetails(Integer)}
     */
    @Test
    void testGetTherapistDetails() {
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
        therapist2.setUserType(User.UserType.CLIENT);
        Optional<Therapist> ofResult = Optional.of(therapist2);
        when(therapistRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        TherapistResponse actualTherapistDetails = therapistServiceImpl.getTherapistDetails(1);

        // Assert
        verify(therapistRepository).findById(Mockito.<Integer>any());
        assertEquals("6625550144", actualTherapistDetails.phone());
        assertEquals("Doe", actualTherapistDetails.surname());
        assertEquals("Education", actualTherapistDetails.education());
        assertEquals("Name", actualTherapistDetails.name());
        assertEquals("Personal Therapy", actualTherapistDetails.personalTherapy());
        assertEquals("The characteristics of someone or something", actualTherapistDetails.description());
        assertEquals("Therapeutic Community", actualTherapistDetails.therapeuticCommunity());
        assertEquals("alice.liddell@example.org", actualTherapistDetails.photo());
        assertEquals("jane.doe@example.org", actualTherapistDetails.email());
        assertEquals(1, actualTherapistDetails.id().intValue());
        assertTrue(actualTherapistDetails.approved());
        assertTrue(actualTherapistDetails.finishedRegistration());
        assertTrue(actualTherapistDetails.isTherapist());
        List<TopicResponse> topicsResult = actualTherapistDetails.topics();
        assertTrue(topicsResult.isEmpty());
        assertSame(topicsResult, actualTherapistDetails.languages());
    }

    /**
     * Method under test: {@link TherapistServiceImpl#getTherapistDetails(Integer)}
     */
    @Test
    void testGetTherapistDetails2() {
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

        SpokenLanguage spokenLanguage = new SpokenLanguage();
        spokenLanguage.setId(1);
        spokenLanguage.setName("Name");
        spokenLanguage.setTherapist(therapist2);

        ArrayList<SpokenLanguage> languages = new ArrayList<>();
        languages.add(spokenLanguage);

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
        therapist4.setLanguages(languages);
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
        Optional<Therapist> ofResult = Optional.of(therapist4);
        when(therapistRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        TherapistResponse actualTherapistDetails = therapistServiceImpl.getTherapistDetails(1);

        // Assert
        verify(therapistRepository).findById(Mockito.<Integer>any());
        assertEquals("6625550144", actualTherapistDetails.phone());
        assertEquals("Doe", actualTherapistDetails.surname());
        assertEquals("Education", actualTherapistDetails.education());
        List<LanguageResponse> languagesResult = actualTherapistDetails.languages();
        assertEquals(1, languagesResult.size());
        LanguageResponse getResult = languagesResult.get(0);
        assertEquals("Name", getResult.name());
        assertEquals("Name", actualTherapistDetails.name());
        assertEquals("Personal Therapy", actualTherapistDetails.personalTherapy());
        assertEquals("The characteristics of someone or something", actualTherapistDetails.description());
        assertEquals("Therapeutic Community", actualTherapistDetails.therapeuticCommunity());
        assertEquals("alice.liddell@example.org", actualTherapistDetails.photo());
        assertEquals("jane.doe@example.org", actualTherapistDetails.email());
        assertEquals(1, getResult.id().intValue());
        assertEquals(1, actualTherapistDetails.id().intValue());
        assertTrue(actualTherapistDetails.approved());
        assertTrue(actualTherapistDetails.finishedRegistration());
        assertTrue(actualTherapistDetails.isTherapist());
        assertTrue(actualTherapistDetails.topics().isEmpty());
    }

    /**
     * Method under test: {@link TherapistServiceImpl#getTherapistDetails(Integer)}
     */
    @Test
    void testGetTherapistDetails3() {
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

        SpokenLanguage spokenLanguage = new SpokenLanguage();
        spokenLanguage.setId(1);
        spokenLanguage.setName("Name");
        spokenLanguage.setTherapist(therapist2);

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

        Schedule schedule2 = new Schedule();
        schedule2.setAvailableTimeCells(new ArrayList<>());
        schedule2.setId(2);
        schedule2.setTherapist(therapist3);

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
        therapist4.setSchedule(schedule2);
        therapist4.setSurname("Surname");
        therapist4.setThemes(new ArrayList<>());
        therapist4.setTherapeuticCommunity("42");
        therapist4.setUserType(User.UserType.THERAPIST);

        SpokenLanguage spokenLanguage2 = new SpokenLanguage();
        spokenLanguage2.setId(2);
        spokenLanguage2.setName("42");
        spokenLanguage2.setTherapist(therapist4);

        ArrayList<SpokenLanguage> languages = new ArrayList<>();
        languages.add(spokenLanguage2);
        languages.add(spokenLanguage);

        Schedule schedule3 = new Schedule();
        schedule3.setAvailableTimeCells(new ArrayList<>());
        schedule3.setId(1);
        schedule3.setTherapist(new Therapist());

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
        therapist5.setSchedule(schedule3);
        therapist5.setSurname("Doe");
        therapist5.setThemes(new ArrayList<>());
        therapist5.setTherapeuticCommunity("Therapeutic Community");
        therapist5.setUserType(User.UserType.CLIENT);

        Schedule schedule4 = new Schedule();
        schedule4.setAvailableTimeCells(new ArrayList<>());
        schedule4.setId(1);
        schedule4.setTherapist(therapist5);

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
        therapist6.setLanguages(languages);
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
        Optional<Therapist> ofResult = Optional.of(therapist6);
        when(therapistRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        TherapistResponse actualTherapistDetails = therapistServiceImpl.getTherapistDetails(1);

        // Assert
        verify(therapistRepository).findById(Mockito.<Integer>any());
        List<LanguageResponse> languagesResult = actualTherapistDetails.languages();
        assertEquals(2, languagesResult.size());
        LanguageResponse getResult = languagesResult.get(0);
        assertEquals("42", getResult.name());
        assertEquals("6625550144", actualTherapistDetails.phone());
        assertEquals("Doe", actualTherapistDetails.surname());
        assertEquals("Education", actualTherapistDetails.education());
        LanguageResponse getResult2 = languagesResult.get(1);
        assertEquals("Name", getResult2.name());
        assertEquals("Name", actualTherapistDetails.name());
        assertEquals("Personal Therapy", actualTherapistDetails.personalTherapy());
        assertEquals("The characteristics of someone or something", actualTherapistDetails.description());
        assertEquals("Therapeutic Community", actualTherapistDetails.therapeuticCommunity());
        assertEquals("alice.liddell@example.org", actualTherapistDetails.photo());
        assertEquals("jane.doe@example.org", actualTherapistDetails.email());
        assertEquals(1, getResult2.id().intValue());
        assertEquals(1, actualTherapistDetails.id().intValue());
        assertEquals(2, getResult.id().intValue());
        assertTrue(actualTherapistDetails.approved());
        assertTrue(actualTherapistDetails.finishedRegistration());
        assertTrue(actualTherapistDetails.isTherapist());
        assertTrue(actualTherapistDetails.topics().isEmpty());
    }

    /**
     * Method under test: {@link TherapistServiceImpl#getTherapistDetails(Integer)}
     */
    @Test
    void testGetTherapistDetails4() {
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

        Schedule schedule2 = new Schedule();
        schedule2.setAvailableTimeCells(new ArrayList<>());
        schedule2.setId(1);
        schedule2.setTherapist(therapist);

        Theme theme = new Theme();
        theme.setId(1);
        theme.setName("Name");
        theme.setTherapists(new ArrayList<>());

        ArrayList<Theme> themes = new ArrayList<>();
        themes.add(theme);

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
        therapist2.setThemes(themes);
        therapist2.setTherapeuticCommunity("Therapeutic Community");
        therapist2.setUserType(User.UserType.CLIENT);
        Optional<Therapist> ofResult = Optional.of(therapist2);
        when(therapistRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        TherapistResponse actualTherapistDetails = therapistServiceImpl.getTherapistDetails(1);

        // Assert
        verify(therapistRepository).findById(Mockito.<Integer>any());
        assertEquals("6625550144", actualTherapistDetails.phone());
        assertEquals("Doe", actualTherapistDetails.surname());
        assertEquals("Education", actualTherapistDetails.education());
        assertEquals("Name", actualTherapistDetails.name());
        List<TopicResponse> topicsResult = actualTherapistDetails.topics();
        assertEquals(1, topicsResult.size());
        TopicResponse getResult = topicsResult.get(0);
        assertEquals("Name", getResult.name());
        assertEquals("Personal Therapy", actualTherapistDetails.personalTherapy());
        assertEquals("The characteristics of someone or something", actualTherapistDetails.description());
        assertEquals("Therapeutic Community", actualTherapistDetails.therapeuticCommunity());
        assertEquals("alice.liddell@example.org", actualTherapistDetails.photo());
        assertEquals("jane.doe@example.org", actualTherapistDetails.email());
        assertEquals(1, actualTherapistDetails.id().intValue());
        assertEquals(1, getResult.id().intValue());
        assertTrue(actualTherapistDetails.approved());
        assertTrue(actualTherapistDetails.finishedRegistration());
        assertTrue(actualTherapistDetails.isTherapist());
        assertTrue(actualTherapistDetails.languages().isEmpty());
    }

    /**
     * Method under test: {@link TherapistServiceImpl#getTherapistDetails(Integer)}
     */
    @Test
    void testGetTherapistDetails5() {
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

        Schedule schedule2 = new Schedule();
        schedule2.setAvailableTimeCells(new ArrayList<>());
        schedule2.setId(1);
        schedule2.setTherapist(therapist);

        Theme theme = new Theme();
        theme.setId(1);
        theme.setName("Name");
        theme.setTherapists(new ArrayList<>());

        Theme theme2 = new Theme();
        theme2.setId(2);
        theme2.setName("42");
        theme2.setTherapists(new ArrayList<>());

        ArrayList<Theme> themes = new ArrayList<>();
        themes.add(theme2);
        themes.add(theme);

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
        therapist2.setThemes(themes);
        therapist2.setTherapeuticCommunity("Therapeutic Community");
        therapist2.setUserType(User.UserType.CLIENT);
        Optional<Therapist> ofResult = Optional.of(therapist2);
        when(therapistRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        TherapistResponse actualTherapistDetails = therapistServiceImpl.getTherapistDetails(1);

        // Assert
        verify(therapistRepository).findById(Mockito.<Integer>any());
        List<TopicResponse> topicsResult = actualTherapistDetails.topics();
        assertEquals(2, topicsResult.size());
        TopicResponse getResult = topicsResult.get(0);
        assertEquals("42", getResult.name());
        assertEquals("6625550144", actualTherapistDetails.phone());
        assertEquals("Doe", actualTherapistDetails.surname());
        assertEquals("Education", actualTherapistDetails.education());
        assertEquals("Name", actualTherapistDetails.name());
        TopicResponse getResult2 = topicsResult.get(1);
        assertEquals("Name", getResult2.name());
        assertEquals("Personal Therapy", actualTherapistDetails.personalTherapy());
        assertEquals("The characteristics of someone or something", actualTherapistDetails.description());
        assertEquals("Therapeutic Community", actualTherapistDetails.therapeuticCommunity());
        assertEquals("alice.liddell@example.org", actualTherapistDetails.photo());
        assertEquals("jane.doe@example.org", actualTherapistDetails.email());
        assertEquals(1, actualTherapistDetails.id().intValue());
        assertEquals(1, getResult2.id().intValue());
        assertEquals(2, getResult.id().intValue());
        assertTrue(actualTherapistDetails.approved());
        assertTrue(actualTherapistDetails.finishedRegistration());
        assertTrue(actualTherapistDetails.isTherapist());
        assertTrue(actualTherapistDetails.languages().isEmpty());
    }

    /**
     * Method under test: {@link TherapistServiceImpl#getTherapistDetails(Integer)}
     */
    @Test
    void testGetTherapistDetails6() {
        // Arrange
        Optional<Therapist> emptyResult = Optional.empty();
        when(therapistRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> therapistServiceImpl.getTherapistDetails(1));
        verify(therapistRepository).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link TherapistServiceImpl#findAllReservations(Integer)}
     */
    @Test
    void testFindAllReservations() {
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
        therapist2.setUserType(User.UserType.CLIENT);
        Optional<Therapist> ofResult = Optional.of(therapist2);
        when(therapistRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        List<ReservationResponse> actualFindAllReservationsResult = therapistServiceImpl.findAllReservations(1);

        // Assert
        verify(therapistRepository).findById(Mockito.<Integer>any());
        assertTrue(actualFindAllReservationsResult.isEmpty());
    }

    /**
     * Method under test: {@link TherapistServiceImpl#findAllReservations(Integer)}
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

        ArrayList<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation2);

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

        Schedule schedule5 = new Schedule();
        schedule5.setAvailableTimeCells(new ArrayList<>());
        schedule5.setId(1);
        schedule5.setTherapist(therapist6);

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
        therapist7.setReservations(reservations);
        therapist7.setSchedule(schedule5);
        therapist7.setSurname("Doe");
        therapist7.setThemes(new ArrayList<>());
        therapist7.setTherapeuticCommunity("Therapeutic Community");
        therapist7.setUserType(User.UserType.CLIENT);
        Optional<Therapist> ofResult = Optional.of(therapist7);
        when(therapistRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        List<ReservationResponse> actualFindAllReservationsResult = therapistServiceImpl.findAllReservations(1);

        // Assert
        verify(therapistRepository).findById(Mockito.<Integer>any());
        assertEquals(1, actualFindAllReservationsResult.size());
        ReservationResponse getResult = actualFindAllReservationsResult.get(0);
        assertEquals("1970-01-01T12:00:00", getResult.start());
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
     * Method under test: {@link TherapistServiceImpl#findAllReservations(Integer)}
     */
    @Test
    void testFindAllReservations3() {
        // Arrange
        Optional<Therapist> emptyResult = Optional.empty();
        when(therapistRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> therapistServiceImpl.findAllReservations(1));
        verify(therapistRepository).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link TherapistServiceImpl#findAllClients(Integer)}
     */
    @Test
    void testFindAllClients() {
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
        therapist2.setUserType(User.UserType.CLIENT);
        Optional<Therapist> ofResult = Optional.of(therapist2);
        when(therapistRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        List<ClientResponse> actualFindAllClientsResult = therapistServiceImpl.findAllClients(1);

        // Assert
        verify(therapistRepository).findById(Mockito.<Integer>any());
        assertTrue(actualFindAllClientsResult.isEmpty());
    }

    /**
     * Method under test: {@link TherapistServiceImpl#findAllClients(Integer)}
     */
    @Test
    void testFindAllClients2() {
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

        ArrayList<Client> clients = new ArrayList<>();
        clients.add(client);

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
        therapist4.setClients(clients);
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
        Optional<Therapist> ofResult = Optional.of(therapist4);
        when(therapistRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        List<ClientResponse> actualFindAllClientsResult = therapistServiceImpl.findAllClients(1);

        // Assert
        verify(therapistRepository).findById(Mockito.<Integer>any());
        assertEquals(1, actualFindAllClientsResult.size());
    }

    /**
     * Method under test: {@link TherapistServiceImpl#findAllClients(Integer)}
     */
    @Test
    void testFindAllClients3() {
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

        Schedule schedule2 = new Schedule();
        schedule2.setAvailableTimeCells(new ArrayList<>());
        schedule2.setId(2);
        schedule2.setTherapist(therapist3);

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
        therapist4.setSchedule(schedule2);
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

        ArrayList<Client> clients = new ArrayList<>();
        clients.add(client2);
        clients.add(client);

        Schedule schedule3 = new Schedule();
        schedule3.setAvailableTimeCells(new ArrayList<>());
        schedule3.setId(1);
        schedule3.setTherapist(new Therapist());

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
        therapist5.setSchedule(schedule3);
        therapist5.setSurname("Doe");
        therapist5.setThemes(new ArrayList<>());
        therapist5.setTherapeuticCommunity("Therapeutic Community");
        therapist5.setUserType(User.UserType.CLIENT);

        Schedule schedule4 = new Schedule();
        schedule4.setAvailableTimeCells(new ArrayList<>());
        schedule4.setId(1);
        schedule4.setTherapist(therapist5);

        Therapist therapist6 = new Therapist();
        therapist6.setApproved(true);
        therapist6.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist6.setCertificates(new ArrayList<>());
        therapist6.setClients(clients);
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
        Optional<Therapist> ofResult = Optional.of(therapist6);
        when(therapistRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        List<ClientResponse> actualFindAllClientsResult = therapistServiceImpl.findAllClients(1);

        // Assert
        verify(therapistRepository).findById(Mockito.<Integer>any());
        assertEquals(2, actualFindAllClientsResult.size());
    }

    /**
     * Method under test: {@link TherapistServiceImpl#findAllClients(Integer)}
     */
    @Test
    void testFindAllClients4() {
        // Arrange
        Optional<Therapist> emptyResult = Optional.empty();
        when(therapistRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> therapistServiceImpl.findAllClients(1));
        verify(therapistRepository).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test:
     * {@link TherapistServiceImpl#updateTherapist(Integer, UserRequest)}
     */
    @Test
    void testUpdateTherapist() {
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
        therapist2.setUserType(User.UserType.CLIENT);
        Optional<Therapist> ofResult = Optional.of(therapist2);

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
        therapist3.setSchedule(new Schedule());
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

        Schedule schedule4 = new Schedule();
        schedule4.setAvailableTimeCells(new ArrayList<>());
        schedule4.setId(1);
        schedule4.setTherapist(therapist4);

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
        when(therapistRepository.save(Mockito.<Therapist>any())).thenReturn(therapist5);
        when(therapistRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        therapistServiceImpl.updateTherapist(1, new UserRequest("Name", "Doe", "42", "jane.doe@example.org"));

        // Assert that nothing has changed
        verify(therapistRepository).findById(Mockito.<Integer>any());
        verify(therapistRepository).save(Mockito.<Therapist>any());
    }

    /**
     * Method under test:
     * {@link TherapistServiceImpl#updateTherapist(Integer, UserRequest)}
     */
    @Test
    void testUpdateTherapist2() {
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
        therapist2.setUserType(User.UserType.CLIENT);
        Optional<Therapist> ofResult = Optional.of(therapist2);
        when(therapistRepository.save(Mockito.<Therapist>any()))
                .thenThrow(new EntityNotFoundException("An error occurred"));
        when(therapistRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(EntityNotFoundException.class,
                () -> therapistServiceImpl.updateTherapist(1, new UserRequest("Name", "Doe", "42", "jane.doe@example.org")));
        verify(therapistRepository).findById(Mockito.<Integer>any());
        verify(therapistRepository).save(Mockito.<Therapist>any());
    }

    /**
     * Method under test:
     * {@link TherapistServiceImpl#createReview(Integer, CreateReviewRequest)}
     */
    @Test
    void testCreateReview() {
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
        therapist2.setUserType(User.UserType.CLIENT);
        Optional<Therapist> ofResult = Optional.of(therapist2);
        when(therapistRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

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
        therapist3.setSchedule(new Schedule());
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
        client.setTherapist(therapist4);
        client.setUserType(User.UserType.CLIENT);
        Optional<Client> ofResult2 = Optional.of(client);
        when(clientRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult2);

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

        Schedule schedule4 = new Schedule();
        schedule4.setAvailableTimeCells(new ArrayList<>());
        schedule4.setId(1);
        schedule4.setTherapist(therapist5);

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

        Client author = new Client();
        author.setEmail("jane.doe@example.org");
        author.setGender(User.Gender.MALE);
        author.setId(1);
        author.setName("Name");
        author.setPassword("iloveyou");
        author.setPhoneNumber("6625550144");
        author.setRegistrationFinished(true);
        author.setReservations(new ArrayList<>());
        author.setSurname("Doe");
        author.setTherapist(therapist6);
        author.setUserType(User.UserType.CLIENT);

        Schedule schedule5 = new Schedule();
        schedule5.setAvailableTimeCells(new ArrayList<>());
        schedule5.setId(1);
        schedule5.setTherapist(new Therapist());

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
        therapist7.setSchedule(schedule5);
        therapist7.setSurname("Doe");
        therapist7.setThemes(new ArrayList<>());
        therapist7.setTherapeuticCommunity("Therapeutic Community");
        therapist7.setUserType(User.UserType.CLIENT);

        Schedule schedule6 = new Schedule();
        schedule6.setAvailableTimeCells(new ArrayList<>());
        schedule6.setId(1);
        schedule6.setTherapist(therapist7);

        Therapist recipient = new Therapist();
        recipient.setApproved(true);
        recipient.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        recipient.setCertificates(new ArrayList<>());
        recipient.setClients(new ArrayList<>());
        recipient.setDescription("The characteristics of someone or something");
        recipient.setEducation("Education");
        recipient.setEmail("jane.doe@example.org");
        recipient.setGender(User.Gender.MALE);
        recipient.setId(1);
        recipient.setLanguages(new ArrayList<>());
        recipient.setName("Name");
        recipient.setPassword("iloveyou");
        recipient.setPersonalTherapy("Personal Therapy");
        recipient.setPhoneNumber("6625550144");
        recipient.setPhoto("alice.liddell@example.org");
        recipient.setRegistrationFinished(true);
        recipient.setReservations(new ArrayList<>());
        recipient.setSchedule(schedule6);
        recipient.setSurname("Doe");
        recipient.setThemes(new ArrayList<>());
        recipient.setTherapeuticCommunity("Therapeutic Community");
        recipient.setUserType(User.UserType.CLIENT);

        Review review = new Review();
        review.setAuthor(author);
        review.setId(1);
        review.setRecipient(recipient);
        review.setText("Text");
        when(reviewRepository.save(Mockito.<Review>any())).thenReturn(review);

        // Act
        CreateReviewResponse actualCreateReviewResult = therapistServiceImpl.createReview(1,
                new CreateReviewRequest(1, "Text"));

        // Assert
        verify(clientRepository).findById(Mockito.<Integer>any());
        verify(therapistRepository).findById(Mockito.<Integer>any());
        verify(reviewRepository).save(Mockito.<Review>any());
        assertEquals(1, actualCreateReviewResult.id().intValue());
    }

    /**
     * Method under test:
     * {@link TherapistServiceImpl#createReview(Integer, CreateReviewRequest)}
     */
    @Test
    void testCreateReview2() {
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
        therapist2.setUserType(User.UserType.CLIENT);
        Optional<Therapist> ofResult = Optional.of(therapist2);
        when(therapistRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

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
        therapist3.setSchedule(new Schedule());
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
        client.setTherapist(therapist4);
        client.setUserType(User.UserType.CLIENT);
        Optional<Client> ofResult2 = Optional.of(client);
        when(clientRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult2);
        when(reviewRepository.save(Mockito.<Review>any())).thenThrow(new EntityNotFoundException("An error occurred"));

        // Act and Assert
        assertThrows(EntityNotFoundException.class,
                () -> therapistServiceImpl.createReview(1, new CreateReviewRequest(1, "Text")));
        verify(clientRepository).findById(Mockito.<Integer>any());
        verify(therapistRepository).findById(Mockito.<Integer>any());
        verify(reviewRepository).save(Mockito.<Review>any());
    }

    /**
     * Method under test: {@link TherapistServiceImpl#getAllReviews(Integer)}
     */
    @Test
    void testGetAllReviews() {
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
        therapist2.setUserType(User.UserType.CLIENT);
        Optional<Therapist> ofResult = Optional.of(therapist2);
        when(therapistRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        when(reviewRepository.findReviewsByRecipient(Mockito.<Therapist>any())).thenReturn(new ArrayList<>());

        // Act
        List<ReviewResponse> actualAllReviews = therapistServiceImpl.getAllReviews(1);

        // Assert
        verify(reviewRepository).findReviewsByRecipient(Mockito.<Therapist>any());
        verify(therapistRepository).findById(Mockito.<Integer>any());
        assertTrue(actualAllReviews.isEmpty());
    }

    /**
     * Method under test: {@link TherapistServiceImpl#getAllReviews(Integer)}
     */
    @Test
    void testGetAllReviews2() {
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
        therapist2.setUserType(User.UserType.CLIENT);
        Optional<Therapist> ofResult = Optional.of(therapist2);
        when(therapistRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        when(reviewRepository.findReviewsByRecipient(Mockito.<Therapist>any()))
                .thenThrow(new EntityNotFoundException("An error occurred"));

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> therapistServiceImpl.getAllReviews(1));
        verify(reviewRepository).findReviewsByRecipient(Mockito.<Therapist>any());
        verify(therapistRepository).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link TherapistServiceImpl#getAllReviews(Integer)}
     */
    @Test
    void testGetAllReviews3() {
        // Arrange
        Optional<Therapist> emptyResult = Optional.empty();
        when(therapistRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> therapistServiceImpl.getAllReviews(1));
        verify(therapistRepository).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link TherapistServiceImpl#getAllReviews(Integer)}
     */
    @Test
    void testGetAllReviews4() {
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
        therapist2.setUserType(User.UserType.CLIENT);
        Optional<Therapist> ofResult = Optional.of(therapist2);
        when(therapistRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

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

        Client author = new Client();
        author.setEmail("jane.doe@example.org");
        author.setGender(User.Gender.MALE);
        author.setId(1);
        author.setName("Name");
        author.setPassword("iloveyou");
        author.setPhoneNumber("6625550144");
        author.setRegistrationFinished(true);
        author.setReservations(new ArrayList<>());
        author.setSurname("Doe");
        author.setTherapist(therapist3);
        author.setUserType(User.UserType.CLIENT);

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

        Schedule schedule4 = new Schedule();
        schedule4.setAvailableTimeCells(new ArrayList<>());
        schedule4.setId(1);
        schedule4.setTherapist(therapist4);

        Therapist recipient = new Therapist();
        recipient.setApproved(true);
        recipient.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        recipient.setCertificates(new ArrayList<>());
        recipient.setClients(new ArrayList<>());
        recipient.setDescription("The characteristics of someone or something");
        recipient.setEducation("Education");
        recipient.setEmail("jane.doe@example.org");
        recipient.setGender(User.Gender.MALE);
        recipient.setId(1);
        recipient.setLanguages(new ArrayList<>());
        recipient.setName("Name");
        recipient.setPassword("iloveyou");
        recipient.setPersonalTherapy("Personal Therapy");
        recipient.setPhoneNumber("6625550144");
        recipient.setPhoto("alice.liddell@example.org");
        recipient.setRegistrationFinished(true);
        recipient.setReservations(new ArrayList<>());
        recipient.setSchedule(schedule4);
        recipient.setSurname("Doe");
        recipient.setThemes(new ArrayList<>());
        recipient.setTherapeuticCommunity("Therapeutic Community");
        recipient.setUserType(User.UserType.CLIENT);

        Review review = new Review();
        review.setAuthor(author);
        review.setId(1);
        review.setRecipient(recipient);
        review.setText("Text");

        ArrayList<Review> reviewList = new ArrayList<>();
        reviewList.add(review);
        when(reviewRepository.findReviewsByRecipient(Mockito.<Therapist>any())).thenReturn(reviewList);

        // Act
        List<ReviewResponse> actualAllReviews = therapistServiceImpl.getAllReviews(1);

        // Assert
        verify(reviewRepository).findReviewsByRecipient(Mockito.<Therapist>any());
        verify(therapistRepository).findById(Mockito.<Integer>any());
        assertEquals(1, actualAllReviews.size());
        ReviewResponse getResult = actualAllReviews.get(0);
        assertEquals("Text", getResult.text());
        assertEquals(1, getResult.author().intValue());
        assertEquals(1, getResult.therapistId().intValue());
    }

    /**
     * Method under test: {@link TherapistServiceImpl#getAllReviews(Integer)}
     */
    @Test
    void testGetAllReviews5() {
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
        therapist2.setUserType(User.UserType.CLIENT);
        Optional<Therapist> ofResult = Optional.of(therapist2);
        when(therapistRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

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

        Client author = new Client();
        author.setEmail("jane.doe@example.org");
        author.setGender(User.Gender.MALE);
        author.setId(1);
        author.setName("Name");
        author.setPassword("iloveyou");
        author.setPhoneNumber("6625550144");
        author.setRegistrationFinished(true);
        author.setReservations(new ArrayList<>());
        author.setSurname("Doe");
        author.setTherapist(therapist3);
        author.setUserType(User.UserType.CLIENT);

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

        Schedule schedule4 = new Schedule();
        schedule4.setAvailableTimeCells(new ArrayList<>());
        schedule4.setId(1);
        schedule4.setTherapist(therapist4);

        Therapist recipient = new Therapist();
        recipient.setApproved(true);
        recipient.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        recipient.setCertificates(new ArrayList<>());
        recipient.setClients(new ArrayList<>());
        recipient.setDescription("The characteristics of someone or something");
        recipient.setEducation("Education");
        recipient.setEmail("jane.doe@example.org");
        recipient.setGender(User.Gender.MALE);
        recipient.setId(1);
        recipient.setLanguages(new ArrayList<>());
        recipient.setName("Name");
        recipient.setPassword("iloveyou");
        recipient.setPersonalTherapy("Personal Therapy");
        recipient.setPhoneNumber("6625550144");
        recipient.setPhoto("alice.liddell@example.org");
        recipient.setRegistrationFinished(true);
        recipient.setReservations(new ArrayList<>());
        recipient.setSchedule(schedule4);
        recipient.setSurname("Doe");
        recipient.setThemes(new ArrayList<>());
        recipient.setTherapeuticCommunity("Therapeutic Community");
        recipient.setUserType(User.UserType.CLIENT);

        Review review = new Review();
        review.setAuthor(author);
        review.setId(1);
        review.setRecipient(recipient);
        review.setText("Text");

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

        Client author2 = new Client();
        author2.setEmail("john.smith@example.org");
        author2.setGender(User.Gender.FEMALE);
        author2.setId(2);
        author2.setName("42");
        author2.setPassword("Password");
        author2.setPhoneNumber("8605550118");
        author2.setRegistrationFinished(false);
        author2.setReservations(new ArrayList<>());
        author2.setSurname("Surname");
        author2.setTherapist(therapist5);
        author2.setUserType(User.UserType.THERAPIST);

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
        therapist6.setSchedule(new Schedule());
        therapist6.setSurname("Surname");
        therapist6.setThemes(new ArrayList<>());
        therapist6.setTherapeuticCommunity("42");
        therapist6.setUserType(User.UserType.THERAPIST);

        Schedule schedule6 = new Schedule();
        schedule6.setAvailableTimeCells(new ArrayList<>());
        schedule6.setId(2);
        schedule6.setTherapist(therapist6);

        Therapist recipient2 = new Therapist();
        recipient2.setApproved(false);
        recipient2.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        recipient2.setCertificates(new ArrayList<>());
        recipient2.setClients(new ArrayList<>());
        recipient2.setDescription("Description");
        recipient2.setEducation("42");
        recipient2.setEmail("john.smith@example.org");
        recipient2.setGender(User.Gender.FEMALE);
        recipient2.setId(2);
        recipient2.setLanguages(new ArrayList<>());
        recipient2.setName("42");
        recipient2.setPassword("Password");
        recipient2.setPersonalTherapy("42");
        recipient2.setPhoneNumber("8605550118");
        recipient2.setPhoto("Photo");
        recipient2.setRegistrationFinished(false);
        recipient2.setReservations(new ArrayList<>());
        recipient2.setSchedule(schedule6);
        recipient2.setSurname("Surname");
        recipient2.setThemes(new ArrayList<>());
        recipient2.setTherapeuticCommunity("42");
        recipient2.setUserType(User.UserType.THERAPIST);

        Review review2 = new Review();
        review2.setAuthor(author2);
        review2.setId(2);
        review2.setRecipient(recipient2);
        review2.setText("42");

        ArrayList<Review> reviewList = new ArrayList<>();
        reviewList.add(review2);
        reviewList.add(review);
        when(reviewRepository.findReviewsByRecipient(Mockito.<Therapist>any())).thenReturn(reviewList);

        // Act
        List<ReviewResponse> actualAllReviews = therapistServiceImpl.getAllReviews(1);

        // Assert
        verify(reviewRepository).findReviewsByRecipient(Mockito.<Therapist>any());
        verify(therapistRepository).findById(Mockito.<Integer>any());
        assertEquals(2, actualAllReviews.size());
        ReviewResponse getResult = actualAllReviews.get(0);
        assertEquals("42", getResult.text());
        ReviewResponse getResult2 = actualAllReviews.get(1);
        assertEquals("Text", getResult2.text());
        assertEquals(1, getResult2.author().intValue());
        assertEquals(1, getResult.therapistId().intValue());
        assertEquals(1, getResult2.therapistId().intValue());
        assertEquals(2, getResult.author().intValue());
    }

    /**
     * Method under test: {@link TherapistServiceImpl#getAllTherapists()}
     */
    @Test
    void testGetAllTherapists() {
        // Arrange
        when(therapistRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<TherapistResponse> actualAllTherapists = therapistServiceImpl.getAllTherapists();

        // Assert
        verify(therapistRepository).findAll();
        assertTrue(actualAllTherapists.isEmpty());
    }

    /**
     * Method under test: {@link TherapistServiceImpl#getAllTherapists()}
     */
    @Test
    void testGetAllTherapists2() {
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
        therapist2.setUserType(User.UserType.CLIENT);

        ArrayList<Therapist> therapistList = new ArrayList<>();
        therapistList.add(therapist2);
        when(therapistRepository.findAll()).thenReturn(therapistList);

        // Act
        List<TherapistResponse> actualAllTherapists = therapistServiceImpl.getAllTherapists();

        // Assert
        verify(therapistRepository).findAll();
        assertEquals(1, actualAllTherapists.size());
    }

    /**
     * Method under test: {@link TherapistServiceImpl#getAllTherapists()}
     */
    @Test
    void testGetAllTherapists3() {
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
        therapist2.setUserType(User.UserType.CLIENT);

        Schedule schedule3 = new Schedule();
        schedule3.setAvailableTimeCells(new ArrayList<>());
        schedule3.setId(2);
        schedule3.setTherapist(new Therapist());

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
        therapist3.setSchedule(schedule3);
        therapist3.setSurname("Surname");
        therapist3.setThemes(new ArrayList<>());
        therapist3.setTherapeuticCommunity("42");
        therapist3.setUserType(User.UserType.THERAPIST);

        Schedule schedule4 = new Schedule();
        schedule4.setAvailableTimeCells(new ArrayList<>());
        schedule4.setId(2);
        schedule4.setTherapist(therapist3);

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
        therapist4.setSchedule(schedule4);
        therapist4.setSurname("Surname");
        therapist4.setThemes(new ArrayList<>());
        therapist4.setTherapeuticCommunity("42");
        therapist4.setUserType(User.UserType.THERAPIST);

        ArrayList<Therapist> therapistList = new ArrayList<>();
        therapistList.add(therapist4);
        therapistList.add(therapist2);
        when(therapistRepository.findAll()).thenReturn(therapistList);

        // Act
        List<TherapistResponse> actualAllTherapists = therapistServiceImpl.getAllTherapists();

        // Assert
        verify(therapistRepository).findAll();
        assertEquals(2, actualAllTherapists.size());
    }

    /**
     * Method under test: {@link TherapistServiceImpl#getAllTherapists()}
     */
    @Test
    void testGetAllTherapists4() {
        // Arrange
        when(therapistRepository.findAll()).thenThrow(new EntityNotFoundException("An error occurred"));

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> therapistServiceImpl.getAllTherapists());
        verify(therapistRepository).findAll();
    }

    /**
     * Method under test: {@link TherapistServiceImpl#getAllTherapists()}
     */
    @Test
    void testGetAllTherapists5() {
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

        SpokenLanguage spokenLanguage = new SpokenLanguage();
        spokenLanguage.setId(1);
        spokenLanguage.setName("Name");
        spokenLanguage.setTherapist(therapist2);

        ArrayList<SpokenLanguage> languages = new ArrayList<>();
        languages.add(spokenLanguage);

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
        therapist4.setLanguages(languages);
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

        ArrayList<Therapist> therapistList = new ArrayList<>();
        therapistList.add(therapist4);
        when(therapistRepository.findAll()).thenReturn(therapistList);

        // Act
        List<TherapistResponse> actualAllTherapists = therapistServiceImpl.getAllTherapists();

        // Assert
        verify(therapistRepository).findAll();
        assertEquals(1, actualAllTherapists.size());
    }

    /**
     * Method under test: {@link TherapistServiceImpl#getAllTherapists()}
     */
    @Test
    void testGetAllTherapists6() {
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

        SpokenLanguage spokenLanguage = new SpokenLanguage();
        spokenLanguage.setId(1);
        spokenLanguage.setName("Name");
        spokenLanguage.setTherapist(therapist2);

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

        Schedule schedule2 = new Schedule();
        schedule2.setAvailableTimeCells(new ArrayList<>());
        schedule2.setId(2);
        schedule2.setTherapist(therapist3);

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
        therapist4.setSchedule(schedule2);
        therapist4.setSurname("Surname");
        therapist4.setThemes(new ArrayList<>());
        therapist4.setTherapeuticCommunity("42");
        therapist4.setUserType(User.UserType.THERAPIST);

        SpokenLanguage spokenLanguage2 = new SpokenLanguage();
        spokenLanguage2.setId(2);
        spokenLanguage2.setName("42");
        spokenLanguage2.setTherapist(therapist4);

        ArrayList<SpokenLanguage> languages = new ArrayList<>();
        languages.add(spokenLanguage2);
        languages.add(spokenLanguage);

        Schedule schedule3 = new Schedule();
        schedule3.setAvailableTimeCells(new ArrayList<>());
        schedule3.setId(1);
        schedule3.setTherapist(new Therapist());

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
        therapist5.setSchedule(schedule3);
        therapist5.setSurname("Doe");
        therapist5.setThemes(new ArrayList<>());
        therapist5.setTherapeuticCommunity("Therapeutic Community");
        therapist5.setUserType(User.UserType.CLIENT);

        Schedule schedule4 = new Schedule();
        schedule4.setAvailableTimeCells(new ArrayList<>());
        schedule4.setId(1);
        schedule4.setTherapist(therapist5);

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
        therapist6.setLanguages(languages);
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

        ArrayList<Therapist> therapistList = new ArrayList<>();
        therapistList.add(therapist6);
        when(therapistRepository.findAll()).thenReturn(therapistList);

        // Act
        List<TherapistResponse> actualAllTherapists = therapistServiceImpl.getAllTherapists();

        // Assert
        verify(therapistRepository).findAll();
        assertEquals(1, actualAllTherapists.size());
    }

    /**
     * Method under test: {@link TherapistServiceImpl#getAllTherapists()}
     */
    @Test
    void testGetAllTherapists7() {
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

        Schedule schedule2 = new Schedule();
        schedule2.setAvailableTimeCells(new ArrayList<>());
        schedule2.setId(1);
        schedule2.setTherapist(therapist);

        Theme theme = new Theme();
        theme.setId(1);
        theme.setName("Name");
        theme.setTherapists(new ArrayList<>());

        ArrayList<Theme> themes = new ArrayList<>();
        themes.add(theme);

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
        therapist2.setThemes(themes);
        therapist2.setTherapeuticCommunity("Therapeutic Community");
        therapist2.setUserType(User.UserType.CLIENT);

        ArrayList<Therapist> therapistList = new ArrayList<>();
        therapistList.add(therapist2);
        when(therapistRepository.findAll()).thenReturn(therapistList);

        // Act
        List<TherapistResponse> actualAllTherapists = therapistServiceImpl.getAllTherapists();

        // Assert
        verify(therapistRepository).findAll();
        assertEquals(1, actualAllTherapists.size());
    }

    /**
     * Method under test: {@link TherapistServiceImpl#getAllTherapists()}
     */
    @Test
    void testGetAllTherapists8() {
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

        Schedule schedule2 = new Schedule();
        schedule2.setAvailableTimeCells(new ArrayList<>());
        schedule2.setId(1);
        schedule2.setTherapist(therapist);

        Theme theme = new Theme();
        theme.setId(1);
        theme.setName("Name");
        theme.setTherapists(new ArrayList<>());

        Theme theme2 = new Theme();
        theme2.setId(2);
        theme2.setName("42");
        theme2.setTherapists(new ArrayList<>());

        ArrayList<Theme> themes = new ArrayList<>();
        themes.add(theme2);
        themes.add(theme);

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
        therapist2.setThemes(themes);
        therapist2.setTherapeuticCommunity("Therapeutic Community");
        therapist2.setUserType(User.UserType.CLIENT);

        ArrayList<Therapist> therapistList = new ArrayList<>();
        therapistList.add(therapist2);
        when(therapistRepository.findAll()).thenReturn(therapistList);

        // Act
        List<TherapistResponse> actualAllTherapists = therapistServiceImpl.getAllTherapists();

        // Assert
        verify(therapistRepository).findAll();
        assertEquals(1, actualAllTherapists.size());
    }
}
