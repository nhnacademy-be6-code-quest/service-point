package com.service.point.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.point.client.UserNameClient;
import com.service.point.client.UserRankClient;
import com.service.point.config.MemberShipMessageDto;
import com.service.point.config.ReviewMessageDto;
import com.service.point.domain.PointStatus;
import com.service.point.domain.entity.PointAccumulationHistory;
import com.service.point.domain.entity.PointPolicy;
import com.service.point.dto.message.PointRewardRefundMessageDto;
import com.service.point.dto.request.PointRewardOrderRequestDto;
import com.service.point.dto.response.ClientGradeRateResponseDto;
import com.service.point.dto.response.ClientNameResponseDto;
import com.service.point.dto.response.PointAccumulationAdminPageResponseDto;
import com.service.point.dto.response.PointAccumulationMyPageResponseDto;
import com.service.point.exception.ClientNotFoundException;
import com.service.point.exception.RabbitMessageConvertException;
import com.service.point.repository.PointAccumulationHistoryRepository;
import com.service.point.repository.PointPolicyRepository;
import com.service.point.service.impl.PointAccumulationHistoryServiceImpl;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

class PointAccumulationHistoryServiceImplTest {

    @Mock
    private UserNameClient userNameClient;

    @Mock
    private PointAccumulationHistoryRepository pointAccumulationHistoryRepository;

    @Mock
    private PointPolicyRepository pointPolicyRepository;

    @Mock
    private UserRankClient userRankClient;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private PointAccumulationHistoryServiceImpl pointAccumulationHistoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testOrderPoint() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-User-Id", "1");
        PointRewardOrderRequestDto requestDto = new PointRewardOrderRequestDto();
        requestDto.setAccumulatedPoint(100L);

        when(userRankClient.getClientGradeRate(anyLong()))
            .thenReturn(ResponseEntity.ok(new ClientGradeRateResponseDto(1L)));
        PointPolicy pointPolicy = new PointPolicy();
        pointPolicy.setPointValue(10L);
        when(pointPolicyRepository.findById(anyLong())).thenReturn(Optional.of(pointPolicy));

        pointAccumulationHistoryService.orderPoint(headers, requestDto);

        verify(pointAccumulationHistoryRepository, times(1)).save(any());
    }

    @Test
    void testOrderPoint_ClientNotFound() {
        HttpHeaders headers = new HttpHeaders();
        PointRewardOrderRequestDto requestDto = new PointRewardOrderRequestDto();

        assertThrows(ClientNotFoundException.class, () -> pointAccumulationHistoryService.orderPoint(headers, requestDto));
    }

    @Test
    void testReviewPoint() throws IOException {
        ReviewMessageDto reviewMessageDto = new ReviewMessageDto();
        reviewMessageDto.setHasImage(true);
        reviewMessageDto.setClientId(1L);
        String message = "testMessage";

        when(objectMapper.readValue(message, ReviewMessageDto.class)).thenReturn(reviewMessageDto);

        PointPolicy pointPolicy = new PointPolicy();
        pointPolicy.setPointValue(10L);
        when(pointPolicyRepository.findByPointAccumulationTypeEqualsAndPointStatus(anyString(), any(PointStatus.class)))
            .thenReturn(pointPolicy);

        pointAccumulationHistoryService.reviewPoint(message);

        verify(pointAccumulationHistoryRepository, times(1)).save(any(PointAccumulationHistory.class));
    }

    @Test
    void testReviewPoint_MessageConversionException() throws IOException {
        String message = "invalidMessage";
        when(objectMapper.readValue(message, ReviewMessageDto.class)).thenThrow(RabbitMessageConvertException.class);

        assertThrows(RabbitMessageConvertException.class, () -> pointAccumulationHistoryService.reviewPoint(message));
    }

    @Test
    void testMemberShipPoint() throws IOException {
        MemberShipMessageDto memberShipMessageDto = new MemberShipMessageDto();
        memberShipMessageDto.setClientId(1L);
        String message = "testMessage";

        when(objectMapper.readValue(message, MemberShipMessageDto.class)).thenReturn(memberShipMessageDto);

        PointPolicy pointPolicy = new PointPolicy();
        pointPolicy.setPointValue(10L);
        when(pointPolicyRepository.findByPointAccumulationTypeEqualsAndPointStatus(anyString(), any(PointStatus.class)))
            .thenReturn(pointPolicy);

        pointAccumulationHistoryService.memberShipPoint(message);

        verify(pointAccumulationHistoryRepository, times(1)).save(any(PointAccumulationHistory.class));
    }

    @Test
    void testRefundPoint() throws IOException {
        PointRewardRefundMessageDto refundMessageDto = new PointRewardRefundMessageDto();
        refundMessageDto.setClientId(1L);
        refundMessageDto.setPayment(100L);
        refundMessageDto.setDiscountAmountByPoint(10L);
        String message = "testMessage";

        when(objectMapper.readValue(message, PointRewardRefundMessageDto.class)).thenReturn(refundMessageDto);

        PointPolicy pointPolicy = new PointPolicy();
        pointPolicy.setPointValue(10L);
        when(pointPolicyRepository.findByPointAccumulationTypeEqualsAndPointStatus(anyString(), any(PointStatus.class)))
            .thenReturn(pointPolicy);

        pointAccumulationHistoryService.refundPoint(message);

        verify(pointAccumulationHistoryRepository, times(2)).save(any(PointAccumulationHistory.class));
    }

    @Test
    void testRefundPoint_MessageConversionException() throws IOException {
        String message = "invalidMessage";
        when(objectMapper.readValue(message, PointRewardRefundMessageDto.class)).thenThrow(RabbitMessageConvertException.class);

        assertThrows(RabbitMessageConvertException.class, () -> pointAccumulationHistoryService.refundPoint(message));
    }


    @Test
    void testRewardClientPoint() {
        // Setup
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-User-Id", "1");

        PointPolicy pointPolicy = new PointPolicy("포인트", 100L);
        when(pointPolicyRepository.findById(anyLong())).thenReturn(Optional.of(pointPolicy));

        PointAccumulationHistory pointAccumulationHistory = new PointAccumulationHistory(pointPolicy, 1L, 10L);
        Page<PointAccumulationHistory> page = new PageImpl<>(Collections.singletonList(pointAccumulationHistory));

        when(pointAccumulationHistoryRepository.findByClientId(anyLong(), any(PageRequest.class))).thenReturn(page);

        // Act
        Page<PointAccumulationMyPageResponseDto> result = pointAccumulationHistoryService.rewardClientPoint(headers, 0, 10);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getContent().size());
        PointAccumulationMyPageResponseDto dto = result.getContent().get(0);
        assertEquals(10L, dto.getPointAccumulationAmount());  // Adjust according to your DTO's fields
        verify(pointAccumulationHistoryRepository, times(1)).findByClientId(anyLong(), any(PageRequest.class));
    }

    @Test
    void testRewardUserPoint() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-User-Id", "1");

        PointAccumulationHistory history = new PointAccumulationHistory(new PointPolicy(),1L,100L);


        Page<PointAccumulationHistory> histories = new PageImpl<>(Collections.singletonList(history));
        when(pointAccumulationHistoryRepository.findAll(any(PageRequest.class))).thenReturn(histories);

        PointPolicy pointPolicy = new PointPolicy("포인트",100L);

        when(pointPolicyRepository.findById(anyLong())).thenReturn(Optional.of(pointPolicy));

        ClientNameResponseDto clientNameResponseDto = new ClientNameResponseDto();
        clientNameResponseDto.setClientName("TestClient");
        when(userNameClient.getClientName(any(HttpHeaders.class), anyLong())).thenReturn(ResponseEntity.ok(clientNameResponseDto));

        Page<PointAccumulationAdminPageResponseDto> result = pointAccumulationHistoryService.rewardUserPoint(headers, 0, 10);

        assertFalse(result.isEmpty());
        assertEquals("포인트", result.getContent().get(0).getPointAccumulationType());
        assertEquals("TestClient", result.getContent().get(0).getClientName());
    }
    @Test
    void testDeletePoint() {
        pointAccumulationHistoryService.deletePoint(1L);

        verify(pointPolicyRepository, times(1)).deleteById(1L);
    }
}
