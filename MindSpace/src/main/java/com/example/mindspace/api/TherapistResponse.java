package com.example.mindspace.api;

public record TherapistResponse(
        Integer id,
        String name,
        String surname,
        String phone,
        String email
) {
}
