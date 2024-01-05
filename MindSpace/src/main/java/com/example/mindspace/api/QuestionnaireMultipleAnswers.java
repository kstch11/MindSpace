package com.example.mindspace.api;

import java.util.List;

public record QuestionnaireMultipleAnswers(
        Integer id,
        String question,
        List<String> option
) implements QuestionnaireAnswer { }
