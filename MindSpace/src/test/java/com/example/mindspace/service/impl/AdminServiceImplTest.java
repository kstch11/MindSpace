package com.example.mindspace.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.mindspace.exception.EntityNotFoundException;
import com.example.mindspace.model.Schedule;
import com.example.mindspace.model.Therapist;
import com.example.mindspace.model.User;
import com.example.mindspace.repository.AdminRepository;
import com.example.mindspace.repository.ScheduleRepository;
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
    private ScheduleRepository scheduleRepository;

    @MockBean
    private TherapistRepository therapistRepository;

    @MockBean
    private TimeCellServiceImpl timeCellServiceImpl;

    /**
     * Method under test: {@link AdminServiceImpl#approveTherapist(Integer)}
     */
    @Test
    void testApproveTherapist() {
        // Arrange
        Schedule schedule = new Schedule();
        schedule.setAvailableTimeCells(new ArrayList<>());
        schedule.setId(1);
        schedule.setTherapist(new Therapist());

        Therapist therapist = getTherapist(schedule);

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
        therapist5.setExperience(1);
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
        when(scheduleRepository.save(Mockito.<Schedule>any())).thenThrow(new EntityNotFoundException("An error occurred"));

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> adminServiceImpl.approveTherapist(1));
        verify(therapistRepository).findById(Mockito.<Integer>any());
        verify(scheduleRepository).save(Mockito.<Schedule>any());
    }

    private static Therapist getTherapist(Schedule schedule) {
        Therapist therapist = new Therapist();
        therapist.setApproved(true);
        therapist.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist.setCertificates(new ArrayList<>());
        therapist.setClients(new ArrayList<>());
        therapist.setDescription("The characteristics of someone or something");
        therapist.setEducation("Education");
        therapist.setEmail("jane.doe@example.org");
        therapist.setExperience(1);
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
        return therapist;
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
        therapist.setExperience(1);
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
        therapist5.setExperience(1);
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

        Schedule schedule5 = new Schedule();
        schedule5.setAvailableTimeCells(new ArrayList<>());
        schedule5.setId(1);
        schedule5.setTherapist(new Therapist());

        Therapist therapist6 = new Therapist();
        therapist6.setApproved(true);
        therapist6.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist6.setCertificates(new ArrayList<>());
        therapist6.setClients(new ArrayList<>());
        therapist6.setDescription("The characteristics of someone or something");
        therapist6.setEducation("Education");
        therapist6.setEmail("jane.doe@example.org");
        therapist6.setExperience(1);
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

        Schedule schedule6 = new Schedule();
        schedule6.setAvailableTimeCells(new ArrayList<>());
        schedule6.setId(1);
        schedule6.setTherapist(therapist6);

        Therapist therapist7 = new Therapist();
        therapist7.setApproved(true);
        therapist7.setBirthDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        therapist7.setCertificates(new ArrayList<>());
        therapist7.setClients(new ArrayList<>());
        therapist7.setDescription("The characteristics of someone or something");
        therapist7.setEducation("Education");
        therapist7.setEmail("jane.doe@example.org");
        therapist7.setExperience(1);
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
        therapist7.setSchedule(schedule6);
        therapist7.setSurname("Doe");
        therapist7.setThemes(new ArrayList<>());
        therapist7.setTherapeuticCommunity("Therapeutic Community");
        therapist7.setUserType(User.UserType.CLIENT);

        Schedule schedule7 = new Schedule();
        schedule7.setAvailableTimeCells(new ArrayList<>());
        schedule7.setId(1);
        schedule7.setTherapist(therapist7);
        when(scheduleRepository.save(Mockito.<Schedule>any())).thenReturn(schedule7);
        doNothing().when(timeCellServiceImpl).generateTimeCells(Mockito.<Schedule>any());

        // Act
        adminServiceImpl.approveTherapist(1);

        // Assert
        verify(timeCellServiceImpl).generateTimeCells(Mockito.<Schedule>any());
        verify(therapistRepository).findById(Mockito.<Integer>any());
        verify(scheduleRepository).save(Mockito.<Schedule>any());
        verify(therapistRepository).save(Mockito.<Therapist>any());
    }
}
