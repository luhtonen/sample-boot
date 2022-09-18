package com.example.sampleboot.controller;

import com.example.sampleboot.dto.PersonDto;
import com.example.sampleboot.exception.PersonNotFoundException;
import com.example.sampleboot.service.IPersonService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SampleBootController.class)
class SampleBootControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IPersonService personService;

    @Test
    @DisplayName("get /persons should return an empty list when no data in database")
    void testGetAllPersonsWithEmptyResponse() throws Exception {
        when(personService.getPersons()).thenReturn(Collections.emptyList());

        final var result = mockMvc.perform(get("/persons"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertThat(result.getResponse()).isNotNull();
        assertThat(result.getResponse().getContentLength()).isEqualTo(0);
    }

    @Test
    @DisplayName("get /persons should return a list of persons found from database")
    void testGetAllPersons() throws Exception {
        final var expected = List.of(mockPersonDto());
        when(personService.getPersons()).thenReturn(expected);

        final var result = mockMvc.perform(get("/persons"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertThat(result.getResponse()).isNotNull();
        assertThat(result.getResponse().getContentAsString().length()).isGreaterThan(0);
    }

    @Test
    @DisplayName("get /persons/{id} should return status 404 when entity not found from database")
    void testGetPersonNotFound() throws Exception {
        when(personService.getPerson(anyString())).thenThrow(PersonNotFoundException.class);

        mockMvc.perform(get("/persons/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("get /persons/{id} should return person found from database")
    void testGetPerson() throws Exception {
        when(personService.getPerson(anyString())).thenReturn(mockPersonDto());

        mockMvc.perform(get("/persons/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    private PersonDto mockPersonDto() {
        return new PersonDto("1", 1L, "Test", LocalDate.now(), "WontSay");
    }
}
