package com.service.point.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

 class PointPolicyNotFoundExceptionTest {

    @Test
     void testPointPolicyNotFoundException() {
        // Given
        String errorMessage = "Point policy not found";

        // When, Then
        assertThrows(PointPolicyNotFoundException.class, () -> {
            throw new PointPolicyNotFoundException(errorMessage);
        });
    }
}
