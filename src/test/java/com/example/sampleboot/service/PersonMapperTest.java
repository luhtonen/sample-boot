package com.example.sampleboot.service;

import com.example.sampleboot.dto.PersonDto;
import com.example.sampleboot.jpa.PersonEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PersonMapperTest {
    private final PersonMapper personMapper = new PersonMapper();

    @Nested
    @DisplayName("map to PersonEntity")
    class TestToEntityMapping {
        @Test
        @DisplayName("should throw an exception when DTO is null")
        void testNullInput() {
            assertThatThrownBy(() -> personMapper.map((PersonDto) null))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Input cannot be null");
        }
    }

    @Nested
    @DisplayName("map to PersonDto")
    class TestToDtoMapping {
        @Test
        @DisplayName("should return null when Entity is null")
        void testNullInput() {
            assertThat(personMapper.map((PersonEntity) null)).isNull();
        }
    }
}
