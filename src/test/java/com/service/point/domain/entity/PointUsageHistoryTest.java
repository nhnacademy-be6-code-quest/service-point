package com.service.point.domain.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

 class PointUsageHistoryTest {

    @Test
     void testConstructorAndGetters() {
        // Given
        Integer pointUsageAmount = 50;
        PointUsageType pointUsageType = new PointUsageType(); // Create PointUsageType as needed
        long clientId = 123;

        // When
        PointUsageHistory history = new PointUsageHistory(pointUsageAmount, pointUsageType, clientId);

        // Then
        assertNotNull(history);
        assertEquals(pointUsageAmount, history.getPointUsageAmount());
        assertEquals(pointUsageType, history.getPointUsageType());
        assertEquals(clientId, history.getClientId());
    }

    @Test
     void testNoArgsConstructor() {
        // Given
        PointUsageHistory history = new PointUsageHistory();

        // Then
        assertNotNull(history);
    }
 }
