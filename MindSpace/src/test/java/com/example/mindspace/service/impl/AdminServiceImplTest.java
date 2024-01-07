package com.example.mindspace.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.mindspace.exception.EntityNotFoundException;
import com.example.mindspace.model.Schedule;
import com.example.mindspace.model.Therapist;
import com.example.mindspace.model.User;
import com.example.mindspace.repository.AdminRepository;
import com.example.mindspace.repository.TherapistRepository;

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

@ContextConfiguration(classes = {AdminServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AdminServiceImplTest {
    @MockBean
    private AdminRepository adminRepository;

    @Autowired
    private AdminServiceImpl adminServiceImpl;

    @MockBean
    private TherapistRepository therapistRepository;

    @Test
    void testApproveTherapist() {
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

        // Act
        adminServiceImpl.approveTherapist(1);

        // Assert
        verify(therapistRepository).findById(Mockito.<Integer>any());
        verify(therapistRepository).save(Mockito.<Therapist>any());
    }

    /**
     * Method under test: {@link AdminServiceImpl#approveTherapist(Integer)}
     */
    @Test
    void testApproveTherapist2() {
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
        assertThrows(EntityNotFoundException.class, () -> adminServiceImpl.approveTherapist(1));
        verify(therapistRepository).findById(Mockito.<Integer>any());
        verify(therapistRepository).save(Mockito.<Therapist>any());
    }
}
