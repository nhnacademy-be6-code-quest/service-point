package com.service.point.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

 class PointUsagePaymentRequestDtoTest {

    @Test
     void testNoArgsConstructor() {
        // Given
        PointUsagePaymentRequestDto dto;

        // When
        dto = new PointUsagePaymentRequestDto();

        // Then
        assertEquals(null, dto.getPointUsageAmount()); // Check default value for Long (null)
    }

    @Test
     void testConstructorAndGetters() {
        // Given
        Long pointUsagePayment = 500L;

        // When
        PointUsagePaymentRequestDto dto = new PointUsagePaymentRequestDto();
        dto.setPointUsageAmount(pointUsagePayment);

        // Then
        assertEquals(pointUsagePayment, dto.getPointUsageAmount());
    }
}
