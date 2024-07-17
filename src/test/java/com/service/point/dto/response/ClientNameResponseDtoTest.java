package com.service.point.dto.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

 class ClientNameResponseDtoTest {

    @Test
     void testBuilder() {
        // Given
        String clientName = "John Doe";

        // When
        ClientNameResponseDto dto = ClientNameResponseDto.builder()
            .clientName(clientName)
            .build();

        // Then
        assertNotNull(dto);
        assertEquals(clientName, dto.getClientName());
    }

    @Test
     void testNoArgsConstructor() {
        // Given
        ClientNameResponseDto dto;

        // When
        dto = new ClientNameResponseDto();

        // Then
        assertEquals(null, dto.getClientName()); // Check default value for String (null)
    }

    @Test
     void testAllArgsConstructor() {
        // Given
        String clientName = "Jane Doe";

        // When
        ClientNameResponseDto dto = new ClientNameResponseDto(clientName);

        // Then
        assertEquals(clientName, dto.getClientName());
    }

    @Test
     void testSetterAndGetter() {
        // Given
        String clientName = "James Smith";

        // When
        ClientNameResponseDto dto = new ClientNameResponseDto();
        dto.setClientName(clientName);

        // Then
        assertEquals(clientName, dto.getClientName());
    }
}
