package com.example.mindspace.api;

public record CreateReviewRequest(
    Integer author,
    String text
) { }
