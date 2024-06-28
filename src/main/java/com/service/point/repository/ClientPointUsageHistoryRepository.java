package com.service.point.repository;

import com.service.point.domain.entity.ClientPointUsageHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientPointUsageHistoryRepository extends JpaRepository<ClientPointUsageHistory, Long> {
}
