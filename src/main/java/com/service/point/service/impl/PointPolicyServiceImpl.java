package com.service.point.service.impl;


import com.service.point.domain.entity.PointPolicy;
import com.service.point.domain.request.PointPolicyRegisterRequestDto;
import com.service.point.repository.PointPolicyRepository;
import com.service.point.service.PointPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

    @Service
    public class PointPolicyServiceImpl implements PointPolicyService {

        @Autowired
        private PointPolicyRepository pointPolicyRepository;

        public void addPointPolicy(PointPolicyRegisterRequestDto pointPolicyRegisterRequestDto) {
            // 현재 유효한 정책을 조회
            PointPolicy currentPolicy = pointPolicyRepository.findByPointPolicyNameAndPointPolicyExpirationDateIsNull(pointPolicyRegisterRequestDto.getPointPolicyName());
            if (currentPolicy != null) {
                // 기존 정책의 종료일을 오늘로 설정
                currentPolicy.setPointPolicyExpirationDate(LocalDateTime.now());
                pointPolicyRepository.save(currentPolicy);
            }

            // 새로운 정책의 시작일을 내일로 설정
            PointPolicy pointPolicy =new PointPolicy(pointPolicyRegisterRequestDto.getPointPolicyName(),pointPolicyRegisterRequestDto.getPointPolicyAccumulationRate(),pointPolicyRegisterRequestDto.getPointPolicyAccumulationAmount());
            pointPolicyRepository.save(pointPolicy);
        }

        public List<PointPolicy> getAllPointPolicies() {
            return pointPolicyRepository.findAllByPointPolicyExpirationDateIsNull();
        }

//        public PointPolicy getPointPolicyById(long id) {
//            return pointPolicyRepository.findById(id)
//                    .orElseThrow(() -> new RuntimeException("PointPolicy not found"));
//        }

        public void deletePointPolicy(long pointPolicyId){
            pointPolicyRepository.deleteById(pointPolicyId);
        }

    }

