package com.service.point.dto.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

 class TotalPointAmountResponseDtoTest {

    @Test
     void testNoArgsConstructor() {
        // Given
        TotalPointAmountResponseDto dto;

        // When
        dto = new TotalPointAmountResponseDto();

        // Then
        assertNotNull(dto);
        assertEquals(null, dto.getTotalPoint()); // Check default value for Integer (null)
        assertEquals(null, dto.getPointAccumulationRate()); // Check default value for Integer (null)
    }

    @Test
     void testSetterAndGetter() {
        // Given
        Integer totalPoint = 1000;
        Integer pointAccumulationRate = 5;

        // When
        TotalPointAmountResponseDto dto = new TotalPointAmountResponseDto();
        dto.setTotalPoint(totalPoint);
        dto.setPointAccumulationRate(pointAccumulationRate);

        // Then
        assertEquals(totalPoint, dto.getTotalPoint());
        assertEquals(pointAccumulationRate, dto.getPointAccumulationRate());
    }
}
