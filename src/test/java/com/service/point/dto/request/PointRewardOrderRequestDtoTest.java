package com.service.point.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

 class PointRewardOrderRequestDtoTest {

    @Test
     void testNoArgsConstructor() {
        // Given
        PointRewardOrderRequestDto dto;

        // When
        dto = new PointRewardOrderRequestDto();

        // Then
        assertEquals(null, dto.getAccumulatedPoint()); // Check default value for Integer (null)
    }

    @Test
     void testConstructorAndGetters() {
        // Given
        Integer accumulatedPoint = 500;

        // When
        PointRewardOrderRequestDto dto = new PointRewardOrderRequestDto();
        dto.setAccumulatedPoint(accumulatedPoint);

        // Then
        assertEquals(accumulatedPoint, dto.getAccumulatedPoint());
    }
}
