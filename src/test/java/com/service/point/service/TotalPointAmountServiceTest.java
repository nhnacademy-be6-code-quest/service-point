package com.service.point.service;


import com.service.point.client.UserRankClient;
import com.service.point.domain.entity.PointPolicy;
import com.service.point.dto.response.ClientGradeRateResponseDto;
import com.service.point.dto.response.TotalPointAmountResponseDto;
import com.service.point.exception.ClientNotFoundException;
import com.service.point.repository.PointAccumulationHistoryRepository;
import com.service.point.repository.PointPolicyRepository;
import com.service.point.repository.PointUsageHistoryRepository;
import com.service.point.service.impl.TotalPointAmountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class TotalPointAmountServiceTest {

    @Mock
    private PointAccumulationHistoryRepository pointAccumulationHistoryRepository;

    @Mock
    private PointUsageHistoryRepository pointUsageHistoryRepository;

    @Mock
    private PointPolicyRepository pointPolicyRepository;

    @Mock
    private UserRankClient userRankClient;

    @InjectMocks
    private TotalPointAmountServiceImpl totalPointAmountService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindPointWithValidHeaders() {
        // Mock data
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-User-Id", "1");

        when(userRankClient.getClientGradeRate(anyLong())).thenReturn(ResponseEntity.ok(new ClientGradeRateResponseDto(1L)));
        when(pointUsageHistoryRepository.findTotalPointsByClientId(anyLong())).thenReturn(100L);
        when(pointAccumulationHistoryRepository.findTotalPointsByClientId(anyLong())).thenReturn(200L);
        when(pointPolicyRepository.findById(anyLong())).thenReturn(java.util.Optional.of(new PointPolicy("포인트", 10L)));

        // Execute the method
        TotalPointAmountResponseDto result = totalPointAmountService.findPoint(headers);

        // Verify and assert
        assertNotNull(result);
        assertEquals(100L, result.getTotalPoint());
        assertEquals(10L, result.getPointAccumulationRate());

        verify(userRankClient, times(1)).getClientGradeRate(anyLong());
        verify(pointUsageHistoryRepository, times(1)).findTotalPointsByClientId(anyLong());
        verify(pointAccumulationHistoryRepository, times(1)).findTotalPointsByClientId(anyLong());
        verify(pointPolicyRepository, times(1)).findById(anyLong());
    }

    @Test
    void testFindPointWithNullUserIdHeader() {
        // Mock data
        HttpHeaders headers = new HttpHeaders();

        // Execute and assert
        ClientNotFoundException exception = assertThrows(ClientNotFoundException.class, () -> {
            totalPointAmountService.findPoint(headers);
        });

        assertEquals("유저를 찾을수 없습니다.", exception.getMessage());
    }

    @Test
    void testFindPointWithNegativeTotalPoints() {
        // Mock data
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-User-Id", "1");

        when(userRankClient.getClientGradeRate(anyLong())).thenReturn(ResponseEntity.ok(new ClientGradeRateResponseDto(1L)));
        when(pointUsageHistoryRepository.findTotalPointsByClientId(anyLong())).thenReturn(300L);
        when(pointAccumulationHistoryRepository.findTotalPointsByClientId(anyLong())).thenReturn(200L);
        when(pointPolicyRepository.findById(anyLong())).thenReturn(java.util.Optional.of(new PointPolicy("포인트", 10L)));

        // Execute the method
        TotalPointAmountResponseDto result = totalPointAmountService.findPoint(headers);

        // Verify and assert
        assertNotNull(result);
        assertEquals(0L, result.getTotalPoint());
        assertEquals(10L, result.getPointAccumulationRate());

        verify(userRankClient, times(1)).getClientGradeRate(anyLong());
        verify(pointUsageHistoryRepository, times(1)).findTotalPointsByClientId(anyLong());
        verify(pointAccumulationHistoryRepository, times(1)).findTotalPointsByClientId(anyLong());
        verify(pointPolicyRepository, times(1)).findById(anyLong());
    }
}
