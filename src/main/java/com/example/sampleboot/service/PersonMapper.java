package com.example.sampleboot.service;

import com.example.sampleboot.dto.PersonDto;
import com.example.sampleboot.jpa.Gender;
import com.example.sampleboot.jpa.PersonEntity;

public class PersonMapper {
    public PersonEntity map(final PersonDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        final PersonEntity personEntity = new PersonEntity();
        if (dto.id() != null) {
            personEntity.setId(Long.parseLong(dto.id()));
        }
        personEntity.setVersion(dto.version());
        personEntity.setName(dto.name());
        if (dto.gender() != null) {
            personEntity.setGender(Gender.valueOf(dto.gender()));
        }
        personEntity.setBirthDate(dto.birthDate());
        return personEntity;
    }

    public PersonDto map(final PersonEntity entity) {
        if (entity == null) {
            return null;
        }
        if (entity.getId() == null) {
            throw new IllegalStateException("ID field cannot be null");
        }
        return new PersonDto(entity.getId().toString(), entity.getVersion(), entity.getName(),
                entity.getBirthDate(), entity.getGender() != null ? entity.getGender().name() : null);
    }
}
