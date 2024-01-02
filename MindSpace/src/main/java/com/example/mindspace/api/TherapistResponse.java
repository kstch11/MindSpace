package com.example.mindspace.api;

public record TherapistResponse(
        Integer id,
        String name,
        String surname,
        String phone,
        String email,
        boolean finishedRegistration,
        String description,
        String education,
        String languages,
        String personalTherapy,
        String photo,
        String therapeuticCommunity,
        boolean approved,
        boolean isTherapist
) implements UserResponse { }
