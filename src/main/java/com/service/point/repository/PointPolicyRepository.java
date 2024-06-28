package com.service.point.repository;

import com.service.point.domain.entity.PointPolicy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointPolicyRepository extends JpaRepository<PointPolicy, Long> {
    PointPolicy findByPointPolicyNameAndPointPolicyExpirationDateIsNull(String pointPolicyName);
    List<PointPolicy> findAllByPointPolicyExpirationDateIsNull();
}
