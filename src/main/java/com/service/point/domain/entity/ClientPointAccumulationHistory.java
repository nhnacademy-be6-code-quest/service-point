package com.service.point.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class ClientPointAccumulationHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long clientPointAccumulationHistoryId;
    @ManyToOne
    @JoinColumn(name = "point_policy_id")
    private PointPolicy pointPolicy;
    private LocalDate clientPointAccumulationHistoryDatetime;
    private Integer clientPointAccumulationAmount;
    private long clientId;


}
