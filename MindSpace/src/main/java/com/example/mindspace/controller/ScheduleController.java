package com.example.mindspace.controller;

import com.example.mindspace.api.ScheduleResponse;
import com.example.mindspace.service.impl.ScheduleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleServiceImpl scheduleService;

    @Autowired
    public ScheduleController(ScheduleServiceImpl scheduleService) {
        this.scheduleService = scheduleService;
    }

    /**
     * Retrieves a list of all schedules.
     *
     * @return ResponseEntity containing a list of schedule responses and an HTTP status code.
     */
    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleResponse>> getSchedule() {
        return new ResponseEntity<>(scheduleService.findAll(), HttpStatus.OK);
    }
}
