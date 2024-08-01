package com.service.point.service;

import com.service.point.dto.request.ClientPointAccumulationResponseDto;
import com.service.point.dto.request.PointPolicyActiveRequestDto;
import com.service.point.dto.request.PointPolicyModifyRequestDto;
import com.service.point.dto.request.PointPolicyRegisterRequestDto;
import com.service.point.dto.response.PointPolicyAdminListResponseDto;
import com.service.point.dto.response.PointPolicyDetailResponseDto;
import org.springframework.data.domain.Page;

public interface PointPolicyService {

    /**
     * 포인트 정책을 저장하는 함수 (관리자 권한 필요)
     *
     * @author jjeonmin
     * @param pointPolicyRegisterRequestDto 포인트정책 Dto
     */
    void savePointPolicy(PointPolicyRegisterRequestDto pointPolicyRegisterRequestDto);

    /**
     * 관리자페이지에서 페이지와, 한 페이지의 사이즈를 인자로 받아 포인트량이 null이 아닌 포인트 정책을 반환 (관리자 권한 필요)
     *
     * @param page 요구하는 페이지 값
     * @param size 한페이지 내의 보여질 정보
     * @return 해당하는 포인트 정책 반환
     */
    Page<PointPolicyAdminListResponseDto> getAllPointPolicies(int page, int size);

    /**
     * 관리자페이지에서 포인트정책아이디를 받아 해당 포인트정책을 반환 (관리자 권한 필요)
     *
     * @author jjeonmin
     * @param pointPolicyId 포인트정책아이디
     * @return 해당하는 포인트 정책 반환
     */
    PointPolicyDetailResponseDto findPointPolicy (long pointPolicyId);

    /**
     * 관리자페이지에서 포인트 정책 수정을 수행 (관리자 권한 필요)
     *
     * @author jjeonmin
     * @param pointPolicyModifyRequestDto 포인트정책 수정을위한 Dto
     */
    void modifyPointPolicy(PointPolicyModifyRequestDto pointPolicyModifyRequestDto);

    /**
     * 관리자페이지에서 포인트 정책 활성화를 수행
     *
     * @author jjeonmin
     * @param pointPolicyActiveRequestDto 포인트 정책 활성화를 위한 Dto
     */
    void pointPolicyActive(PointPolicyActiveRequestDto pointPolicyActiveRequestDto);





    ClientPointAccumulationResponseDto findByAccumulation(long pointPolicyId);

    }
