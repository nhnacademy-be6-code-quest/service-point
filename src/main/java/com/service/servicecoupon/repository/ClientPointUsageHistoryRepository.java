package com.service.servicecoupon.repository;

import com.service.servicecoupon.domain.entity.ClientPointUsageHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientPointUsageHistoryRepository extends JpaRepository<ClientPointUsageHistory, Long> {
}
