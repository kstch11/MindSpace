package com.example.mindspace.api;

public record CreateReviewRequest(
    Integer author,
    Integer therapistId,
    String text
) { }
