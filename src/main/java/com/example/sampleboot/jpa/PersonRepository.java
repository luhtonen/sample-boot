package com.example.sampleboot.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
}
