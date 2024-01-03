package com.example.mindspace.controller;

import com.example.mindspace.api.ThemeResponse;
import com.example.mindspace.service.impl.ThemeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/themes")
public class ThemeController {
    private final ThemeServiceImpl themeService;

    @Autowired
    public ThemeController(ThemeServiceImpl themeService) {
        this.themeService = themeService;
    }

    @GetMapping("/allThemes")
    public ResponseEntity<List<ThemeResponse>> getAllThemes() {
        return new ResponseEntity<>(themeService.findAllThemes(), HttpStatus.OK);
    }
}
