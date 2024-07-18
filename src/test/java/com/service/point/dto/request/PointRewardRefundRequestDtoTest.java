package com.service.point.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PointRewardRefundRequestDtoTest {

    @Test
     void testNoArgsConstructor() {
        // Given
        PointRewardRefundRequestDto dto;

        // When
        dto = new PointRewardRefundRequestDto();

        // Then
        assertNull(dto.getAccumulatedPoint()); // Check default value for Long (null)
    }

    @Test
     void testConstructorAndGetters() {
        // Given
        Long accumulatedPoint = 700L;

        // When
        PointRewardRefundRequestDto dto = new PointRewardRefundRequestDto();
        dto.setAccumulatedPoint(accumulatedPoint);

        // Then

    }
}
