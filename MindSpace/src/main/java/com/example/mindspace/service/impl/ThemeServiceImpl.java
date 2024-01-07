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

    /**
     * Retrieves a list of all themes.
     *
     * @return List of ThemeResponse objects representing all themes.
     */
    public List<ThemeResponse> findAllThemes() {
        return themeRepository.findAll().stream().map(t -> new ThemeResponse(
                t.getId(),
                t.getName()
        )).toList();
    }

    public Theme findByName(String name) {
        return themeRepository.findByName(name);
    }
}
