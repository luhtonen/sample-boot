package com.example.sampleboot.service;

import com.example.sampleboot.dto.PersonDto;
import com.example.sampleboot.jpa.Gender;
import com.example.sampleboot.jpa.PersonEntity;
import com.example.sampleboot.jpa.PersonRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public class PersonService implements IPersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public PersonService(final PersonRepository personRepository,
                         final PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    @Override
    public List<PersonDto> getPersons() {
        return personRepository.findAll().stream()
                .map(personMapper::map)
                .toList();
    }

    @Override
    public PersonDto getPerson(final String personId) {
        return personRepository.findById(Long.parseLong(personId))
                .map(personMapper::map)
                .orElseThrow(() -> new EntityNotFoundException("No person found with given id " + personId));
    }

    @Override
    public PersonDto createPerson(final PersonDto person) {
        final PersonEntity personEntity = personMapper.map(person);
        return personMapper.map(personRepository.save(personEntity));
    }

    @Override
    public PersonDto updatePerson(final String personId,
                                  final PersonDto person) {
        final PersonEntity existingPerson = personRepository.findById(Long.parseLong(personId))
                .orElseThrow(() -> new EntityNotFoundException("No person found with given id " + personId));
        existingPerson.setName(person.name());
        existingPerson.setGender(Gender.valueOf(person.gender()));
        existingPerson.setBirthDate(person.birthDate());
        return personMapper.map(personRepository.save(existingPerson));
    }

    @Override
    public void deletePersons(final String personId) {
        personRepository.findById(Long.parseLong(personId))
                .ifPresentOrElse(
                        personRepository::delete,
                        () -> {
                            throw new EntityNotFoundException("No person with given id " + personId);
                        });
    }
}
