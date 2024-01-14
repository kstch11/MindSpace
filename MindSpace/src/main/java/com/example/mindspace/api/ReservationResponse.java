package com.example.mindspace.api;

import java.time.LocalDateTime;

public record ReservationResponse(
        Integer id,
        String start,
        String end,
        ClientResponse clientResponse,
        TherapistResponse therapist
) {
}
