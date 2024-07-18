package com.service.point.service.impl;

import com.service.point.domain.PointUsageKind;
import com.service.point.domain.entity.PointUsageHistory;
import com.service.point.domain.entity.PointUsageType;
import com.service.point.dto.request.PointUsagePaymentRequestDto;
import com.service.point.dto.request.PointUsageRefundRequestDto;
import com.service.point.dto.response.PointUsageAdminPageResponseDto;
import com.service.point.dto.response.PointUsageMyPageResponseDto;
import com.service.point.exception.ClientNotFoundException;
import com.service.point.exception.PointTypeNotFoundException;
import com.service.point.repository.PointUsageHistoryRepository;
import com.service.point.repository.PointUsageTypeRepository;
import com.service.point.service.PointUsageHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.NumberUtils;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PointUsageHistoryServiceImpl implements PointUsageHistoryService {

    private final PointUsageTypeRepository pointUsageTypeRepository;
    private final PointUsageHistoryRepository pointUsageHistoryRepository;
    private static final String ID_HEADER = "X-User-Id";



    @Override
    @RabbitListener(queues = "${rabbit.use.point.queue.name}")
    public void usedPaymentPoint(PointUsagePaymentRequestDto pointUsagePaymentRequestDto){
        log.info("{}", pointUsagePaymentRequestDto.getPointUsageAmount());
        PointUsageType pointUsageType = pointUsageTypeRepository.findByPointUsageKind(PointUsageKind.PAYMENT);
        PointUsageHistory pointUsageHistory = new PointUsageHistory(pointUsagePaymentRequestDto.getPointUsageAmount(), pointUsageType, pointUsagePaymentRequestDto.getClientId());
        pointUsageHistoryRepository.save(pointUsageHistory);
    }

    @Override
    public void usedRefundPoint (PointUsageRefundRequestDto pointUsageRefundRequestDto, HttpHeaders headers){
        if (headers.getFirst(ID_HEADER) == null) {
            throw new ClientNotFoundException("유저가 존재하지 않습니다.");
        }
        long clientId = NumberUtils.toLong(headers.getFirst(ID_HEADER));
        PointUsageType pointUsageType = pointUsageTypeRepository.findByPointUsageKind(PointUsageKind.REFUND);
        PointUsageHistory pointUsageHistory = new PointUsageHistory(pointUsageRefundRequestDto.getPointUsagePayment(), pointUsageType, clientId);
        pointUsageHistoryRepository.save(pointUsageHistory);
    }

    @Override
    public Page<PointUsageMyPageResponseDto> useClientPoint(HttpHeaders headers, int page, int size){
        if (headers.getFirst(ID_HEADER) == null) {
            throw new ClientNotFoundException("유저가 존재하지 않습니다.");
        }
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Direction.DESC, "pointUsageHistoryDate"));
        long clientId = NumberUtils.toLong(headers.getFirst(ID_HEADER));
        Page<PointUsageHistory> pointUsageHistories = pointUsageHistoryRepository.findByClientId(clientId, pageRequest);
        return pointUsageHistories.map(points -> {
            PointUsageMyPageResponseDto pointUsageMyPageResponseDto = new PointUsageMyPageResponseDto();
            PointUsageType pointUsageType = pointUsageTypeRepository.findById(points.getPointUsageType().getPointUsageTypeId()).orElseThrow(()-> new PointTypeNotFoundException("포인트 타입을 찾을수 없습니다."));
            pointUsageMyPageResponseDto.setPointUsageHistoryDate(String.valueOf(points.getPointUsageHistoryDate()));
            pointUsageMyPageResponseDto.setPointUsageAmount(points.getPointUsageAmount());
            pointUsageMyPageResponseDto.setPointUsageType(pointUsageType.getPointUsageKind().getValue());
            return pointUsageMyPageResponseDto;
        });
    }

    @Override
    public Page<PointUsageAdminPageResponseDto> useUserPoint(int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Direction.DESC, "pointUsageHistoryDate"));
        Page<PointUsageHistory> pointUsageHistories = pointUsageHistoryRepository.findAll(pageRequest);
        return pointUsageHistories.map(points -> {
            PointUsageAdminPageResponseDto pointUsageAdminPageResponseDto = new PointUsageAdminPageResponseDto();
            PointUsageType pointUsageType = pointUsageTypeRepository.findById(points.getPointUsageType().getPointUsageTypeId()).orElseThrow(()-> new PointTypeNotFoundException("포인트 타입을 찾을수 없습니다."));
            pointUsageAdminPageResponseDto.setPointUsageHistoryDate(String.valueOf(points.getPointUsageHistoryDate()));
            pointUsageAdminPageResponseDto.setClientId(points.getClientId());
            pointUsageAdminPageResponseDto.setPointUsageAmount(points.getPointUsageAmount());
            pointUsageAdminPageResponseDto.setPointUsageType(pointUsageType.getPointUsageKind().getValue());
            return pointUsageAdminPageResponseDto;
        });
    }
}
