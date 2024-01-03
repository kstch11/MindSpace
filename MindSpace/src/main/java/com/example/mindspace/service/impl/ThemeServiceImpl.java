package com.example.mindspace.service.impl;

import com.example.mindspace.api.ThemeResponse;
import com.example.mindspace.model.Theme;
import com.example.mindspace.repository.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ThemeServiceImpl {
    private final ThemeRepository themeRepository;

    public List<ThemeResponse> findAllThemes() {
        return themeRepository.findAll().stream().map(t -> new ThemeResponse(
                t.getId(),
                t.getName()
        )).toList();
    }
}
