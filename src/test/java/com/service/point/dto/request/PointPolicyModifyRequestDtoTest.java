package com.service.point.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PointPolicyModifyRequestDtoTest {

    @Test
     void testNoArgsConstructor() {
        // Given
        PointPolicyModifyRequestDto dto;

        // When
        dto = new PointPolicyModifyRequestDto();

        // Then
        assertEquals(0L, dto.getPointPolicyId()); // Check default value for long (0L)
        assertNull(dto.getPointValue()); // Check default value for Long (null)
    }

    @Test
     void testConstructorAndGetters() {
        // Given
        long pointPolicyId = 1L;
        Long pointValue = 100L;

        // When
        PointPolicyModifyRequestDto dto = new PointPolicyModifyRequestDto();
        dto.setPointPolicyId(pointPolicyId);
        dto.setPointValue(pointValue);

        // Then
        assertEquals(pointPolicyId, dto.getPointPolicyId());
    }
}
