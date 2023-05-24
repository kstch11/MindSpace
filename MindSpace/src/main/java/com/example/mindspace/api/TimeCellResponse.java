package com.example.mindspace.api;

import java.time.LocalDate;

public record TimeCellResponse(
        Integer id,
        LocalDate date
) {
}
