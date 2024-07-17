package com.service.point.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

 class PointUsageKindTest {

    @Test
     void testFromCodeValid() {
        // Given
        int code = 0;

        // When
        PointUsageKind kind = PointUsageKind.fromCode(code);

        // Then
        assertNotNull(kind);
        assertEquals(code, kind.getCode());
        assertEquals("결재", kind.getValue());
    }

    @Test
     void testFromCodeInvalid() {
        // Given
        int invalidCode = 999;

        // Then
        assertThrows(IllegalArgumentException.class, () -> {
            PointUsageKind.fromCode(invalidCode);
        });
    }

    @Test
     void testFromValueValid() {
        // Given
        String value = "환불";

        // When
        PointUsageKind kind = PointUsageKind.fromValue(value);

        // Then
        assertNotNull(kind);
        assertEquals(value, kind.getValue());
        assertEquals(1, kind.getCode());
    }

    @Test
     void testFromValueInvalid() {
        // Given
        String invalidValue = "Invalid";

        // Then
        assertThrows(IllegalArgumentException.class, () -> {
            PointUsageKind.fromValue(invalidValue);
        });
    }
}
