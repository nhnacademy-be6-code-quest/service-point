package com.service.point.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

 class PointPolicyModifyRequestDtoTest {

    @Test
     void testNoArgsConstructor() {
        // Given
        PointPolicyModifyRequestDto dto;

        // When
        dto = new PointPolicyModifyRequestDto();

        // Then
        assertEquals(0L, dto.getPointPolicyId()); // Check default value for long (0L)
        assertEquals(null, dto.getPointValue()); // Check default value for Integer (null)
    }

    @Test
     void testConstructorAndGetters() {
        // Given
        long pointPolicyId = 1L;
        Integer pointValue = 100;

        // When
        PointPolicyModifyRequestDto dto = new PointPolicyModifyRequestDto();
        dto.setPointPolicyId(pointPolicyId);
        dto.setPointValue(pointValue);

        // Then
        assertEquals(pointPolicyId, dto.getPointPolicyId());
        assertEquals(pointValue, dto.getPointValue());
    }
}
