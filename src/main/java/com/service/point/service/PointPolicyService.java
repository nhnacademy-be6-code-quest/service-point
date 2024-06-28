package com.service.point.service;

import com.service.point.domain.entity.PointPolicy;
import com.service.point.domain.request.PointPolicyRegisterRequestDto;

import java.time.LocalDateTime;
import java.util.List;

public interface PointPolicyService {

    void addPointPolicy(PointPolicyRegisterRequestDto pointPolicyRegisterRequestDto);

    List<PointPolicy> getAllPointPolicies();

    void deletePointPolicy(long pointPolicyId);
}