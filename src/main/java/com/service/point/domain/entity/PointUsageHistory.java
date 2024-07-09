package com.service.point.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class PointUsageHistory {

    @Id
    private long clientPointUsageHistoryId;
    private long clientId;
    private long paymentId;
    private LocalDate clientPointUsageHistoryDatetime;
    private Integer clientPointUsageAmount;


}
