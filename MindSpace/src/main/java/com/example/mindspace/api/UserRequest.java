package com.example.mindspace.api;

public record UserRequest(
        String name,
        String surname,
        String number,
        String email
) {
}
