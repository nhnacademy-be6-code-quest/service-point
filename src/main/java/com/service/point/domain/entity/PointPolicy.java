package com.service.point.domain.entity;

import com.service.point.domain.PointPolicyType;
import com.service.point.domain.PointStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class PointPolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pointPolicyId;
    private String pointPolicyType;
    @Setter
    private Integer pointValue;
    private LocalDate pointPolicyCreationDate;
    @Setter
    private LocalDate pointPolicyExpirationDate;
    @Setter
    private PointStatus pointStatus;

    public PointPolicy(String pointPolicyType, Integer pointValue) {
        this.pointPolicyCreationDate=LocalDate.now();
        this.pointStatus=PointStatus.DISABLED;
        this.pointValue = pointValue;
        this.pointPolicyType = pointPolicyType;
    }
}
