package com.example.mindspace.api;

public record ReviewResponse(
        Integer author,
        Integer therapistId,
        String text
) { }
