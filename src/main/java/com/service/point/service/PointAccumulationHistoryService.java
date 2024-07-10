package com.service.point.service;

import com.service.point.dto.request.PointRewardOrderRequestDto;
import com.service.point.dto.request.PointRewardRefundRequestDto;
import com.service.point.dto.response.PointAccumulationAdminPageResponseDto;
import com.service.point.dto.response.PointAccumulationMyPageResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;

public interface PointAccumulationHistoryService {
    void orderPoint(HttpHeaders headers,
        PointRewardOrderRequestDto pointPolicyOrderResponseDto);

    void reviewPoint(HttpHeaders headers);

    void memberShipPoint(String message);

    void refundPoint(HttpHeaders headers,
        PointRewardRefundRequestDto pointRewardRefundRequestDto);

    Page<PointAccumulationMyPageResponseDto> rewardClientPoint(HttpHeaders headers, int page,
        int size);

    Page<PointAccumulationAdminPageResponseDto> rewardUserPoint(int page, int size);
}
