package com.example.mindspace.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.mindspace.api.ClientResponse;
import com.example.mindspace.api.ClientTherapistRelationRequest;
import com.example.mindspace.api.QuestionnaireRequest;
import com.example.mindspace.api.UserRequest;
import com.example.mindspace.security.UserPrincipal;
import com.example.mindspace.service.impl.ClientServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ClientsController.class})
@ExtendWith(SpringExtension.class)
class ClientsControllerTest {
    @MockBean
    private ClientServiceImpl clientServiceImpl;

    @Autowired
    private ClientsController clientsController;

    /**
     * Method under test:
     * {@link ClientsController#setRegistrationComplete(UserPrincipal)}
     */
    @Test
    void testSetRegistrationComplete() throws Exception {
        // Arrange
        doNothing().when(clientServiceImpl).setRegistrationComplete(Mockito.<UserPrincipal>any());
        MockHttpServletRequestBuilder putResult = MockMvcRequestBuilders.put("/clients/profile/regDone");
        MockHttpServletRequestBuilder requestBuilder = putResult.param("userPrincipal",
                String.valueOf(new UserPrincipal(1, "jane.doe@example.org", "iloveyou", new ArrayList<>())));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(clientsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test:
     * {@link ClientsController#setRegistrationComplete(UserPrincipal)}
     */
    @Test
    void testSetRegistrationComplete2() throws Exception {
        // Arrange
        doNothing().when(clientServiceImpl).setRegistrationComplete(Mockito.<UserPrincipal>any());
        MockHttpServletRequestBuilder putResult = MockMvcRequestBuilders.put("/clients/profile/regDone");
        MockHttpServletRequestBuilder requestBuilder = putResult.param("userPrincipal", String.valueOf(""));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(clientsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link ClientsController#cancelTherapist(Integer)}
     */
    @Test
    void testCancelTherapist() throws Exception {
        // Arrange
        doNothing().when(clientServiceImpl).cancelTherapist(Mockito.<Integer>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/clients/{id}/therapist", 1);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(clientsController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link ClientsController#cancelTherapist(Integer)}
     */
    @Test
    void testCancelTherapist2() throws Exception {
        // Arrange
        doNothing().when(clientServiceImpl).cancelTherapist(Mockito.<Integer>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/clients/{id}/therapist", 1);
        requestBuilder.characterEncoding("Encoding");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(clientsController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test:
     * {@link ClientsController#updateClient(Integer, UserRequest)}
     */
    @Test
    void testUpdateClient() throws Exception {
        // Arrange
        doNothing().when(clientServiceImpl).updateClient(Mockito.<Integer>any(), Mockito.<UserRequest>any());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.put("/clients/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new UserRequest("Name", "Doe", "42", "jane.doe@example.org")));

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(clientsController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link ClientsController#getAllReservations(Integer)}
     */
    @Test
    void testGetAllReservations() throws Exception {
        // Arrange
        when(clientServiceImpl.findAllReservations(Mockito.<Integer>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/clients/{id}/reservations", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(clientsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ClientsController#getAllReservations(Integer)}
     */
    @Test
    void testGetAllReservations2() throws Exception {
        // Arrange
        when(clientServiceImpl.findAllReservations(Mockito.<Integer>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/clients/{id}/reservations", 1);
        requestBuilder.characterEncoding("Encoding");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(clientsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ClientsController#getCurrentUser(UserPrincipal)}
     */
    @Test
    void testGetCurrentUser() throws Exception {
        // Arrange
        when(clientServiceImpl.getClientDetails(Mockito.<Integer>any()))
                .thenReturn(new ClientResponse(1, 1, "Name", "Doe", "6625550144", "jane.doe@example.org", true, true));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/clients/profile");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("userPrincipal",
                String.valueOf(new UserPrincipal(1, "jane.doe@example.org", "iloveyou", new ArrayList<>())));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(clientsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"therapistId\":1,\"name\":\"Name\",\"surname\":\"Doe\",\"phone\":\"6625550144\",\"email\":\"jane.doe@example"
                                        + ".org\",\"finishedRegistration\":true,\"isTherapist\":true}"));
    }

    /**
     * Method under test: {@link ClientsController#getCurrentUser(UserPrincipal)}
     */
    @Test
    void testGetCurrentUser2() throws Exception {
        // Arrange
        when(clientServiceImpl.getClientDetails(Mockito.<Integer>any()))
                .thenReturn(new ClientResponse(1, 1, "Name", "Doe", "6625550144", "jane.doe@example.org", true, true));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/clients/profile");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("userPrincipal", String.valueOf(""));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(clientsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"therapistId\":1,\"name\":\"Name\",\"surname\":\"Doe\",\"phone\":\"6625550144\",\"email\":\"jane.doe@example"
                                        + ".org\",\"finishedRegistration\":true,\"isTherapist\":true}"));
    }

    /**
     * Method under test: {@link ClientsController#getDetails(Integer)}
     */
    @Test
    void testGetDetails() throws Exception {
        // Arrange
        when(clientServiceImpl.getClientDetails(Mockito.<Integer>any()))
                .thenReturn(new ClientResponse(1, 1, "Name", "Doe", "6625550144", "jane.doe@example.org", true, true));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/clients/{id}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(clientsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"therapistId\":1,\"name\":\"Name\",\"surname\":\"Doe\",\"phone\":\"6625550144\",\"email\":\"jane.doe@example"
                                        + ".org\",\"finishedRegistration\":true,\"isTherapist\":true}"));
    }

    /**
     * Method under test:
     * {@link ClientsController#getTherapist(UserPrincipal, ClientTherapistRelationRequest)}
     */
    @Test
    void testGetTherapist() throws Exception {
        // Arrange
        doNothing().when(clientServiceImpl)
                .setNewTherapist(Mockito.<Integer>any(), Mockito.<ClientTherapistRelationRequest>any());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/clients/therapist");
        MockHttpServletRequestBuilder contentTypeResult = getResult
                .param("userPrincipal",
                        String.valueOf(new UserPrincipal(1, "jane.doe@example.org", "iloveyou", new ArrayList<>())))
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ClientTherapistRelationRequest(1)));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(clientsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test:
     * {@link ClientsController#getTherapist(UserPrincipal, ClientTherapistRelationRequest)}
     */
    @Test
    void testGetTherapist2() throws Exception {
        // Arrange
        doNothing().when(clientServiceImpl)
                .setNewTherapist(Mockito.<Integer>any(), Mockito.<ClientTherapistRelationRequest>any());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/clients/therapist");
        MockHttpServletRequestBuilder contentTypeResult = getResult.param("userPrincipal", String.valueOf(""))
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ClientTherapistRelationRequest(1)));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(clientsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test:
     * {@link ClientsController#postClientQuestionnaire(UserPrincipal, QuestionnaireRequest)}
     */
    @Test
    void testPostClientQuestionnaire() throws Exception {
        // Arrange
        when(clientServiceImpl.saveQuestionnaire(Mockito.<UserPrincipal>any(), Mockito.<QuestionnaireRequest>any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/clients/questionnaire");
        MockHttpServletRequestBuilder contentTypeResult = postResult
                .param("userPrincipal",
                        String.valueOf(new UserPrincipal(1, "jane.doe@example.org", "iloveyou", new ArrayList<>())))
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult.content(
                objectMapper.writeValueAsString(new QuestionnaireRequest("Name", "Doe", "6625550144", new ArrayList<>())));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(clientsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link ClientsController#postClientQuestionnaire(UserPrincipal, QuestionnaireRequest)}
     */
    @Test
    void testPostClientQuestionnaire2() throws Exception {
        // Arrange
        when(clientServiceImpl.saveQuestionnaire(Mockito.<UserPrincipal>any(), Mockito.<QuestionnaireRequest>any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/clients/questionnaire");
        MockHttpServletRequestBuilder contentTypeResult = postResult.param("userPrincipal", String.valueOf(""))
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult.content(
                objectMapper.writeValueAsString(new QuestionnaireRequest("Name", "Doe", "6625550144", new ArrayList<>())));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(clientsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link ClientsController#setTherapist(UserPrincipal, ClientTherapistRelationRequest)}
     */
    @Test
    void testSetTherapist() throws Exception {
        // Arrange
        doNothing().when(clientServiceImpl)
                .setNewTherapist(Mockito.<Integer>any(), Mockito.<ClientTherapistRelationRequest>any());
        MockHttpServletRequestBuilder putResult = MockMvcRequestBuilders.put("/clients/therapist");
        MockHttpServletRequestBuilder contentTypeResult = putResult
                .param("userPrincipal",
                        String.valueOf(new UserPrincipal(1, "jane.doe@example.org", "iloveyou", new ArrayList<>())))
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ClientTherapistRelationRequest(1)));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(clientsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test:
     * {@link ClientsController#setTherapist(UserPrincipal, ClientTherapistRelationRequest)}
     */
    @Test
    void testSetTherapist2() throws Exception {
        // Arrange
        doNothing().when(clientServiceImpl)
                .setNewTherapist(Mockito.<Integer>any(), Mockito.<ClientTherapistRelationRequest>any());
        MockHttpServletRequestBuilder putResult = MockMvcRequestBuilders.put("/clients/therapist");
        MockHttpServletRequestBuilder contentTypeResult = putResult.param("userPrincipal", String.valueOf(""))
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ClientTherapistRelationRequest(1)));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(clientsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
