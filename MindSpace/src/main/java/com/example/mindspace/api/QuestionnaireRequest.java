package com.example.mindspace.api;

import java.util.List;

public record QuestionnaireRequest(
    String name,
    String surname,
    String phoneNumber,
    List<QuestionnaireAnswer> answers
) { }
