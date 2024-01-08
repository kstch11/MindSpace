package com.example.mindspace.api;

import java.util.List;

public record TherapistResponse(
        Integer id,
        String name,
        String surname,
        String phone,
        String email,
        boolean finishedRegistration,
        String description,
        String education,
        List<LanguageResponse> languages,
        String personalTherapy,
        String photo,
        String therapeuticCommunity,
        boolean approved,
        boolean isTherapist,
        List<TopicResponse> topics,
        Integer experience
) implements UserResponse { }
