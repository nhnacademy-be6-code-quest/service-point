package com.service.point.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.point.config.MemberShipMessageDto;
import com.service.point.config.ReviewMessageDto;
import com.service.point.domain.PointStatus;
import com.service.point.domain.entity.PointAccumulationHistory;
import com.service.point.domain.entity.PointPolicy;
import com.service.point.dto.request.PointRewardOrderRequestDto;
import com.service.point.exception.ClientNotFoundException;
import com.service.point.repository.PointAccumulationHistoryRepository;
import com.service.point.repository.PointPolicyRepository;
import com.service.point.service.impl.PointAccumulationHistoryServiceImpl;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;

 class PointAccumulationHistoryServiceTest {

    @Mock
    private PointPolicyRepository pointPolicyRepository;

    @Mock
    private PointAccumulationHistoryRepository pointAccumulationHistoryRepository;

    @Mock
    private HttpHeaders headers;

    @InjectMocks
    private PointAccumulationHistoryServiceImpl pointAccumulationHistoryService;
    @Mock
    private ObjectMapper objectMapper;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
     void testOrderPoint_ClientNotFound() {
        // Given
        PointRewardOrderRequestDto requestDto = new PointRewardOrderRequestDto();
        requestDto.setAccumulatedPoint(100L);
        when(headers.getFirst("X-User-Id")).thenReturn(null);

        // When, Then
        assertThrows(ClientNotFoundException.class, () -> {
            pointAccumulationHistoryService.orderPoint(headers, requestDto);
        });
    }


    @Test
    void testReviewPoint_WithImage() throws IOException {
        // Given
        ReviewMessageDto reviewMessageDto = new ReviewMessageDto();
        reviewMessageDto.setClientId(1L);
        reviewMessageDto.setHasImage(true);
        String message = "{\"clientId\":1,\"hasImage\":true}";
        when(objectMapper.readValue(message, ReviewMessageDto.class)).thenReturn(reviewMessageDto);
        PointPolicy pointPolicy = new PointPolicy("사진리뷰", 20L);
        pointPolicy.setPointStatus(PointStatus.ACTIVATE);
        when(pointPolicyRepository.findByPointAccumulationTypeEqualsAndPointStatus("사진리뷰", PointStatus.ACTIVATE))
            .thenReturn(pointPolicy);

        // When
        pointAccumulationHistoryService.reviewPoint(message);

        // Then
        verify(pointPolicyRepository, times(1)).findByPointAccumulationTypeEqualsAndPointStatus("사진리뷰", PointStatus.ACTIVATE);
        verify(pointAccumulationHistoryRepository, times(1)).save(any(PointAccumulationHistory.class));
    }

    @Test
    void testReviewPoint_WithoutImage() throws IOException {
        // Given
        ReviewMessageDto reviewMessageDto = new ReviewMessageDto();
        reviewMessageDto.setClientId(1L);
        reviewMessageDto.setHasImage(false);
        String message = "{\"clientId\":1,\"hasImage\":false}";
        when(objectMapper.readValue(message, ReviewMessageDto.class)).thenReturn(reviewMessageDto);
        PointPolicy pointPolicy = new PointPolicy("리뷰", 10L);
        pointPolicy.setPointStatus(PointStatus.ACTIVATE);
        when(pointPolicyRepository.findByPointAccumulationTypeEqualsAndPointStatus("리뷰", PointStatus.ACTIVATE))
            .thenReturn(pointPolicy);

        // When
        pointAccumulationHistoryService.reviewPoint(message);

        // Then
        verify(pointPolicyRepository, times(1)).findByPointAccumulationTypeEqualsAndPointStatus("리뷰", PointStatus.ACTIVATE);
        verify(pointAccumulationHistoryRepository, times(1)).save(any(PointAccumulationHistory.class));
    }
    @Test
     void testMemberShipPoint_Success() throws IOException {
        // Given
        MemberShipMessageDto memberShipMessageDto = new MemberShipMessageDto();
        memberShipMessageDto.setClientId(1L);
        String message = "{\"clientId\":1}";
        when(objectMapper.readValue(message, MemberShipMessageDto.class)).thenReturn(memberShipMessageDto);
        PointPolicy pointPolicy = new PointPolicy("회원가입", 50L);
        pointPolicy.setPointStatus(PointStatus.ACTIVATE);
        when(pointPolicyRepository.findByPointAccumulationTypeEqualsAndPointStatus("회원가입", PointStatus.ACTIVATE))
            .thenReturn(pointPolicy);

        // When
        pointAccumulationHistoryService.memberShipPoint(message);

        // Then
        verify(pointPolicyRepository, times(1)).findByPointAccumulationTypeEqualsAndPointStatus("회원가입", PointStatus.ACTIVATE);
        verify(pointAccumulationHistoryRepository, times(1)).save(any(PointAccumulationHistory.class));
    }



}
