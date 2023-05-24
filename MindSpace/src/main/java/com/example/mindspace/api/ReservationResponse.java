package com.example.mindspace.api;

public record ReservationResponse(
        Integer id,
        String date,
        ClientResponse clientResponse,
        TherapistResponse therapist
) {
}
