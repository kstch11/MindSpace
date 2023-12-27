package com.example.mindspace.service.interfaces;

import com.example.mindspace.model.Schedule;

public interface ScheduleService {
    public void updateSchedule(Schedule schedule);

    public void generateTimeCells();
}
