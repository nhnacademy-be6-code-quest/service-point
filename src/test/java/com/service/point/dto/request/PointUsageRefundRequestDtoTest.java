package com.service.point.dto.request;

import com.service.point.dto.message.PointUsagePaymentMessageDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

 class PointUsageRefundRequestDtoTest {

    @Test
     void testNoArgsConstructor() {
        // Given
        PointUsagePaymentMessageDto dto;

        // When
        dto = new PointUsagePaymentMessageDto();

        // Then
        assertEquals(null, dto.getPointUsagePayment()); // Check default value for Long (null)
    }

    @Test
     void testConstructorAndGetters() {
        // Given
        Long pointUsagePayment = 200L;

        // When
        PointUsagePaymentMessageDto dto = new PointUsagePaymentMessageDto();
        dto.setPointUsagePayment(pointUsagePayment);

        // Then
        assertEquals(pointUsagePayment, dto.getPointUsagePayment());
    }
}
