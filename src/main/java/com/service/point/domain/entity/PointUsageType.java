package com.service.point.domain.entity;

import com.service.point.domain.PointUsageKind;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PointUsageType {
    @Id
    private long pointUsageTypeId;
    @Column(name = "pointUsageType")
    private PointUsageKind pointUsageKind;
}
