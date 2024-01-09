package com.example.mindspace.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.mindspace.api.ClientResponse;
import com.example.mindspace.api.LanguageResponse;
import com.example.mindspace.api.ReservationRequest;
import com.example.mindspace.api.ReservationResponse;
import com.example.mindspace.api.TherapistResponse;
import com.example.mindspace.service.impl.ReservationServiceImpl;
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

@ContextConfiguration(classes = {ReservationController.class})
@ExtendWith(SpringExtension.class)
class ReservationControllerTest {
    @Autowired
    private ReservationController reservationController;

    @MockBean
    private ReservationServiceImpl reservationServiceImpl;

    /**
     * Method under test: {@link ReservationController#getReservation(Integer)}
     */
    @Test
    void testGetReservation() throws Exception {
        // Arrange
        ClientResponse clientResponse = new ClientResponse(1, 1, "Name", "Doe", "6625550144", "jane.doe@example.org", true,
                true);

        ArrayList<LanguageResponse> languages = new ArrayList<>();
        when(reservationServiceImpl.getReservation(Mockito.<Integer>any()))
                .thenReturn(new ReservationResponse(1, "2020-03-01", clientResponse,
                        new TherapistResponse(1, "Name", "Doe", "6625550144", "jane.doe@example.org", true,
                                "The characteristics of someone or something", "Education", languages, "Personal Therapy",
                                "alice.liddell@example.org", "Therapeutic Community", true, true, new ArrayList<>(), 1)));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/reservations/{id}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(reservationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"date\":\"2020-03-01\",\"clientResponse\":{\"id\":1,\"therapistId\":1,\"name\":\"Name\",\"surname\":\"Doe\","
                                        + "\"phone\":\"6625550144\",\"email\":\"jane.doe@example.org\",\"finishedRegistration\":true,\"isTherapist\":true},"
                                        + "\"therapist\":{\"id\":1,\"name\":\"Name\",\"surname\":\"Doe\",\"phone\":\"6625550144\",\"email\":\"jane.doe@example.org"
                                        + "\",\"finishedRegistration\":true,\"description\":\"The characteristics of someone or something\",\"education"
                                        + "\":\"Education\",\"languages\":[],\"personalTherapy\":\"Personal Therapy\",\"photo\":\"alice.liddell@example.org"
                                        + "\",\"therapeuticCommunity\":\"Therapeutic Community\",\"approved\":true,\"isTherapist\":true,\"topics\":[],"
                                        + "\"experience\":1}}"));
    }

    /**
     * Method under test: {@link ReservationController#cancelReservation(Integer)}
     */
    @Test
    void testCancelReservation() throws Exception {
        // Arrange
        doNothing().when(reservationServiceImpl).cancelReservation(Mockito.<Integer>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/reservations/{id}", 1);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(reservationController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link ReservationController#cancelReservation(Integer)}
     */
    @Test
    void testCancelReservation2() throws Exception {
        // Arrange
        doNothing().when(reservationServiceImpl).cancelReservation(Mockito.<Integer>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/reservations/{id}", 1);
        requestBuilder.characterEncoding("Encoding");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(reservationController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test:
     * {@link ReservationController#delayReservation(Integer, Integer)}
     */
    @Test
    void testDelayReservation() throws Exception {
        // Arrange
        doNothing().when(reservationServiceImpl).delayReservation(Mockito.<Integer>any(), Mockito.<Integer>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/reservations/{reservationId}/delay/{timeCellId}", 1, 1);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(reservationController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test:
     * {@link ReservationController#delayReservation(Integer, Integer)}
     */
    @Test
    void testDelayReservation2() throws Exception {
        // Arrange
        doNothing().when(reservationServiceImpl).delayReservation(Mockito.<Integer>any(), Mockito.<Integer>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/reservations/{reservationId}/delay/{timeCellId}", 1, 1);
        requestBuilder.characterEncoding("Encoding");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(reservationController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test:
     * {@link ReservationController#createReservation(ReservationRequest)}
     */
    @Test
    void testCreateReservation() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/reservations")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ReservationRequest(1, 1, 1)));

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(reservationController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(405));
    }
}
