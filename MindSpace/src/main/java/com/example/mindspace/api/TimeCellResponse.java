package com.example.mindspace.api;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TimeCellResponse(
        Integer id,
        LocalDateTime startTime,
        LocalDateTime endTime,
        boolean isReserved,
        boolean isExpired,
        Integer clientId,
        Integer therapistId
) {
}
