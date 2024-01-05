package com.example.mindspace.api;

public record AdminResponse(
        Integer id,
        String name,
        boolean isAdmin,
        boolean finishedRegistration
) implements UserResponse { }
