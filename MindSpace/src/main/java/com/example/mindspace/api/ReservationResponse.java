package com.example.mindspace.api;

// client, therapist, date
public record ReservationResponse(
        Integer id,
        String date,
        ClientResponse clientResponse,
        TherapistResponse therapist
) {
}
