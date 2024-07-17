package com.service.point.dto.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

 class ClientGradeRateResponseDtoTest {

    @Test
     void testNoArgsConstructor() {
        // Given
        ClientGradeRateResponseDto dto;

        // When
        dto = new ClientGradeRateResponseDto();

        // Then
        assertEquals(null, dto.getRatePolicyId()); // Check default value for Long (null)
    }

    @Test
     void testAllArgsConstructor() {
        // Given
        Long ratePolicyId = 123L;

        // When
        ClientGradeRateResponseDto dto = new ClientGradeRateResponseDto(ratePolicyId);

        // Then
        assertEquals(ratePolicyId, dto.getRatePolicyId());
    }

    @Test
     void testSetterAndGetter() {
        // Given
        Long ratePolicyId = 456L;

        // When
        ClientGradeRateResponseDto dto = new ClientGradeRateResponseDto();
        dto.setRatePolicyId(ratePolicyId);

        // Then
        assertEquals(ratePolicyId, dto.getRatePolicyId());
    }
}
