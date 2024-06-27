package com.service.servicecoupon.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class ClientPointUsageHistory {

    @Id
    private long clientPointUsageHistoryId;
    private long clientId;
    private long paymentId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime clientPointUsageHistoryDatetime;
    private Integer clientPointUsageAmount;


}
