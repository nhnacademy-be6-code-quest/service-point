package com.service.point.service.impl;


import com.service.point.domain.PointStatus;
import com.service.point.domain.entity.PointPolicy;
import com.service.point.dto.request.PointPolicyActiveRequestDto;
import com.service.point.dto.request.PointPolicyModifyRequestDto;
import com.service.point.dto.request.PointPolicyRegisterRequestDto;
import com.service.point.dto.response.PointPolicyAdminListResponseDto;
import com.service.point.dto.response.PointPolicyDetailResponseDto;
import com.service.point.exception.PointPolicyNotFoundException;
import com.service.point.repository.PointPolicyRepository;
import com.service.point.service.PointPolicyService;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointPolicyServiceImpl implements PointPolicyService {

    private final PointPolicyRepository pointPolicyRepository;
    @Override
    public void savePointPolicy(PointPolicyRegisterRequestDto pointPolicyRegisterRequestDto) {

        PointPolicy pointPolicy = new PointPolicy(
            pointPolicyRegisterRequestDto.getPointPolicyType(),
            pointPolicyRegisterRequestDto.getPointValue());
        pointPolicyRepository.save(pointPolicy);
    }
    @Override
    public Page<PointPolicyAdminListResponseDto> getAllPointPolicies(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size,
            Sort.by("pointPolicyType").descending());
        Page<PointPolicy> pointPolicies = pointPolicyRepository.findByPointValueIsNotNull(
            pageRequest);
        return pointPolicies.map(pointPolicy -> {
            PointPolicyAdminListResponseDto dto = new PointPolicyAdminListResponseDto();
            dto.setPointPolicyType(pointPolicy.getPointPolicyType());
            dto.setPointValue(pointPolicy.getPointValue());
            dto.setPointPolicyId(pointPolicy.getPointPolicyId());
            dto.setPointPolicyCreationDate(
                String.valueOf(pointPolicy.getPointPolicyCreationDate()));
            dto.setPointStatus(pointPolicy.getPointStatus().getValue());
            return dto;
        });
    }

    public PointPolicyDetailResponseDto findPointPolicy (long pointPolicyId){
        PointPolicy pointPolicy = pointPolicyRepository.findById(pointPolicyId).orElseThrow(()-> new PointPolicyNotFoundException("포인트 정책을 찾을수 없습니다."));
        PointPolicyDetailResponseDto dto = new PointPolicyDetailResponseDto();
        dto.setPointValue(pointPolicy.getPointValue());
        return dto;
    }

    public void modifyPointPolicy(PointPolicyModifyRequestDto pointPolicyModifyRequestDto){

        PointPolicy pointPolicy = pointPolicyRepository.findById(pointPolicyModifyRequestDto.getPointPolicyId()).orElseThrow(()-> new PointPolicyNotFoundException("포인트 정책을 찾을수 없습니다."));
        pointPolicy.setPointValue(pointPolicyModifyRequestDto.getPointValue());
        pointPolicyRepository.save(pointPolicy);
    }

    public void pointPolicyActive(PointPolicyActiveRequestDto pointPolicyActiveRequestDto){
        PointPolicy pointPolicy = pointPolicyRepository.findByPointPolicyTypeContainingAndPointStatus(pointPolicyActiveRequestDto.getPointPolicyType(),
            PointStatus.ACTIVATE);
        pointPolicy.setPointStatus(PointStatus.DISABLED);

        PointPolicy pointPolicyActive = pointPolicyRepository.findById(
            pointPolicyActiveRequestDto.getPointPolicyId()).orElseThrow(()-> new PointPolicyNotFoundException("포인트 정책을 찾을수 없습니다."));
        pointPolicyActive.setPointStatus(PointStatus.ACTIVATE);
        pointPolicyRepository.save(pointPolicyActive);
    }

    @Override
    public void deletePointPolicy(long pointPolicyId) {
        PointPolicy pointPolicy = pointPolicyRepository.findById(pointPolicyId).orElseThrow(() ->
            new PointPolicyNotFoundException("포인트 정책을 찾을수 없습니다."));
        pointPolicy.setPointStatus(PointStatus.DISABLED);
        pointPolicy.setPointPolicyExpirationDate(LocalDate.now());
        pointPolicyRepository.save(pointPolicy);
    }

}

