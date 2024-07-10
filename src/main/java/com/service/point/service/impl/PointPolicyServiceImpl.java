package com.service.point.service.impl;


import com.service.point.domain.PointStatus;
import com.service.point.domain.entity.PointPolicy;
import com.service.point.dto.request.PointPolicyRegisterRequestDto;
import com.service.point.dto.response.PointPolicyAdminListResponseDto;
import com.service.point.exception.PointPolicyNotFoundException;
import com.service.point.repository.PointPolicyRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

    @Service
    @RequiredArgsConstructor
    public class PointPolicyServiceImpl  {

        private final PointPolicyRepository pointPolicyRepository;

        public void savePointPolicy(PointPolicyRegisterRequestDto pointPolicyRegisterRequestDto) {
            // 현재 유효한 정책을 조회
            PointPolicy currentPolicy = pointPolicyRepository.findByPointPolicyTypeAndPointPolicyExpirationDateIsNull(pointPolicyRegisterRequestDto.getPointPolicyType());
            if (currentPolicy != null) {
                // 기존 정책의 종료일을 오늘로 설정
                currentPolicy.setPointPolicyExpirationDate(LocalDate.now());
                currentPolicy.setPointStatus(PointStatus.DISABLED);
                pointPolicyRepository.save(currentPolicy);
            }

            // 새로운 정책의 시작일을 내일로 설정
            PointPolicy pointPolicy =new PointPolicy(pointPolicyRegisterRequestDto.getPointPolicyType(),pointPolicyRegisterRequestDto.getPointValue());
            pointPolicyRepository.save(pointPolicy);
        }

        public Page<PointPolicyAdminListResponseDto> getAllPointPolicies(int page, int size) {
            PageRequest pageRequest = PageRequest.of(page, size, Sort.by("pointPolicyType").descending());
            Page<PointPolicy> pointPolicies = pointPolicyRepository.findAllByPointPolicyExpirationDateIsNull(pageRequest);
            return pointPolicies.map(pointPolicy -> {
                PointPolicyAdminListResponseDto dto = new PointPolicyAdminListResponseDto();
                dto.setPointPolicyType(pointPolicy.getPointPolicyType().getValue());
                dto.setPointValue(pointPolicy.getPointValue());
                dto.setPointPolicyCreationDate(String.valueOf(pointPolicy.getPointPolicyCreationDate()));
                return dto;
            });
        }



        public void deletePointPolicy(long pointPolicyId){
            PointPolicy pointPolicy = pointPolicyRepository.findById(pointPolicyId).orElseThrow(()->
                new PointPolicyNotFoundException("포인트 정책을 찾을수 없습니다."));
            pointPolicy.setPointStatus(PointStatus.DISABLED);
            pointPolicy.setPointPolicyExpirationDate(LocalDate.now());
            pointPolicyRepository.save(pointPolicy);
        }

    }

