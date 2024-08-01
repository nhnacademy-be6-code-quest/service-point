package com.service.point.domain.entity;

import com.service.point.domain.PointUsageKind;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PointUsageType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pointUsageTypeId;
    @Column(name = "pointUsageType")
    private PointUsageKind pointUsageKind;
}
