package com.service.point.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

 class PointUsageRefundRequestDtoTest {

    @Test
     void testNoArgsConstructor() {
        // Given
        PointUsageRefundRequestDto dto;

        // When
        dto = new PointUsageRefundRequestDto();

        // Then
        assertEquals(null, dto.getPointUsagePayment()); // Check default value for Long (null)
    }

    @Test
     void testConstructorAndGetters() {
        // Given
        Long pointUsagePayment = 200L;

        // When
        PointUsageRefundRequestDto dto = new PointUsageRefundRequestDto();
        dto.setPointUsagePayment(pointUsagePayment);

        // Then
        assertEquals(pointUsagePayment, dto.getPointUsagePayment());
    }
}
