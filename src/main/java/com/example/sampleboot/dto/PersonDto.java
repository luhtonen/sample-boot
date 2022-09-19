package com.example.sampleboot.dto;

import java.time.LocalDate;

public record PersonDto(String id, Long version, String name, LocalDate birthDate,
                        String gender) {
}
