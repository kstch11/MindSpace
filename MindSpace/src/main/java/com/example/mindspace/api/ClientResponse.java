package com.example.mindspace.api;

public record ClientResponse(
        Integer id,
        Integer therapistId,
        String name,
        String surname,
        String phone,
        String email,
        boolean finishedRegistration,
        boolean isTherapist
) implements UserResponse { }
