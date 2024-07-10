package com.service.point.service.impl;

import com.service.point.domain.PointUsageKind;
import com.service.point.domain.entity.PointUsageHistory;
import com.service.point.domain.entity.PointUsageType;
import com.service.point.dto.request.PointUsagePaymentRequestDto;
import com.service.point.dto.request.PointUsageRefundRequestDto;
import com.service.point.exception.ClientNotFoundException;
import com.service.point.repository.PointUsageHistoryRepository;
import com.service.point.repository.PointUsageTypeRepository;
import org.apache.commons.lang.math.NumberUtils;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointUsageHistoryServiceImpl {

    private final PointUsageTypeRepository pointUsageTypeRepository;
    private final PointUsageHistoryRepository pointUsageHistoryRepository;
    private static final String ID_HEADER = "X-User-Id";

    public void usedPaymentPoint(PointUsagePaymentRequestDto pointUsagePaymentRequestDto, HttpHeaders headers){
        if (headers.getFirst(ID_HEADER) == null) {
            throw new ClientNotFoundException("유저가 존재하지 않습니다.");
        }
        long clientId = NumberUtils.toLong(headers.getFirst(ID_HEADER));
        PointUsageType pointUsageType = pointUsageTypeRepository.findByPointUsageKind(PointUsageKind.PAYMENT);
        PointUsageHistory pointUsageHistory = new PointUsageHistory(pointUsagePaymentRequestDto.getPointUsagePayment(), pointUsageType, clientId);
        pointUsageHistoryRepository.save(pointUsageHistory);
    }
    //주문 amount 를 받아서 바로 박고


    public void usedRefundPoint (PointUsageRefundRequestDto pointUsageRefundRequestDto, HttpHeaders headers){
        if (headers.getFirst(ID_HEADER) == null) {
            throw new ClientNotFoundException("유저가 존재하지 않습니다.");
        }
        long clientId = NumberUtils.toLong(headers.getFirst(ID_HEADER));
        PointUsageType pointUsageType = pointUsageTypeRepository.findByPointUsageKind(PointUsageKind.REFUND);
        PointUsageHistory pointUsageHistory = new PointUsageHistory(pointUsageRefundRequestDto.getPointUsagePayment(), pointUsageType, clientId);
        pointUsageHistoryRepository.save(pointUsageHistory);
    }
    //환불은 적립내역 받아서 사용 처리
}
