package com.example.sampleboot.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public record PersonDto(String id, Long version, String name, LocalDate birthDate,
                        String gender) implements Serializable {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PersonDto personDto = (PersonDto) o;
        return Objects.equals(id, personDto.id) &&
                Objects.equals(version, personDto.version) &&
                Objects.equals(name, personDto.name) &&
                Objects.equals(birthDate, personDto.birthDate) &&
                Objects.equals(gender, personDto.gender);
    }

    @Override
    public String toString() {
        return "PersonDto{" +
                "id='" + id + '\'' +
                ", version=" + version +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", gender='" + gender + '\'' +
                '}';
    }
}
