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
        assertEquals(null, dto.getPointUsagePayment()); // Check default value for Integer (null)
    }

    @Test
     void testConstructorAndGetters() {
        // Given
        Integer pointUsagePayment = 500;

        // When
        PointUsagePaymentRequestDto dto = new PointUsagePaymentRequestDto();
        dto.setPointUsagePayment(pointUsagePayment);

        // Then
        assertEquals(pointUsagePayment, dto.getPointUsagePayment());
    }
}
