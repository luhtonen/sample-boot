package com.example.sampleboot.service;

import com.example.sampleboot.dto.PersonDto;
import com.example.sampleboot.jpa.Gender;
import com.example.sampleboot.jpa.PersonEntity;

public class PersonMapper {
    public PersonEntity map(final PersonDto dto) {
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
        return new PersonDto(entity.getId().toString(), entity.getVersion(), entity.getName(),
                entity.getBirthDate(), entity.getGender() != null ?entity.getGender().name(): null);
    }

}
