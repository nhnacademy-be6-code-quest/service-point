package com.service.point.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity
@Data
@NoArgsConstructor
public class PointPolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pointPolicyId;
    private String pointPolicyName;
    private Integer pointPolicyAccumulationRate;
    private Integer pointPolicyAccumulationAmount;
    private LocalDate pointPolicyCreationDate;
    private LocalDate pointPolicyExpirationDate;

    public PointPolicy(String pointPolicyName, Integer pointPolicyAccumulationRate, Integer pointPolicyAccumulationAmount
    ) {
        this.pointPolicyName = pointPolicyName;
        this.pointPolicyAccumulationRate = pointPolicyAccumulationRate;
        this.pointPolicyAccumulationAmount = pointPolicyAccumulationAmount;
        this.pointPolicyCreationDate = LocalDate.now();
    }
}
