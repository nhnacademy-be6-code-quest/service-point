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
public class PointUsageHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pointUsageHistoryId;
    private LocalDate pointUsageHistoryDate;
    private Long pointUsageAmount;
    @ManyToOne
    @JoinColumn(name = "pointUsageTypeId")
    private PointUsageType pointUsageType;
    private long clientId;

    public PointUsageHistory(Long pointUsageAmount, PointUsageType pointUsageType,
        long clientId) {
        this.pointUsageHistoryDate=LocalDate.now();
        this.pointUsageAmount = pointUsageAmount;
        this.pointUsageType = pointUsageType;
        this.clientId = clientId;
    }
}
