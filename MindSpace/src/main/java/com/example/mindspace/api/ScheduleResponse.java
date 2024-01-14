package com.example.mindspace.api;

import com.example.mindspace.model.TimeCell;

import java.util.List;

public record ScheduleResponse(Integer id, Integer therapistId, List<TimeCellResponse> timeCells) {
}
