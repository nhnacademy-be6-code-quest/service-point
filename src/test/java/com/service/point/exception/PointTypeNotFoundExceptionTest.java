package com.service.point.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

 class PointTypeNotFoundExceptionTest {

    @Test
     void testPointTypeNotFoundException() {
        // Given
        String errorMessage = "Point type not found";

        // When, Then
        assertThrows(PointTypeNotFoundException.class, () -> {
            throw new PointTypeNotFoundException(errorMessage);
        });
    }
}
