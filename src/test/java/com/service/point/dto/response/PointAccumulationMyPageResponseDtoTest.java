package com.service.point.dto.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

 class PointAccumulationMyPageResponseDtoTest {

    @Test
     void testNoArgsConstructor() {
        // Given
        PointAccumulationMyPageResponseDto dto;

        // When
        dto = new PointAccumulationMyPageResponseDto();

        // Then
        assertEquals(null, dto.getPointAccumulationAmount()); // Check default value for Integer (null)
        assertEquals(null, dto.getPointAccumulationHistoryDate()); // Check default value for String (null)
        assertEquals(null, dto.getPointAccumulationType()); // Check default value for String (null)
    }

    @Test
     void testSetterAndGetter() {
        // Given
        Integer pointAccumulationAmount = 100;
        String pointAccumulationHistoryDate = "2023-07-15";
        String pointAccumulationType = "TypeA";

        // When
        PointAccumulationMyPageResponseDto dto = new PointAccumulationMyPageResponseDto();
        dto.setPointAccumulationAmount(pointAccumulationAmount);
        dto.setPointAccumulationHistoryDate(pointAccumulationHistoryDate);
        dto.setPointAccumulationType(pointAccumulationType);

        // Then
        assertEquals(pointAccumulationAmount, dto.getPointAccumulationAmount());
        assertEquals(pointAccumulationHistoryDate, dto.getPointAccumulationHistoryDate());
        assertEquals(pointAccumulationType, dto.getPointAccumulationType());
    }
}
