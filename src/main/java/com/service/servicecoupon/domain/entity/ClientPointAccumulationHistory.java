package com.service.servicecoupon.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class ClientPointAccumulationHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long clientPointAccumulationHistoryId;
    private long pointPolicyId;
    private LocalDateTime clientPointAccumulationHistoryDatetime;
    private Integer clientPointAccumulationAmount;
    @OneToMany
    @JoinColumn(name = "point_type_id")
    private List<PointType> pointType;
    private long clientId;


}
