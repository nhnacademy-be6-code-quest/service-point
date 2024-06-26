package com.service.servicecoupon.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PointPolicy {
    @Id
    private long pointPolicyId;
    private String pointPolicyName;
    private Integer pointPolicyAccumulationRate;
    private Integer pointPolicyAccumulationAmount;
}
