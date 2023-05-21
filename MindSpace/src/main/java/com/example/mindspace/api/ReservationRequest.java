package com.example.mindspace.api;

public record ReservationRequest(
        Integer therapistId,
        Integer timeCellId
) { }
