package com.service.point.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PointPolicyRegisterRequestDtoTest {

    @Test
     void testNoArgsConstructor() {
        // Given
        PointPolicyRegisterRequestDto dto;

        // When
        dto = new PointPolicyRegisterRequestDto();

        // Then
        assertNull(dto.getPointAccumulationType()); // Check default value for String (null)
    }

    @Test
     void testConstructorAndGetters() {
        // Given
        String pointAccumulationType = "TypeA";
        Long pointValue = 100L;

        // When
        PointPolicyRegisterRequestDto dto = new PointPolicyRegisterRequestDto();
        dto.setPointAccumulationType(pointAccumulationType);
        dto.setPointValue(pointValue);

        // Then
        assertEquals(pointAccumulationType, dto.getPointAccumulationType());
    }
}
