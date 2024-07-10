package com.service.point.repository;

import com.service.point.domain.entity.PointUsageHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PointUsageHistoryRepository extends JpaRepository<PointUsageHistory, Long> {
    @Query("SELECT SUM(p.pointUsageAmount) FROM PointUsageHistory p WHERE p.clientId = :clientId")
    Integer findTotalPointsByClientId(@Param("clientId") long clientId);

    Page<PointUsageHistory> findByClientId(long clientId, Pageable pageable);
}
