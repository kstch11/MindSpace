package com.example.mindspace.api;

public record QuestionnaireSingleAnswer(
        Integer id,
        String question,
        String option
) implements QuestionnaireAnswer { }
