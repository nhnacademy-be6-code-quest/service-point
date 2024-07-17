package com.service.point.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

 class PointRewardRefundRequestDtoTest {

    @Test
     void testNoArgsConstructor() {
        // Given
        PointRewardRefundRequestDto dto;

        // When
        dto = new PointRewardRefundRequestDto();

        // Then
        assertEquals(null, dto.getAccumulatedPoint()); // Check default value for Integer (null)
    }

    @Test
     void testConstructorAndGetters() {
        // Given
        Integer accumulatedPoint = 700;

        // When
        PointRewardRefundRequestDto dto = new PointRewardRefundRequestDto();
        dto.setAccumulatedPoint(accumulatedPoint);

        // Then
        assertEquals(accumulatedPoint, dto.getAccumulatedPoint());
    }
}
