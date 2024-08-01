package com.service.point.service.impl;


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
import com.service.point.service.PointPolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointPolicyServiceImpl implements PointPolicyService {

    private final PointPolicyRepository pointPolicyRepository;
    private static final String NO_POINT_POLICY="포인트 정책을 찾을수 없습니다.";

    @Override
    public void savePointPolicy(PointPolicyRegisterRequestDto pointPolicyRegisterRequestDto) {

        PointPolicy pointPolicy = new PointPolicy(
            pointPolicyRegisterRequestDto.getPointAccumulationType(),
            pointPolicyRegisterRequestDto.getPointValue());
        pointPolicyRepository.save(pointPolicy);
    }

    @Override
    public Page<PointPolicyAdminListResponseDto> getAllPointPolicies(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size,
            Sort.by("pointAccumulationType").descending());
        Page<PointPolicy> pointPolicies = pointPolicyRepository.findByPointValueIsNotNull(
            pageRequest);
        return pointPolicies.map(pointPolicy -> {
            PointPolicyAdminListResponseDto dto = new PointPolicyAdminListResponseDto();
            dto.setPointAccumulationType(pointPolicy.getPointAccumulationType());
            dto.setPointValue(pointPolicy.getPointValue());
            dto.setPointPolicyId(pointPolicy.getPointPolicyId());
            dto.setPointPolicyCreationDate(
                String.valueOf(pointPolicy.getPointPolicyCreationDate()));
            dto.setPointStatus(pointPolicy.getPointStatus().getValue());
            return dto;
        });
    }

    @Override
    public PointPolicyDetailResponseDto findPointPolicy(long pointPolicyId) {
        PointPolicy pointPolicy = pointPolicyRepository.findById(pointPolicyId)
            .orElseThrow(() -> new PointPolicyNotFoundException(NO_POINT_POLICY));
        PointPolicyDetailResponseDto dto = new PointPolicyDetailResponseDto();
        dto.setPointValue(pointPolicy.getPointValue());
        dto.setPointAccumulationType(pointPolicy.getPointAccumulationType());
        return dto;
    }

    @Override
    public void modifyPointPolicy(PointPolicyModifyRequestDto pointPolicyModifyRequestDto) {

        PointPolicy pointPolicy = pointPolicyRepository.findById(
                pointPolicyModifyRequestDto.getPointPolicyId())
            .orElseThrow(() -> new PointPolicyNotFoundException(NO_POINT_POLICY));
        pointPolicy.setPointValue(pointPolicyModifyRequestDto.getPointValue());
        pointPolicyRepository.save(pointPolicy);
    }

    @Override
    public void pointPolicyActive(PointPolicyActiveRequestDto pointPolicyActiveRequestDto) {

        PointPolicy pointPolicy = pointPolicyRepository.findByPointAccumulationTypeEqualsAndPointStatus(
            pointPolicyActiveRequestDto.getPointAccumulationType(),
            PointStatus.ACTIVATE);
        if (pointPolicy!=null) {
            pointPolicy.setPointStatus(PointStatus.DISABLED);
        }
        PointPolicy pointPolicyActive = pointPolicyRepository.findById(
                pointPolicyActiveRequestDto.getPointPolicyId())
            .orElseThrow(() -> new PointPolicyNotFoundException(NO_POINT_POLICY));
        pointPolicyActive.setPointStatus(PointStatus.ACTIVATE);
        pointPolicyRepository.save(pointPolicyActive);
    }
    @Override
    public ClientPointAccumulationResponseDto findByAccumulation(long pointPolicyId) {
        PointPolicy pointPolicy = pointPolicyRepository.findById(pointPolicyId).orElseThrow(()-> new PointPolicyNotFoundException(NO_POINT_POLICY));
        ClientPointAccumulationResponseDto dto = new ClientPointAccumulationResponseDto();
        dto.setPointValue(pointPolicy.getPointValue());
        return dto;
    }
}

