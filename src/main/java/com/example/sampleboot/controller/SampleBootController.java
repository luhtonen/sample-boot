package com.example.sampleboot.controller;

import com.example.sampleboot.dto.PersonDto;
import com.example.sampleboot.service.IPersonService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/persons")
public class SampleBootController {
    private final IPersonService personService;

    public SampleBootController(final IPersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<PersonDto> getPersons() {
        return personService.getPersons();
    }

    @GetMapping(value = "/{personId}")
    public PersonDto getPerson(@PathVariable String personId) {
        return personService.getPerson(personId);
    }

    @PostMapping()
    public PersonDto createPerson(@RequestBody final PersonDto person) {
        return personService.createPerson(person);
    }

    @PutMapping(value = "/{personId}")
    public PersonDto updatePerson(@PathVariable final String personId,
                                  @RequestBody final PersonDto person) {
        return personService.updatePerson(personId, person);
    }

    @DeleteMapping(value = "/{personId}")
    public void deletePerson(@PathVariable final String personId) {
        personService.deletePersons(personId);
    }
}
