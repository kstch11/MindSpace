package com.example.mindspace.service.impl;

import com.example.mindspace.dao.ScheduleRepository;
import com.example.mindspace.dao.TimeCellRepository;
import com.example.mindspace.model.TimeCell;
import com.example.mindspace.service.interfaces.TimeCellService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimeCellServiceImpl implements TimeCellService {
    private final TimeCellRepository timeCellRepository;
    private final ScheduleRepository scheduleRepository;

    @Override
    public void generateTimeCells() {

    }
}
