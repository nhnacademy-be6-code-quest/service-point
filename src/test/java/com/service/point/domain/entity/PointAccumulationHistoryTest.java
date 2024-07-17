package com.service.point.domain.entity;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

 class PointAccumulationHistoryTest {

    @Test
     void testConstructorAndGetters() {
        // Given
        PointPolicy pointPolicy = new PointPolicy(); // Replace with actual PointPolicy instance if needed
        long clientId = 123L;
        Integer pointAccumulationAmount = 100;

        // When
        PointAccumulationHistory history = new PointAccumulationHistory(pointPolicy, clientId, pointAccumulationAmount);

        // Then
        assertNotNull(history);
        assertEquals(pointPolicy, history.getPointPolicy());
        assertEquals(clientId, history.getClientId());
        assertEquals(LocalDate.now(), history.getPointAccumulationHistoryDate());
        assertEquals(pointAccumulationAmount, history.getPointAccumulationAmount());
    }

    @Test
     void testNoArgsConstructor() {
        // Given
        PointAccumulationHistory history = new PointAccumulationHistory();

        // Then
        assertNotNull(history);
        assertNull(history.getPointPolicy());
        assertEquals(0L, history.getClientId());
        assertNull(history.getPointAccumulationHistoryDate());
        assertNull(history.getPointAccumulationAmount());
    }

}
