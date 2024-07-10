package com.service.point.repository;

import com.service.point.domain.entity.PointAccumulationHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PointAccumulationHistoryRepository extends JpaRepository<PointAccumulationHistory, Long> {
    @Query("SELECT SUM(p.pointAccumulationAmount) FROM PointAccumulationHistory p WHERE p.clientId = :clientId")
    Integer findTotalPointsByClientId(@Param("clientId") long clientId);

    Page<PointAccumulationHistory> findByClientId(long clientId, Pageable pageable);


}
