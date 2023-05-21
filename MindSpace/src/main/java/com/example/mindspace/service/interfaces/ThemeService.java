package com.example.mindspace.service.interfaces;

import com.example.mindspace.model.Theme;
import com.example.mindspace.model.Therapist;

import java.util.List;

public interface ThemeService {
    public void createTheme(Theme theme);

    public void compareThemes(Therapist therapist, List<Theme> themes);
}
