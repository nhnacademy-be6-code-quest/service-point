package com.service.point.service;

import com.service.point.domain.PointStatus;
import com.service.point.domain.entity.PointPolicy;
import com.service.point.dto.request.ClientPointAccumulationResponseDto;
import com.service.point.dto.request.PointPolicyActiveRequestDto;
import com.service.point.dto.request.PointPolicyModifyRequestDto;
import com.service.point.dto.request.PointPolicyRegisterRequestDto;
import com.service.point.dto.response.PointPolicyAdminListResponseDto;
import com.service.point.dto.response.PointPolicyDetailResponseDto;
import com.service.point.exception.PointPolicyNotFoundException;
import com.service.point.repository.PointPolicyRepository;
import com.service.point.service.impl.PointPolicyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class PointPolicyServiceTest {

    @Mock
    private PointPolicyRepository pointPolicyRepository;

    @InjectMocks
    private PointPolicyServiceImpl pointPolicyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSavePointPolicy() {
        PointPolicyRegisterRequestDto requestDto = new PointPolicyRegisterRequestDto();
        requestDto.setPointAccumulationType("TYPE");
        requestDto.setPointValue(10L);

        pointPolicyService.savePointPolicy(requestDto);

        verify(pointPolicyRepository, times(1)).save(any(PointPolicy.class));
    }

    @Test
    void testGetAllPointPolicies() {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("pointAccumulationType").descending());
        PointPolicy pointPolicy = new PointPolicy("포인트",10L);
        pointPolicy.setPointStatus(PointStatus.ACTIVATE); // Initialize PointStatus
        Page<PointPolicy> page = new PageImpl<>(Collections.singletonList(pointPolicy));


        when(pointPolicyRepository.findByPointValueIsNotNull(pageRequest)).thenReturn(page);

        Page<PointPolicyAdminListResponseDto> result = pointPolicyService.getAllPointPolicies(0, 10);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(pointPolicyRepository, times(1)).findByPointValueIsNotNull(pageRequest);
    }

    @Test
    void testFindPointPolicy() {
        PointPolicy pointPolicy = new PointPolicy("포인트",10L);
        when(pointPolicyRepository.findById(anyLong())).thenReturn(Optional.of(pointPolicy));

        PointPolicyDetailResponseDto result = pointPolicyService.findPointPolicy(1L);

        assertNotNull(result);
        assertEquals(10L, result.getPointValue());
        assertEquals("포인트", result.getPointAccumulationType());
        verify(pointPolicyRepository, times(1)).findById(1L);
    }

    @Test
    void testFindPointPolicyNotFound() {
        when(pointPolicyRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(PointPolicyNotFoundException.class, () -> pointPolicyService.findPointPolicy(1L));
    }

    @Test
    void testModifyPointPolicy() {
        PointPolicy pointPolicy = new PointPolicy();
        pointPolicy.setPointValue(10L);
        when(pointPolicyRepository.findById(anyLong())).thenReturn(Optional.of(pointPolicy));

        PointPolicyModifyRequestDto requestDto = new PointPolicyModifyRequestDto();
        requestDto.setPointPolicyId(1L);
        requestDto.setPointValue(20L);

        pointPolicyService.modifyPointPolicy(requestDto);

        assertEquals(20L, pointPolicy.getPointValue());
        verify(pointPolicyRepository, times(1)).save(pointPolicy);
    }
    @Test
    void testPointPolicyActive() {
        PointPolicy pointPolicy = new PointPolicy();
        pointPolicy.setPointStatus(PointStatus.ACTIVATE);
        when(pointPolicyRepository.findByPointAccumulationTypeEqualsAndPointStatus(anyString(), any(PointStatus.class))).thenReturn(pointPolicy);
        when(pointPolicyRepository.findById(anyLong())).thenReturn(Optional.of(pointPolicy));

        PointPolicyActiveRequestDto requestDto = new PointPolicyActiveRequestDto();
        requestDto.setPointAccumulationType("TYPE");
        requestDto.setPointPolicyId(1L);

        pointPolicyService.pointPolicyActive(requestDto);

        assertEquals(PointStatus.ACTIVATE, pointPolicy.getPointStatus());
        verify(pointPolicyRepository, times(1)).save(pointPolicy);
    }

    @Test
    void testFindByAccumulation() {
        PointPolicy pointPolicy = new PointPolicy();
        pointPolicy.setPointValue(10L);
        when(pointPolicyRepository.findById(anyLong())).thenReturn(Optional.of(pointPolicy));

        ClientPointAccumulationResponseDto result = pointPolicyService.findByAccumulation(1L);

        assertNotNull(result);
        assertEquals(10L, result.getPointValue());
        verify(pointPolicyRepository, times(1)).findById(1L);
    }
}