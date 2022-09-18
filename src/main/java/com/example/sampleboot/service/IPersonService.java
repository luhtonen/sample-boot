package com.example.sampleboot.service;

import com.example.sampleboot.dto.PersonDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPersonService {
    List<PersonDto> getPersons();

    PersonDto getPerson(final String personId);

    PersonDto createPerson(final PersonDto person);

    PersonDto updatePerson(final String personId, final PersonDto person);

    void deletePersons(final String personId);

}
