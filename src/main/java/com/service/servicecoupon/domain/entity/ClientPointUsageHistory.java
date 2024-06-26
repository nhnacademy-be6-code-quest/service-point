package com.service.servicecoupon.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class ClientPointUsageHistory {

    @Id
    private long clientPointUsageHistoryId;
    private LocalDateTime clientPointUsageHistoryDatetime;
    private Integer clientPointUsageAmount;
    @ManyToOne
    @JoinColumn(name = "point_type_id")
    private PointType pointType;
    private long clientId;

}
