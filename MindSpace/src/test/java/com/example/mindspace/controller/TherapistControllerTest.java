package com.example.mindspace.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.mindspace.api.*;
import com.example.mindspace.model.Schedule;
import com.example.mindspace.model.SpokenLanguage;
import com.example.mindspace.model.Theme;
import com.example.mindspace.model.Therapist;
import com.example.mindspace.model.User;
import com.example.mindspace.repository.ClientRepository;
import com.example.mindspace.repository.ReviewRepository;
import com.example.mindspace.repository.SpokenLanguageRepository;
import com.example.mindspace.repository.ThemeRepository;
import com.example.mindspace.repository.TherapistRepository;
import com.example.mindspace.security.UserPrincipal;
import com.example.mindspace.service.impl.TherapistServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {TherapistController.class})
@ExtendWith(SpringExtension.class)
class TherapistControllerTest {
    @Autowired
    private TherapistController therapistController;

    @MockBean
    private TherapistServiceImpl therapistServiceImpl;

    /**
     * Method under test:
     * {@link TherapistController#setTherapistRegistrationComplete(UserPrincipal)}
     */
    @Test
    void testSetTherapistRegistrationComplete() throws Exception {
        // Arrange
        doNothing().when(therapistServiceImpl).setTherapistRegistrationComplete(Mockito.<UserPrincipal>any());
        MockHttpServletRequestBuilder putResult = MockMvcRequestBuilders.put("/therapists/profile/regDone");
        MockHttpServletRequestBuilder requestBuilder = putResult.param("userPrincipal",
                String.valueOf(new UserPrincipal(1, "jane.doe@example.org", "iloveyou", new ArrayList<>())));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(therapistController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test:
     * {@link TherapistController#setTherapistRegistrationComplete(UserPrincipal)}
     */
    @Test
    void testSetTherapistRegistrationComplete2() throws Exception {
        // Arrange
        doNothing().when(therapistServiceImpl).setTherapistRegistrationComplete(Mockito.<UserPrincipal>any());
        MockHttpServletRequestBuilder putResult = MockMvcRequestBuilders.put("/therapists/profile/regDone");
        MockHttpServletRequestBuilder requestBuilder = putResult.param("userPrincipal", String.valueOf(""));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(therapistController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test:
     * {@link TherapistController#postTherapistQuestionnaire(UserPrincipal, TherapistQuestionnaireRequest)}
     */
    @Test
    void testPostTherapistQuestionnaire() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDateTime` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: com.example.mindspace.api.TherapistQuestionnaireRequest["birthDate"])
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:77)
        //       at com.fasterxml.jackson.databind.SerializerProvider.reportBadDefinition(SerializerProvider.java:1276)
        //       at com.fasterxml.jackson.databind.ser.impl.UnsupportedTypeSerializer.serialize(UnsupportedTypeSerializer.java:35)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:728)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:770)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:480)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:319)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4487)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3742)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        Therapist therapist = mock(Therapist.class);
        doNothing().when(therapist).setBirthDate(Mockito.<LocalDateTime>any());
        doNothing().when(therapist).setDescription(Mockito.<String>any());
        doNothing().when(therapist).setEducation(Mockito.<String>any());
        doNothing().when(therapist).setExperience(Mockito.<Integer>any());
        doNothing().when(therapist).setLanguages(Mockito.<List<SpokenLanguage>>any());
        doNothing().when(therapist).setPersonalTherapy(Mockito.<String>any());
        doNothing().when(therapist).setThemes(Mockito.<List<Theme>>any());
        doNothing().when(therapist).setTherapeuticCommunity(Mockito.<String>any());
        doNothing().when(therapist).setGender(Mockito.<User.Gender>any());
        doNothing().when(therapist).setName(Mockito.<String>any());
        doNothing().when(therapist).setPhoneNumber(Mockito.<String>any());
        doNothing().when(therapist).setSurname(Mockito.<String>any());
        Optional<Therapist> ofResult = Optional.of(therapist);

        Therapist therapist2 = new Therapist();
        therapist2.setApproved(true);
        therapist2.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist2.setCertificates(new ArrayList<>());
        therapist2.setClients(new ArrayList<>());
        therapist2.setDescription("The characteristics of someone or something");
        therapist2.setEducation("Education");
        therapist2.setEmail("jane.doe@example.org");
        therapist2.setExperience(1);
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

        Schedule schedule = new Schedule();
        schedule.setAvailableTimeCells(new ArrayList<>());
        schedule.setId(1);
        schedule.setTherapist(therapist2);

        Therapist therapist3 = new Therapist();
        therapist3.setApproved(true);
        therapist3.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist3.setCertificates(new ArrayList<>());
        therapist3.setClients(new ArrayList<>());
        therapist3.setDescription("The characteristics of someone or something");
        therapist3.setEducation("Education");
        therapist3.setEmail("jane.doe@example.org");
        therapist3.setExperience(1);
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
        therapist3.setSchedule(schedule);
        therapist3.setSurname("Doe");
        therapist3.setThemes(new ArrayList<>());
        therapist3.setTherapeuticCommunity("Therapeutic Community");
        therapist3.setUserType(User.UserType.CLIENT);

        Schedule schedule2 = new Schedule();
        schedule2.setAvailableTimeCells(new ArrayList<>());
        schedule2.setId(1);
        schedule2.setTherapist(therapist3);

        Therapist therapist4 = new Therapist();
        therapist4.setApproved(true);
        therapist4.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist4.setCertificates(new ArrayList<>());
        therapist4.setClients(new ArrayList<>());
        therapist4.setDescription("The characteristics of someone or something");
        therapist4.setEducation("Education");
        therapist4.setEmail("jane.doe@example.org");
        therapist4.setExperience(1);
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
        therapist4.setSchedule(schedule2);
        therapist4.setSurname("Doe");
        therapist4.setThemes(new ArrayList<>());
        therapist4.setTherapeuticCommunity("Therapeutic Community");
        therapist4.setUserType(User.UserType.CLIENT);
        TherapistRepository therapistRepository = mock(TherapistRepository.class);
        when(therapistRepository.save(Mockito.<Therapist>any())).thenReturn(therapist4);
        when(therapistRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        TherapistController therapistController = new TherapistController(
                new TherapistServiceImpl(therapistRepository, mock(ThemeRepository.class), mock(ClientRepository.class),
                        mock(ReviewRepository.class), mock(SpokenLanguageRepository.class)));
        UserPrincipal userPrincipal = new UserPrincipal(1, "jane.doe@example.org", "iloveyou", new ArrayList<>());

        LocalDateTime birthDate = LocalDate.of(1970, 1, 1).atStartOfDay();
        ArrayList<String> topics = new ArrayList<>();

        // Act
        ResponseEntity<TherapistQuestionnaireResponse> actualPostTherapistQuestionnaireResult = therapistController.postTherapistQuestionnaire(
                userPrincipal,
                new TherapistQuestionnaireRequest("Name", "Doe", birthDate, "Gender",
                        "The characteristics of someone or something", topics, "Education", "Therapeutic Community",
                        new ArrayList<>(), "Personal Psychotherapy", 1, "6625550144"));

        // Assert
        verify(therapist).setBirthDate(Mockito.<LocalDateTime>any());
        verify(therapist).setDescription(Mockito.<String>any());
        verify(therapist).setEducation(Mockito.<String>any());
        verify(therapist).setExperience(Mockito.<Integer>any());
        verify(therapist).setLanguages(Mockito.<List<SpokenLanguage>>any());
        verify(therapist).setPersonalTherapy(Mockito.<String>any());
        verify(therapist).setThemes(Mockito.<List<Theme>>any());
        verify(therapist).setTherapeuticCommunity(Mockito.<String>any());
        verify(therapist).setGender(Mockito.<User.Gender>any());
        verify(therapist).setName(Mockito.<String>any());
        verify(therapist).setPhoneNumber(Mockito.<String>any());
        verify(therapist).setSurname(Mockito.<String>any());
        verify(therapistRepository).findById(Mockito.<Integer>any());
        verify(therapistRepository).save(Mockito.<Therapist>any());
        assertNull(actualPostTherapistQuestionnaireResult.getBody());
        assertEquals(HttpStatus.OK, actualPostTherapistQuestionnaireResult.getStatusCode());
        assertTrue(actualPostTherapistQuestionnaireResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link TherapistController#updateTherapist(Integer, UserRequest)}
     */
    @Test
    void testUpdateTherapist() throws Exception {
        // Arrange
        doNothing().when(therapistServiceImpl).updateTherapist(Mockito.<Integer>any(), Mockito.<UserRequest>any());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.put("/therapists/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new UserRequest("Name", "Doe", "42", "jane.doe@example.org")));

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(therapistController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link TherapistController#getAllTherapists()}
     */
    @Test
    void testGetAllTherapists() throws Exception {
        // Arrange
        when(therapistServiceImpl.getAllTherapists()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/therapists/allTherapists");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(therapistController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TherapistController#getAllTherapists()}
     */
    @Test
    void testGetAllTherapists2() throws Exception {
        // Arrange
        when(therapistServiceImpl.getAllTherapists()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/therapists/allTherapists");
        requestBuilder.characterEncoding("Encoding");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(therapistController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link TherapistController#createReview(Integer, CreateReviewRequest)}
     */
    @Test
    void testCreateReview() throws Exception {
        // Arrange
        when(therapistServiceImpl.createReview(Mockito.<Integer>any(), Mockito.<CreateReviewRequest>any()))
                .thenReturn(new CreateReviewResponse(1));
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/therapists/{id}/reviews", 1)
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new CreateReviewRequest(1, "Text")));

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(therapistController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":1}"));
    }

    /**
     * Method under test: {@link TherapistController#getAllReservations(Integer)}
     */
    @Test
    void testGetAllReservations() throws Exception {
        // Arrange
        when(therapistServiceImpl.findAllReservations(Mockito.<Integer>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/therapists/{id}/reservations", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(therapistController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TherapistController#getAllReservations(Integer)}
     */
    @Test
    void testGetAllReservations2() throws Exception {
        // Arrange
        when(therapistServiceImpl.findAllReservations(Mockito.<Integer>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/therapists/{id}/reservations", 1);
        requestBuilder.characterEncoding("Encoding");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(therapistController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link TherapistController#getAllTherapistClients(Integer)}
     */
    @Test
    void testGetAllTherapistClients() throws Exception {
        // Arrange
        when(therapistServiceImpl.findAllClients(Mockito.<Integer>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/therapists/{id}/clients", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(therapistController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link TherapistController#getAllTherapistClients(Integer)}
     */
    @Test
    void testGetAllTherapistClients2() throws Exception {
        // Arrange
        when(therapistServiceImpl.findAllClients(Mockito.<Integer>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/therapists/{id}/clients", 1);
        requestBuilder.characterEncoding("Encoding");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(therapistController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TherapistController#getReviews(Integer)}
     */
    @Test
    void testGetReviews() throws Exception {
        // Arrange
        when(therapistServiceImpl.getAllReviews(Mockito.<Integer>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/therapists/{id}/reviews", 1);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(therapistController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TherapistController#getReviews(Integer)}
     */
    @Test
    void testGetReviews2() throws Exception {
        // Arrange
        when(therapistServiceImpl.getAllReviews(Mockito.<Integer>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/therapists/{id}/reviews", 1);
        requestBuilder.characterEncoding("Encoding");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(therapistController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TherapistController#getSchedule(Integer)}
     */
    @Test
    void testGetSchedule() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/therapists/{id}/schedule", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(therapistController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link TherapistController#getSchedule(Integer)}
     */
    @Test
    void testGetSchedule2() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/therapists/{id}/schedule", 1);
        requestBuilder.characterEncoding("Encoding");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(therapistController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link TherapistController#getTherapist(Integer)}
     */
    @Test
    void testGetTherapist() throws Exception {
        // Arrange
        ArrayList<LanguageResponse> languages = new ArrayList<>();
        when(therapistServiceImpl.getTherapistDetails(Mockito.<Integer>any()))
                .thenReturn(new TherapistResponse(1, "Name", "Doe", "6625550144", "jane.doe@example.org", true,
                        "The characteristics of someone or something", "Education", languages, "Personal Therapy",
                        "alice.liddell@example.org", "Therapeutic Community", true, true, new ArrayList<>(), 1));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/therapists/{id}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(therapistController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"name\":\"Name\",\"surname\":\"Doe\",\"phone\":\"6625550144\",\"email\":\"jane.doe@example.org\",\"finishedR"
                                        + "egistration\":true,\"description\":\"The characteristics of someone or something\",\"education\":\"Education"
                                        + "\",\"languages\":[],\"personalTherapy\":\"Personal Therapy\",\"photo\":\"alice.liddell@example.org\",\"therapeut"
                                        + "icCommunity\":\"Therapeutic Community\",\"approved\":true,\"isTherapist\":true,\"topics\":[],\"experience\":1}"));
    }
}
