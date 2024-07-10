package com.service.point.service;

import com.service.point.dto.request.PointUsagePaymentRequestDto;
import com.service.point.dto.request.PointUsageRefundRequestDto;
import com.service.point.dto.response.PointUsageAdminPageResponseDto;
import com.service.point.dto.response.PointUsageMyPageResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;

public interface PointUsageHistoryService {
    void usedPaymentPoint(PointUsagePaymentRequestDto pointUsagePaymentRequestDto, HttpHeaders headers);

    void usedRefundPoint (PointUsageRefundRequestDto pointUsageRefundRequestDto, HttpHeaders headers);

    Page<PointUsageMyPageResponseDto> useClientPoint(HttpHeaders headers, int page, int size);

    Page<PointUsageAdminPageResponseDto> useUserPoint(int page, int size);
}
