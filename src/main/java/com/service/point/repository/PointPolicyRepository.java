package com.service.point.repository;

import com.service.point.domain.PointPolicyType;
import com.service.point.domain.entity.PointPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointPolicyRepository extends JpaRepository<PointPolicy, Long> {
    PointPolicy findByPointPolicyTypeAndPointPolicyExpirationDateIsNull(
        PointPolicyType pointPolicyType);
    Page<PointPolicy> findAllByPointPolicyExpirationDateIsNull(Pageable pageable);
}
