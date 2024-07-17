package com.service.point.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

 class RabbitMessageConvertExceptionTest {

    @Test
     void testRabbitMessageConvertException() {
        // Given
        String errorMessage = "Failed to convert RabbitMQ message";

        // When, Then
        assertThrows(RabbitMessageConvertException.class, () -> {
            throw new RabbitMessageConvertException(errorMessage);
        });
    }
}
