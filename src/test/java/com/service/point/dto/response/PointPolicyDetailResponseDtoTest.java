package com.service.point.dto.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

 class PointPolicyDetailResponseDtoTest {

    @Test
     void testNoArgsConstructor() {
        // Given
        PointPolicyDetailResponseDto dto;

        // When
        dto = new PointPolicyDetailResponseDto();

        // Then
        assertNotNull(dto);
        assertEquals(null, dto.getPointAccumulationType()); // Check default value for String (null)
        assertEquals(null, dto.getPointValue()); // Check default value for Integer (null)
    }

    @Test
     void testSetterAndGetter() {
        // Given
        String pointAccumulationType = "TypeA";
        Integer pointValue = 100;

        // When
        PointPolicyDetailResponseDto dto = new PointPolicyDetailResponseDto();
        dto.setPointAccumulationType(pointAccumulationType);
        dto.setPointValue(pointValue);

        // Then
        assertEquals(pointAccumulationType, dto.getPointAccumulationType());
        assertEquals(pointValue, dto.getPointValue());
    }
}
