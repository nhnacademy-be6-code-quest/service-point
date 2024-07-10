package com.service.point.domain.entity;

import jakarta.persistence.Entity;
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
    private long pointUsageHistoryId;
    private LocalDate pointUsageHistoryDatetime;
    private Integer pointUsageAmount;
    @ManyToOne
    @JoinColumn(name = "pointUsageTypeId")
    private PointUsageType pointUsageType;
    private long clientId;

    public PointUsageHistory(Integer pointUsageAmount, PointUsageType pointUsageType,
        long clientId) {
        this.pointUsageHistoryDatetime=LocalDate.now();
        this.pointUsageAmount = pointUsageAmount;
        this.pointUsageType = pointUsageType;
        this.clientId = clientId;
    }
}
