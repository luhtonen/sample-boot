package com.example.sampleboot.service;

import com.example.sampleboot.dto.PersonDto;
import com.example.sampleboot.jpa.PersonEntity;
import com.example.sampleboot.jpa.PersonRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class PersonServiceTest {
    @Mock
    private PersonMapper personMapper;
    @Mock
    private PersonRepository personRepository;

    private PersonService personService;

    private AutoCloseable closeable;

    @BeforeEach
    void setup() {
        closeable = openMocks(this);
        personService = new PersonService(personRepository, personMapper);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("getPersons() should return an empty list when no entries in database")
    void testGetAllPersonsWithEmptyResult() {
        when(personRepository.findAll()).thenReturn(emptyList());

        final var result = personService.getPersons();

        assertNotNull(result);
        assertEquals(result.size(), 0);
    }

    @Test
    @DisplayName("getPersons() should return a list of persons found from database")
    void testGetAllPersonsWithResult() {
        final var expected = List.of(mock(PersonEntity.class), mock(PersonEntity.class));
        when(personRepository.findAll()).thenReturn(expected);
        when(personMapper.map(any(PersonEntity.class))).thenReturn(mockPersonDto());

        final var result = personService.getPersons();

        assertNotNull(result);
        assertEquals(result.size(), expected.size());
    }

    @Test
    @DisplayName("getPerson() should throw an exception when person not found from database")
    void testGetPersonWhenPersonNotFound() {
        when(personRepository.findById(anyLong())).thenReturn(Optional.empty());

        final var result =
                assertThrows(EntityNotFoundException.class, () -> personService.getPerson("1"));

        assertNotNull(result);
        assertEquals(result.getMessage(), "No person found with given id 1");
    }

    @Test
    @DisplayName("getPerson() should return a persons found from database")
    void testPersonsWithResult() {
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(mock(PersonEntity.class)));
        when(personMapper.map(any(PersonEntity.class))).thenReturn(mockPersonDto());

        final var result = personService.getPersons();

        assertNotNull(result);
    }

    private PersonDto mockPersonDto() {
        return new PersonDto("1", 1L, "Test", LocalDate.now(), "WontSay");
    }
}
