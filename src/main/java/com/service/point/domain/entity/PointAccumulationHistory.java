package com.service.point.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class PointAccumulationHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pointAccumulationHistoryId;
    @ManyToOne
    @JoinColumn(name = "point_policy_id")
    private PointPolicy pointPolicy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime clientPointAccumulationHistoryDatetime;
    private long clientId;

    public PointAccumulationHistory(PointPolicy pointPolicy, long clientId){
        this.clientPointAccumulationHistoryDatetime = LocalDateTime.now();
        this.pointPolicy = pointPolicy;
        this.clientId = clientId;
    }
}
