package com.service.point.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class PointAccumulationHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pointAccumulationHistoryId;
    @ManyToOne
    @JoinColumn(name = "point_policy_id")
    private PointPolicy pointPolicy;
    private long clientId;
    private LocalDate pointAccumulationHistoryDate;
    private Long pointAccumulationAmount;

    public PointAccumulationHistory(PointPolicy pointPolicy, long clientId, Long pointAccumulationAmount) {
        this.pointPolicy = pointPolicy;
        this.clientId = clientId;
        this.pointAccumulationHistoryDate=LocalDate.now();
        this.pointAccumulationAmount=pointAccumulationAmount;
    }
    public void updateAccumulationPoint(long pointAccumulationAmount){
        this.pointAccumulationAmount=pointAccumulationAmount;
    }
}
