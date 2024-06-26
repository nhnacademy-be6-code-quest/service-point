package com.service.servicecoupon.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PointType {
    @Id
    private long pointTypeId;

    
    private long clientId;
    private long paymentId;
    private long noPhotoReview;
    private long photoReview;
}
