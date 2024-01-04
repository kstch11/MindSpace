package com.example.mindspace.service.interfaces;

import com.example.mindspace.model.Schedule;

public interface TimeCellService {
    public void generateTimeCells(Schedule schedule);

    public void deleteExpiredTimeCells();
}
