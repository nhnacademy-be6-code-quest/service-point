package com.service.servicecoupon.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class PointPolicy {
    @Id
    private long pointPolicyId;
    private String pointPolicyName;
    private Integer pointPolicyAccumulationRate;
    private Integer pointPolicyAccumulationAmount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime pointPolicyCreationDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime pointPolicyExpirationDate;
}
