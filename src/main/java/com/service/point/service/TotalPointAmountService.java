package com.service.point.service;

import com.service.point.dto.response.TotalPointAmountResponseDto;
import org.springframework.http.HttpHeaders;

public interface TotalPointAmountService {

    /**
     * 주문시 회원의 포인트 보유 량과 회원에 해당하는 등급의 포인트 적립량을 반환 (권한 필요)
     *
     * @author jjeonmin
     * @param headers 회원의 아이디정보
     * @return 회원의 포인트 보유 량과 포인트 적립량반환
     */
    TotalPointAmountResponseDto findPoint(HttpHeaders headers);
}
