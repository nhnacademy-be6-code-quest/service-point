package com.service.point.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

 class PointStatusTest {

    @Test
     void testFromCodeValid() {
        // Given
        int code = 0;

        // When
        PointStatus status = PointStatus.fromCode(code);

        // Then
        assertNotNull(status);
        assertEquals(code, status.getCode());
        assertEquals("활성", status.getValue());
    }

    @Test
     void testFromCodeInvalid() {
        // Given
        int invalidCode = 999;

        // Then
        assertThrows(IllegalArgumentException.class, () -> {
            PointStatus.fromCode(invalidCode);
        });
    }

    @Test
     void testFromValueValid() {

        String value = "비활성화";

        // When
        PointStatus status = PointStatus.fromValue(value);

        // Then
        assertNotNull(status);
        assertEquals(value, status.getValue());
        assertEquals(1, status.getCode());
    }

    @Test
     void testFromValueInvalid() {
        // Given
        String invalidValue = "Invalid";

        // Then
        assertThrows(IllegalArgumentException.class, () -> {
            PointStatus.fromValue(invalidValue);
        });
    }
}
