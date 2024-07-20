package com.service.point.dto.request;

import com.service.point.dto.message.PointRewardRefundMessageDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PointRewardRefundRequestDtoTest {

    @Test
     void testNoArgsConstructor() {
        // Given
        PointRewardRefundMessageDto dto;

        // When
        dto = new PointRewardRefundMessageDto();

        // Then
        assertNull(dto.getPayment()); // Check default value for Long (null)
    }

    @Test
     void testConstructorAndGetters() {
        // Given
        Long accumulatedPoint = 700L;

        // When
        PointRewardRefundMessageDto dto = new PointRewardRefundMessageDto();
        dto.setPayment(accumulatedPoint);

        // Then

    }
}
