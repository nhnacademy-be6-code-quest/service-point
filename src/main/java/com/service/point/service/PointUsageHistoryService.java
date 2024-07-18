package com.service.point.service;

import com.service.point.dto.request.PointUsagePaymentRequestDto;
import com.service.point.dto.request.PointUsageRefundRequestDto;
import com.service.point.dto.response.PointUsageAdminPageResponseDto;
import com.service.point.dto.response.PointUsageMyPageResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;

public interface PointUsageHistoryService {

    /**
     * 결제시 사용한 포인트량을 사용 내역에 추가하는 함수
     *
     * @author jjeonmin
     * @param pointUsagePaymentRequestDto 결제 시 사용한 포인트정보 Dto
     */
    void usedPaymentPoint(PointUsagePaymentRequestDto pointUsagePaymentRequestDto);

    /**
     * 결제시 적립된 포인트량을 사용내역에 추가하는 함수
     *
     * @author jjeonmin
     * @param pointUsageRefundRequestDto 결제 시 적립된 포인트정보 Dto
     * @param headers 회원의 아이디정보
     */
    void usedRefundPoint (PointUsageRefundRequestDto pointUsageRefundRequestDto, HttpHeaders headers);

    /**
     * 마이페이지에서 페이지와, 한 페이지의 사이즈를 인자로 받아 회원의 포인트 사용내역을 반환 (권한 필요)
     *
     * @author jjeonmin
     * @param headers 회원의 아이디정보
     * @param page 요구하는 페이지의 값입니다.
     * @param size 한페이지 내의 보여질 정보의 갯수입니다.
     * @return 해당하는 페이지의 포인트 사용내역 페이지를 반환합니다.
     */
    Page<PointUsageMyPageResponseDto> useClientPoint(HttpHeaders headers, int page, int size);

    /**
     * 관리자페지에서 페이지와, 한 페이지의 사이즈를 인자로 받아 모든 회원의 포인트 사용내역을 반환 (관리자 권한 필요)
     *
     * @author jjeonmin
     * @param page 요구하는 페이지의 값입니다.
     * @param size 한페이지 내의 보여질 정보의 갯수입니다.
     * @return 해당하는 페이지의 모든 포인트 사용내역 페이지를 반환합니다.
     */
    Page<PointUsageAdminPageResponseDto> useUserPoint(int page, int size);
}
