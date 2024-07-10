package com.service.point.service;

import com.service.point.dto.request.PointPolicyRegisterRequestDto;
import com.service.point.dto.response.PointPolicyAdminListResponseDto;
import org.springframework.data.domain.Page;

public interface PointPolicyService {
    void savePointPolicy(PointPolicyRegisterRequestDto pointPolicyRegisterRequestDto);

    Page<PointPolicyAdminListResponseDto> getAllPointPolicies(int page, int size);

    void deletePointPolicy(long pointPolicyId);
}
