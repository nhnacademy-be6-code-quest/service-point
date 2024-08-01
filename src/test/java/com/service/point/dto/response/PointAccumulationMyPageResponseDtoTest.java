package com.service.point.dto.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PointAccumulationMyPageResponseDtoTest {

    @Test
     void testNoArgsConstructor() {
        // Given
        PointAccumulationMyPageResponseDto dto;

        // When
        dto = new PointAccumulationMyPageResponseDto();

        // Then
        assertNull(dto.getPointAccumulationAmount()); // Check default value for Long (null)
        assertNull(dto.getPointAccumulationHistoryDate()); // Check default value for String (null)
        assertNull(dto.getPointAccumulationType()); // Check default value for String (null)
    }

    @Test
     void testSetterAndGetter() {
        // Given
        Long pointAccumulationAmount = 100L;
        String pointAccumulationHistoryDate = "2023-07-15";
        String pointAccumulationType = "TypeA";

        // When
        PointAccumulationMyPageResponseDto dto = new PointAccumulationMyPageResponseDto();
        dto.setPointAccumulationAmount(pointAccumulationAmount);
        dto.setPointAccumulationHistoryDate(pointAccumulationHistoryDate);
        dto.setPointAccumulationType(pointAccumulationType);

        // Then
        assertEquals(pointAccumulationHistoryDate, dto.getPointAccumulationHistoryDate());
        assertEquals(pointAccumulationType, dto.getPointAccumulationType());
    }
}
