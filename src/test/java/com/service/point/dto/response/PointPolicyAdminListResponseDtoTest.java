package com.service.point.dto.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

 class PointPolicyAdminListResponseDtoTest {

    @Test
     void testNoArgsConstructor() {
        // Given
        PointPolicyAdminListResponseDto dto;

        // When
        dto = new PointPolicyAdminListResponseDto();

        // Then
        assertNotNull(dto);
        assertEquals(0, dto.getPointPolicyId()); // Check default value for long (0)
        assertEquals(null, dto.getPointAccumulationType()); // Check default value for String (null)
        assertEquals(null, dto.getPointValue()); // Check default value for Integer (null)
        assertEquals(null, dto.getPointPolicyCreationDate()); // Check default value for String (null)
        assertEquals(null, dto.getPointStatus()); // Check default value for String (null)
    }

    @Test
     void testSetterAndGetter() {
        // Given
        long pointPolicyId = 1L;
        String pointAccumulationType = "TypeA";
        Integer pointValue = 100;
        String pointPolicyCreationDate = "2023-07-15";
        String pointStatus = "Active";

        // When
        PointPolicyAdminListResponseDto dto = new PointPolicyAdminListResponseDto();
        dto.setPointPolicyId(pointPolicyId);
        dto.setPointAccumulationType(pointAccumulationType);
        dto.setPointValue(pointValue);
        dto.setPointPolicyCreationDate(pointPolicyCreationDate);
        dto.setPointStatus(pointStatus);

        // Then
        assertEquals(pointPolicyId, dto.getPointPolicyId());
        assertEquals(pointAccumulationType, dto.getPointAccumulationType());
        assertEquals(pointValue, dto.getPointValue());
        assertEquals(pointPolicyCreationDate, dto.getPointPolicyCreationDate());
        assertEquals(pointStatus, dto.getPointStatus());
    }




}
