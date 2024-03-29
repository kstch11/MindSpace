package com.example.mindspace.service.impl;

import com.example.mindspace.model.Schedule;
import com.example.mindspace.model.Therapist;
import com.example.mindspace.repository.AdminRepository;
import com.example.mindspace.repository.ScheduleRepository;
import com.example.mindspace.repository.TherapistRepository;
import com.example.mindspace.exception.EntityNotFoundException;
import com.example.mindspace.model.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl {
    private final TherapistRepository therapistRepository;
    private final ScheduleRepository scheduleRepository;

    private final TimeCellServiceImpl timeCellService;
    private final Logger LOG = Logger.getLogger(AdminServiceImpl.class.getName());

    /**
     * Approves a therapist based on the provided therapist ID.
     * If the therapist with the specified ID is not found, an EntityNotFoundException is thrown.
     *
     * @param therapistId The ID of the therapist to be approved.
     * @throws EntityNotFoundException if the therapist is not found in the repository.
     */
    public void approveTherapist(Integer therapistId) {
        var therapist = therapistRepository.findById(therapistId)
                .orElseThrow(() -> new EntityNotFoundException("Therapist not found"));

        therapist.setApproved(true);
        Schedule schedule = new Schedule();
        scheduleRepository.save(schedule);
        timeCellService.generateTimeCells(schedule);
        therapist.setSchedule(schedule);
        schedule.setTherapist(therapist);
        therapistRepository.save(therapist);
        scheduleRepository.save(schedule);
    }

}
