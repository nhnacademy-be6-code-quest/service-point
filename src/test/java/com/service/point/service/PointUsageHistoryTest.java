package com.service.point.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.point.domain.PointUsageKind;
import com.service.point.domain.entity.PointUsageHistory;
import com.service.point.domain.entity.PointUsageType;
import com.service.point.dto.message.PointUsagePaymentMessageDto;
import com.service.point.dto.message.PointUsageRefundMessageDto;
import com.service.point.dto.response.PointUsageMyPageResponseDto;
import com.service.point.exception.ClientNotFoundException;
import com.service.point.exception.PointTypeNotFoundException;
import com.service.point.exception.RabbitMessageConvertException;
import com.service.point.repository.PointUsageHistoryRepository;
import com.service.point.repository.PointUsageTypeRepository;
import com.service.point.service.impl.PointUsageHistoryServiceImpl;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;

class PointUsageHistoryTest {
    @Mock
    private PointUsageTypeRepository pointUsageTypeRepository;

    @Mock
    private PointUsageHistoryRepository pointUsageHistoryRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private PointUsageHistoryServiceImpl pointUsageHistoryService;

    public PointUsageHistoryTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUsedPaymentPoint() throws IOException {
        // Given
        String message = "{ \"pointUsagePayment\": 100, \"clientId\": \"123\" }";
        PointUsagePaymentMessageDto pointUsagePaymentRequestDto = new PointUsagePaymentMessageDto();
        pointUsagePaymentRequestDto.setPointUsagePayment(100L);
        pointUsagePaymentRequestDto.setClientId(123L);

        PointUsageType pointUsageType = new PointUsageType(); // 필요한 값으로 설정하세요
        PointUsageHistory expectedPointUsageHistory = new PointUsageHistory(100L, pointUsageType, 123L );


        when(objectMapper.readValue(message, PointUsagePaymentMessageDto.class))
            .thenReturn(pointUsagePaymentRequestDto);
        when(pointUsageTypeRepository.findByPointUsageKind(PointUsageKind.PAYMENT))
            .thenReturn(pointUsageType);

        // When
        pointUsageHistoryService.usedPaymentPoint(message);

        // Then
        ArgumentCaptor<PointUsageHistory> captor = forClass(PointUsageHistory.class);
        verify(pointUsageHistoryRepository).save(captor.capture());

        PointUsageHistory capturedHistory = captor.getValue();

        // Compare properties
        assertEquals(expectedPointUsageHistory.getPointUsageAmount(), capturedHistory.getPointUsageAmount());
        assertEquals(expectedPointUsageHistory.getPointUsageType(), capturedHistory.getPointUsageType());
        assertEquals(expectedPointUsageHistory.getClientId(), capturedHistory.getClientId());
    }


    @Test
    void testUsedRefundPoint() throws IOException {
        // Given
        String message = "{ \"pointUsagePayment\": 200, \"clientId\": \"456\" }";
        PointUsageRefundMessageDto pointUsageRefundMessageDto = new PointUsageRefundMessageDto();
        pointUsageRefundMessageDto.setPointUsagePayment(200L);
        pointUsageRefundMessageDto.setClientId(456L);

        PointUsageType pointUsageType = new PointUsageType();
        // Set appropriate values if necessary

        when(objectMapper.readValue(message, PointUsageRefundMessageDto.class))
            .thenReturn(pointUsageRefundMessageDto);
        when(pointUsageTypeRepository.findByPointUsageKind(PointUsageKind.REFUND))
            .thenReturn(pointUsageType);

        // When
        pointUsageHistoryService.usedRefundPoint(message);

        // Then
        ArgumentCaptor<PointUsageHistory> captor = forClass(PointUsageHistory.class);
        verify(pointUsageHistoryRepository, times(1)).save(captor.capture());

        PointUsageHistory captured = captor.getValue();
        assertEquals(200L, captured.getPointUsageAmount());
        assertEquals(pointUsageType, captured.getPointUsageType());
        assertEquals(456L, captured.getClientId());
    }


    @Test
    void testUseClientPointSuccess() {
        // Given
        String clientId = "123";
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-User-Id", clientId);

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "pointUsageHistoryDate"));

        PointUsageKind pointUsageKind = PointUsageKind.REFUND; // Assuming you have a PointUsageKind enum or similar
        PointUsageType pointUsageType = new PointUsageType(1L, pointUsageKind);


        PointUsageHistory pointUsageHistory = new PointUsageHistory(100L, pointUsageType, Long.parseLong(clientId));

        Page<PointUsageHistory> page = new PageImpl<>(Collections.singletonList(pointUsageHistory), pageRequest, 1);

        when(pointUsageHistoryRepository.findByClientId(Long.parseLong(clientId), pageRequest))
            .thenReturn(page);
        when(pointUsageTypeRepository.findById(pointUsageType.getPointUsageTypeId()))
            .thenReturn(Optional.of(pointUsageType));

        // When
        Page<PointUsageMyPageResponseDto> result = pointUsageHistoryService.useClientPoint(headers, 0, 10);

        // Then
        verify(pointUsageHistoryRepository).findByClientId(Long.parseLong(clientId), pageRequest);
        verify(pointUsageTypeRepository).findById(pointUsageType.getPointUsageTypeId());
        assertEquals(1, result.getContent().size());
        assertEquals(100L, result.getContent().get(0).getPointUsageAmount());
    }

    @Test
    void testUseClientPointNoHeader() {
        // Given
        HttpHeaders headers = new HttpHeaders();

        // When & Then
        assertThrows(ClientNotFoundException.class, () -> pointUsageHistoryService.useClientPoint(headers, 0, 10));
    }

    @Test
    void testUseClientPointPointTypeNotFound() {
        // Given
        String clientId = "123";
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-User-Id", clientId);

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "pointUsageHistoryDate"));

        PointUsageKind pointUsageKind = PointUsageKind.REFUND; // Assuming you have a PointUsageKind enum or similar
        PointUsageType pointUsageType = new PointUsageType(1L, pointUsageKind);

        PointUsageHistory pointUsageHistory = new PointUsageHistory(100L, pointUsageType, 123L);


        Page<PointUsageHistory> page = new PageImpl<>(Collections.singletonList(pointUsageHistory), pageRequest, 1);

        when(pointUsageHistoryRepository.findByClientId(Long.parseLong(clientId), pageRequest))
            .thenReturn(page);
        when(pointUsageTypeRepository.findById(pointUsageType.getPointUsageTypeId()))
            .thenReturn(Optional.empty());

        // When & Then
        assertThrows(PointTypeNotFoundException.class, () -> {
            pointUsageHistoryService.useClientPoint(headers, 0, 10);
        });
    }
}
