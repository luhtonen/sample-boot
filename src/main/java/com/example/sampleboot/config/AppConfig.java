package com.example.sampleboot.config;

import com.example.sampleboot.jpa.PersonRepository;
import com.example.sampleboot.service.IPersonService;
import com.example.sampleboot.service.PersonMapper;
import com.example.sampleboot.service.PersonService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public PersonMapper getPersonMapper() {
        return new PersonMapper();
    }
    @Bean
    public IPersonService getPersonService(final PersonRepository personRepository,
                                           final PersonMapper personMapper) {
        return new PersonService(personRepository, personMapper);
    }
}
