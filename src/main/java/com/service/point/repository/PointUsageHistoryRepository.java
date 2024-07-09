package com.service.point.repository;

import com.service.point.domain.entity.PointUsageHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointUsageHistoryRepository extends JpaRepository<PointUsageHistory, Long> {
}
