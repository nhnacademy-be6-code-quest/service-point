package com.service.point.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

 class ClientNotFoundExceptionTest {

    @Test
     void testClientNotFoundException() {
        // Given
        String errorMessage = "Client not found";

        // When, Then
        assertThrows(ClientNotFoundException.class, () -> {
            throw new ClientNotFoundException(errorMessage);
        });
    }
}
