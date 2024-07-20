package com.service.point.dto.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

 class PointAccumulationAdminPageResponseDtoTest {

    @Test
     void testNoArgsConstructor() {
        // Given
        PointAccumulationAdminPageResponseDto dto;

        // When
        dto = new PointAccumulationAdminPageResponseDto();

        // Then
        assertEquals(null, dto.getPointAccumulationAmount()); // Check default value for Long (null)
        assertEquals(null, dto.getPointAccumulationHistoryDate()); // Check default value for String (null)
        assertEquals(null, dto.getPointAccumulationType()); // Check default value for String (null)
        assertEquals(null, dto.getClientName()); // Check default value for String (null)
    }

    @Test
     void testSetterAndGetter() {
        // Given
        Long pointAccumulationAmount = 100L;
        String pointAccumulationHistoryDate = "2023-07-15";
        String pointAccumulationType = "TypeA";
        String clientName = "John Doe";

        // When
        PointAccumulationAdminPageResponseDto dto = new PointAccumulationAdminPageResponseDto();
        dto.setPointAccumulationAmount(pointAccumulationAmount);
        dto.setPointAccumulationHistoryDate(pointAccumulationHistoryDate);
        dto.setPointAccumulationType(pointAccumulationType);
        dto.setClientName(clientName);

        // Then
        assertEquals(pointAccumulationAmount, dto.getPointAccumulationAmount());
        assertEquals(pointAccumulationHistoryDate, dto.getPointAccumulationHistoryDate());
        assertEquals(pointAccumulationType, dto.getPointAccumulationType());
        assertEquals(clientName, dto.getClientName());
    }
}
