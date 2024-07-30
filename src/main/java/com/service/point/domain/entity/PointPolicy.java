package com.service.point.domain.entity;

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
    private String pointAccumulationType;
    @Setter
    private Long pointValue;
    private LocalDate pointPolicyCreationDate;

    @Setter
    private PointStatus pointStatus;

    public PointPolicy(String pointAccumulationType, Long pointValue) {
        this.pointPolicyCreationDate=LocalDate.now();
        this.pointStatus=PointStatus.DISABLED;
        this.pointValue = pointValue;
        this.pointAccumulationType = pointAccumulationType;
    }
}
