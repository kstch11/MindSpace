package com.example.mindspace.service.impl;

import com.example.mindspace.model.Schedule;
import com.example.mindspace.model.TimeCell;
import com.example.mindspace.repository.ScheduleRepository;
import com.example.mindspace.repository.TimeCellRepository;
import com.example.mindspace.service.interfaces.TimeCellService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeCellServiceImpl implements TimeCellService {
    private final TimeCellRepository timeCellRepository;

    /**
     * Generates time cells for a given schedule starting from the next day for 30 days.
     * Time cells are created for each hour from 9 AM to 5 PM.
     *
     * @param schedule The schedule for which time cells are to be generated.
     */
    @Override
    public void generateTimeCells(Schedule schedule) {
        LocalDateTime startDateTime = LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endDateTime = startDateTime.plusDays(30);

        for (LocalDateTime date = startDateTime; date.isBefore(endDateTime); date = date.plusDays(1)) {
            for (int hour = 9; hour <= 17; hour++) {
                TimeCell timeCell = new TimeCell();
                LocalDateTime startTime = LocalDateTime.of(date.toLocalDate(), LocalTime.of(hour, 0));
                LocalDateTime endTime = startTime.plusHours(1);

                timeCell.setStartTime(startTime);
                timeCell.setEndTime(endTime);
                timeCell.setSchedule(schedule);

                timeCellRepository.save(timeCell);
            }
        }
    }

    /**
     * Deletes all time cells that have expired, based on the current date and time.
     */
    @Override
    public void deleteExpiredTimeCells() {
        LocalDateTime now = LocalDateTime.now();
        List<TimeCell> expiredTimeCells = timeCellRepository.findByEndTimeBefore(now);
        timeCellRepository.deleteAll(expiredTimeCells);

    }
}
