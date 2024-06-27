package com.service.servicecoupon.service.impl;



import com.service.servicecoupon.domain.entity.PointPolicy;
import com.service.servicecoupon.repository.PointPolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

    @Service
    public class PointPolicyServiceImpl {

        @Autowired
        private PointPolicyRepository pointPolicyRepository;

        public PointPolicy addPointPolicy(PointPolicy pointPolicy) {
            // 현재 유효한 정책을 조회
            Optional<PointPolicy> currentPolicy = pointPolicyRepository.findByEndDateIsNull();
            if (currentPolicy.isPresent()) {
                PointPolicy existingPolicy = currentPolicy.get();
                // 기존 정책의 종료일을 오늘로 설정
                existingPolicy.setEndDate(LocalDate.now());
                pointPolicyRepository.save(existingPolicy);
            }

            // 새로운 정책의 시작일을 내일로 설정
            pointPolicy.setStartDate(LocalDate.now().plusDays(1));
            pointPolicy.setEndDate(null); // 종료일은 null로 설정 (현재 유효한 정책)
            return pointPolicyRepository.save(pointPolicy);
        }

        public List<PointPolicy> getAllPointPolicies() {
            return pointPolicyRepository.findAll();
        }

        public PointPolicy getPointPolicyById(long id) {
            return pointPolicyRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("PointPolicy not found"));
        }
    }

}
