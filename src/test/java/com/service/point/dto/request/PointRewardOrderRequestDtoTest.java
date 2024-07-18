package com.service.point.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PointRewardOrderRequestDtoTest {

    @Test
     void testNoArgsConstructor() {
        // Given
        PointRewardOrderRequestDto dto;

        // When
        dto = new PointRewardOrderRequestDto();

        // Then
        assertNull(dto.getAccumulatedPoint()); // Check default value for Long (null)
    }

    @Test
     void testConstructorAndGetters() {
        // Given
        Long accumulatedPoint = 500L;

        // When
        PointRewardOrderRequestDto dto = new PointRewardOrderRequestDto();
        dto.setAccumulatedPoint(accumulatedPoint);

        // Then
        assertEquals(accumulatedPoint, dto.getAccumulatedPoint());
    }
}
