package com.service.point.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.point.config.MemberShipMessageDto;
import com.service.point.domain.PointPolicyType;
import com.service.point.domain.entity.PointAccumulationHistory;
import com.service.point.domain.entity.PointPolicy;
import com.service.point.dto.request.PointRewardOrderRequestDto;
import com.service.point.dto.request.PointRewardRefundRequestDto;
import com.service.point.exception.ClientNotFoundException;
import com.service.point.exception.PointPolicyNotFoundException;
import com.service.point.exception.RabbitMessageConvertException;
import com.service.point.repository.PointAccumulationHistoryRepository;
import com.service.point.repository.PointPolicyRepository;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class PointAccumulationHistoryServiceImpl {

    private final PointAccumulationHistoryRepository pointAccumulationHistoryRepository;
    private static final String ID_HEADER = "X-User-Id";
    private final PointPolicyRepository pointPolicyRepository;
    private final ObjectMapper objectMapper;

    public void orderPoint(HttpHeaders headers,
        PointRewardOrderRequestDto pointPolicyOrderResponseDto) {
        if (headers.getFirst(ID_HEADER) == null) {
            throw new ClientNotFoundException("유저가 존재하지 않습니다.");
        }

        PointPolicy pointPolicy = pointPolicyRepository.findByPointPolicyTypeAndPointPolicyExpirationDateIsNull(
            PointPolicyType.PAYMENT);
        if (pointPolicy == null) {
            throw new PointPolicyNotFoundException("포인트 정책이 존재하지않습니다.");
        }
        long clientId = NumberUtils.toLong(headers.getFirst(ID_HEADER));
        PointAccumulationHistory pointAccumulationHistory = new PointAccumulationHistory(
            pointPolicy, clientId, pointPolicyOrderResponseDto.getAccumulatedPoint());
        pointAccumulationHistoryRepository.save(pointAccumulationHistory);
    }

    public void reviewPoint(HttpHeaders headers) {
        if (headers.getFirst(ID_HEADER) == null) {
            throw new ClientNotFoundException("유저가 존재하지 않습니다.");
        }
        PointPolicy pointPolicy = pointPolicyRepository.findByPointPolicyTypeAndPointPolicyExpirationDateIsNull(
            PointPolicyType.REVIEW);
        if (pointPolicy == null) {
            throw new PointPolicyNotFoundException("포인트 정책이 존재하지 않습니다.");
        }
        long clientId = NumberUtils.toLong(headers.getFirst(ID_HEADER));
        PointAccumulationHistory pointAccumulationHistory = new PointAccumulationHistory(
            pointPolicy, clientId, pointPolicy.getPointValue());
        pointAccumulationHistoryRepository.save(pointAccumulationHistory);
    }

    @RabbitListener(queues = "${rabbit.login.queue.name}")
    public void memberShipPoint(String message) {
        MemberShipMessageDto memberShipMessageDto;
        log.error("{}",message);
        try {
            memberShipMessageDto = objectMapper.readValue(message, MemberShipMessageDto.class);
        } catch (IOException e) {
            throw new RabbitMessageConvertException("회원가입 유저 메세지 변환에 실패했습니다.");
        }
        PointPolicy pointPolicy = pointPolicyRepository.findByPointPolicyTypeAndPointPolicyExpirationDateIsNull(
            PointPolicyType.MEMBERSHIP);

        if (pointPolicy == null) {
            throw new PointPolicyNotFoundException("포인트 정책을 찾을수 없습니다.");
        }
        PointAccumulationHistory pointAccumulationHistory = new PointAccumulationHistory(
            pointPolicy,
            memberShipMessageDto.getClientId(), pointPolicy.getPointValue());
        pointAccumulationHistoryRepository.save(pointAccumulationHistory);
    }

    public void refundPoint(HttpHeaders headers, PointRewardRefundRequestDto pointRewardRefundRequestDto) {
        if (headers.getFirst(ID_HEADER) == null) {
            throw new ClientNotFoundException("유저가 존재하지 않습니다.");
        }
        PointPolicy pointPolicy = pointPolicyRepository.findByPointPolicyTypeAndPointPolicyExpirationDateIsNull(
            PointPolicyType.REFUND);
        if (pointPolicy == null) {
            throw new PointPolicyNotFoundException("포인트 정책이 존재하지 않습니다.");
        }
        long clientId = NumberUtils.toLong(headers.getFirst(ID_HEADER));
        PointAccumulationHistory pointAccumulationHistory = new PointAccumulationHistory(
            pointPolicy, clientId, pointRewardRefundRequestDto.getAccumulatedPoint());
        pointAccumulationHistoryRepository.save(pointAccumulationHistory);
    }
}
