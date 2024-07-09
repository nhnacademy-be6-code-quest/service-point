package com.service.point.repository;

import com.service.point.domain.entity.ClientPointAccumulationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointAccumulationHistoryRepository extends JpaRepository<ClientPointAccumulationHistory, Long> {
}
