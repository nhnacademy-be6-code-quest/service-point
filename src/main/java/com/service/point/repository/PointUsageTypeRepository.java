package com.service.point.repository;

import com.service.point.domain.PointUsageKind;
import com.service.point.domain.entity.PointUsageType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointUsageTypeRepository extends JpaRepository<PointUsageType,Long> {
    PointUsageType findByPointUsageKind(PointUsageKind pointUsageKind);
}
