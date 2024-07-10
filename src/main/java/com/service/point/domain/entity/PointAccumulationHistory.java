package com.service.point.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class PointAccumulationHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pointAccumulationHistoryId;
    @ManyToOne
    @JoinColumn(name = "point_policy_id")
    private PointPolicy pointPolicy;
    private long clientId;
    private LocalDate PointAccumulationHistoryDate;
    private Integer pointAccumulationAmount;

    public PointAccumulationHistory(PointPolicy pointPolicy, long clientId, Integer pointAccumulationAmount) {
        this.pointPolicy = pointPolicy;
        this.clientId = clientId;
        this.PointAccumulationHistoryDate=LocalDate.now();
        this.pointAccumulationAmount=pointAccumulationAmount;
    }
}
