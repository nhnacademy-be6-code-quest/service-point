package com.service.point.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

 class PointPolicyRegisterRequestDtoTest {

    @Test
     void testNoArgsConstructor() {
        // Given
        PointPolicyRegisterRequestDto dto;

        // When
        dto = new PointPolicyRegisterRequestDto();

        // Then
        assertEquals(null, dto.getPointAccumulationType()); // Check default value for String (null)
    }

    @Test
     void testConstructorAndGetters() {
        // Given
        String pointAccumulationType = "TypeA";
        Integer pointValue = 100;

        // When
        PointPolicyRegisterRequestDto dto = new PointPolicyRegisterRequestDto();
        dto.setPointAccumulationType(pointAccumulationType);
        dto.setPointValue(pointValue);

        // Then
        assertEquals(pointAccumulationType, dto.getPointAccumulationType());
        assertEquals(pointValue, dto.getPointValue());
    }
}
