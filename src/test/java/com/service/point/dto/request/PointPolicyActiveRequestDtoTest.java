package com.service.point.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

 class PointPolicyActiveRequestDtoTest {

    @Test
     void testConstructorAndGetters() {
        // Given
        long pointPolicyId = 1L;
        String pointAccumulationType = "Type1";

        // When
        PointPolicyActiveRequestDto dto = new PointPolicyActiveRequestDto();
        dto.setPointPolicyId(pointPolicyId);
        dto.setPointAccumulationType(pointAccumulationType);

        // Then
        assertEquals(pointPolicyId, dto.getPointPolicyId());
        assertEquals(pointAccumulationType, dto.getPointAccumulationType());
    }

    @Test
     void testNoArgsConstructor() {
        // Given
        PointPolicyActiveRequestDto dto;

        // When
        dto = new PointPolicyActiveRequestDto();

        // Then
        assertEquals(0L, dto.getPointPolicyId()); // Check default value for long (0L)
    }
}
