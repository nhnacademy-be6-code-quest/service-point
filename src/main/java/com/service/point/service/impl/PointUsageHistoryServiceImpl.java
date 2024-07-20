package com.service.point.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.point.domain.PointUsageKind;
import com.service.point.domain.entity.PointUsageHistory;
import com.service.point.domain.entity.PointUsageType;
import com.service.point.dto.message.PointUsageRefundMessageDto;
import com.service.point.dto.request.PointUsagePaymentRequestDto;
import com.service.point.dto.message.PointUsagePaymentMessageDto;
import com.service.point.dto.response.PointUsageAdminPageResponseDto;
import com.service.point.dto.response.PointUsageMyPageResponseDto;
import com.service.point.exception.ClientNotFoundException;
import com.service.point.exception.PointTypeNotFoundException;
import com.service.point.exception.RabbitMessageConvertException;
import com.service.point.repository.PointUsageHistoryRepository;
import com.service.point.repository.PointUsageTypeRepository;
import com.service.point.service.PointUsageHistoryService;
import java.io.IOException;
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
    private final ObjectMapper objectMapper;
    private static final String ID_HEADER = "X-User-Id";



    @Override
    @RabbitListener(queues = "${rabbit.use.point.queue.name}")
    public void usedPaymentPoint(String message){
        PointUsagePaymentMessageDto pointUsagePaymentRequestDto;
        try{
            pointUsagePaymentRequestDto = objectMapper.readValue(message, PointUsagePaymentMessageDto.class);
        } catch (IOException e){
            throw new RabbitMessageConvertException("사용 포인트 메세지 변환에 실패했습니다.");
        }
        PointUsageType pointUsageType = pointUsageTypeRepository.findByPointUsageKind(PointUsageKind.PAYMENT);
        if (pointUsagePaymentRequestDto.getPointUsagePayment()!=0) {
            PointUsageHistory pointUsageHistory = new PointUsageHistory(
                pointUsagePaymentRequestDto.getPointUsagePayment(), pointUsageType,
                pointUsagePaymentRequestDto.getClientId());
            pointUsageHistoryRepository.save(pointUsageHistory);
        }
    }

    @RabbitListener(queues = "${rabbit.usedRefund.point.queue.name}")
    public void usedRefundPoint (String message){
        PointUsageRefundMessageDto pointUsagePaymentMessageDto;
        try{ pointUsagePaymentMessageDto = objectMapper.readValue(message, PointUsageRefundMessageDto.class);

        }catch (IOException e){
            throw new RabbitMessageConvertException("적립 포인트 메세지 변환에 실패하였습니다.");
        }

        PointUsageType pointUsageType = pointUsageTypeRepository.findByPointUsageKind(PointUsageKind.REFUND);
        PointUsageHistory pointUsageHistory = new PointUsageHistory(pointUsagePaymentMessageDto.getPointUsagePayment(), pointUsageType, pointUsagePaymentMessageDto.getClientId());
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
