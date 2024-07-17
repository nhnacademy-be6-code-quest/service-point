package com.service.point.domain.entity;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

 class PointPolicyTest {

    @Test
     void testConstructorAndGetters() {
        // Given
        String pointAccumulationType = "TYPE_A";
        Integer pointValue = 100;

        // When
        PointPolicy policy = new PointPolicy(pointAccumulationType, pointValue);

        // Then
        assertNotNull(policy);
        assertEquals(pointAccumulationType, policy.getPointAccumulationType());
        assertEquals(pointValue, policy.getPointValue());

    }

    @Test
     void testNoArgsConstructor() {
        // Given
        PointPolicy policy = new PointPolicy();

        // Then
        assertNotNull(policy);

    }

    @Test
     void testSetters() {
        // Given
        PointPolicy policy = new PointPolicy();

        // When
        String newAccumulationType = "TYPE_B";
        Integer newValue = 200;
        LocalDate newExpirationDate = LocalDate.now().plusMonths(6);
        policy.setPointValue(newValue);
        policy.setPointPolicyExpirationDate(newExpirationDate);

        // Then
        assertEquals(newValue, policy.getPointValue());
        assertEquals(newExpirationDate, policy.getPointPolicyExpirationDate());
    }

    // Additional tests can be added as needed
}
