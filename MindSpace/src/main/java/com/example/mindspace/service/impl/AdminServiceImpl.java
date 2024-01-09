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
import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl {
    private final TherapistRepository therapistRepository;
    private final ScheduleRepository scheduleRepository;

    private final TimeCellServiceImpl timeCellService;

    public void approveTherapist(Integer therapistId) {
        var therapist = therapistRepository.findById(therapistId)
                .orElseThrow(() -> new EntityNotFoundException("Therapist not found"));

        therapist.setApproved(true);
        Schedule schedule = new Schedule(therapist, List.of());
        scheduleRepository.save(schedule);
        timeCellService.generateTimeCells(schedule);
        therapist.setSchedule(schedule);
        therapistRepository.save(therapist);

    }

}
