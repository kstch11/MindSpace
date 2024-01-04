package com.example.mindspace.service.impl;

import com.example.mindspace.api.ScheduleResponse;
import com.example.mindspace.api.TimeCellResponse;
import com.example.mindspace.exception.EntityNotFoundException;
import com.example.mindspace.model.Schedule;
import com.example.mindspace.repository.ScheduleRepository;
import com.example.mindspace.service.interfaces.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Override
    public void updateSchedule(Schedule schedule) {
    }

    @Override
    public void generateTimeCells() {

    }

    public List<ScheduleResponse> findAll() {
        return scheduleRepository.findAll().stream().map(s -> new ScheduleResponse(
                s.getId(),
                s.getAvailableTimeCells()
                        .stream()
                        .map(timeCell -> new TimeCellResponse(
                                timeCell.getId(),
                                timeCell.getStartTime(),
                                timeCell.getEndTime(),
                                timeCell.isReserved(),
                                timeCell.isExpired(),
                                timeCell.getReservation() != null
                                        ? timeCell.getReservation().getClient() != null
                                            ? timeCell.getReservation().getClient().getId()
                                            : null
                                        : null,
                                timeCell.getReservation() != null
                                        ?  timeCell.getReservation().getTherapist() != null
                                            ? timeCell.getReservation().getTherapist().getId()
                                            : null
                                        : null
                        )).toList()
        )).toList();
    }

//    public ScheduleResponse getScheduleById(Integer id) {
//        Schedule schedule = findById(id);
//
//        return new ScheduleResponse(schedule.getId(), schedule.getAvailableTimeCells());
//    }
//
//    private Schedule findById(Integer id) {
//        return scheduleRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Schedule with id " + id + "does not exist"));
//    }

}
