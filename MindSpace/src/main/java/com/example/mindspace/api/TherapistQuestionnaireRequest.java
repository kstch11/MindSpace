package com.example.mindspace.api;

import java.time.LocalDateTime;
import java.util.List;

public record TherapistQuestionnaireRequest(
        String name,
        String surname,
        LocalDateTime birthDate,
        String gender,
        String description,
        List<String> topics,
        String education,
        String therapeuticCommunity,
        List<String> languages,
        String personalPsychotherapy,
        Integer experience,
        String phoneNumber
) {
}
