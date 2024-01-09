package com.example.mindspace.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.mindspace.api.AdminResponse;
import com.example.mindspace.api.ClientResponse;
import com.example.mindspace.api.UserResponse;
import com.example.mindspace.model.Admin;
import com.example.mindspace.model.Client;
import com.example.mindspace.model.Schedule;
import com.example.mindspace.model.Therapist;
import com.example.mindspace.model.User;
import com.example.mindspace.repository.UserRepository;
import com.example.mindspace.security.UserPrincipal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private ClientServiceImpl clientServiceImpl;

    @MockBean
    private TherapistServiceImpl therapistServiceImpl;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    /**
     * Method under test: {@link UserServiceImpl#getCurrentUser(UserPrincipal)}
     */
    @Test
    void testGetCurrentUser() {
        // Arrange
        Optional<User> ofResult = Optional.of(new Admin());
        when(userRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        UserResponse actualCurrentUser = userServiceImpl
                .getCurrentUser(new UserPrincipal(1, "jane.doe@example.org", "iloveyou", new ArrayList<>()));

        // Assert
        verify(userRepository).findById(Mockito.<Integer>any());
        assertNull(((AdminResponse) actualCurrentUser).name());
        assertEquals(1, ((AdminResponse) actualCurrentUser).id().intValue());
        assertTrue(((AdminResponse) actualCurrentUser).finishedRegistration());
        assertTrue(((AdminResponse) actualCurrentUser).isAdmin());
    }

    /**
     * Method under test: {@link UserServiceImpl#getCurrentUser(UserPrincipal)}
     */
    @Test
    void testGetCurrentUser2() {
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
        Optional<User> ofResult = Optional.of(client);
        when(userRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        ClientResponse clientResponse = new ClientResponse(1, 1, "Name", "Doe", "6625550144", "jane.doe@example.org", true,
                true);

        when(clientServiceImpl.getClientDetails(Mockito.<Integer>any())).thenReturn(clientResponse);

        // Act
        UserResponse actualCurrentUser = userServiceImpl
                .getCurrentUser(new UserPrincipal(1, "jane.doe@example.org", "iloveyou", new ArrayList<>()));

        // Assert
        verify(clientServiceImpl).getClientDetails(Mockito.<Integer>any());
        verify(userRepository).findById(Mockito.<Integer>any());
        assertSame(clientResponse, actualCurrentUser);
    }
}
