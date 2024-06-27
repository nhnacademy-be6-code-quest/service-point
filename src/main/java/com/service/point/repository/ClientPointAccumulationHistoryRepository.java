package com.service.servicecoupon.repository;

import com.service.servicecoupon.domain.entity.ClientPointAccumulationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientPointAccumulationHistoryRepository extends JpaRepository<ClientPointAccumulationHistory, Long> {
}
