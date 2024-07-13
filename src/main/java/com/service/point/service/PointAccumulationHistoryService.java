package com.service.point.service;

import com.service.point.dto.request.PointRewardOrderRequestDto;
import com.service.point.dto.request.PointRewardRefundRequestDto;
import com.service.point.dto.response.PointAccumulationAdminPageResponseDto;
import com.service.point.dto.response.PointAccumulationMyPageResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;

public interface PointAccumulationHistoryService {

    /**
     * 회원 아이디와 결제 금액을 받아 회원에게 포인트를 지급하는 함수
     *
     * @author jjeonmin
     * @param headers 회원 아이디 정보
     * @param pointPolicyOrderResponseDto 회원정보를 가지는 DTO
     */
    void orderPoint(HttpHeaders headers,
        PointRewardOrderRequestDto pointPolicyOrderResponseDto);

    /**
     * 리뷰작성시 회원에게 포인트를 지급하는 함수
     *
     * @author jjeonmin
     * @param message 리뷰작성유저정보
     */
    void reviewPoint(String message);

    /**
     *  회원가입시 회원에게 포인트를 지급하는 함수
     *
     * @author jjeonmin
     * @param message 회원가입유저정보
     */
    void memberShipPoint(String message);

    /**
     *  환불정보를 받아 회원에게 포인트를 지급하는 함수
     *
     * @author jjeonmin
     * @param headers 회원의 아이디 정보
     * @param pointRewardRefundRequestDto 환불정보를 가지는 DTO
     */
    void refundPoint(HttpHeaders headers, PointRewardRefundRequestDto pointRewardRefundRequestDto);

    /**
     *  페이지와, 한 페이지의 사이즈를 인자로 받아 유저의 포인트 지급내역을 반환하는 함수 (권한 필요)
     *
     * @author jjeonmin
     * @param headers 회원의 아이디 정보
     * @param page 요구하는 페이지의 값
     * @param size 한페이지 내의 보여질 정보의 갯수
     * @return 해당하는 페이지의 유저정보 페이지를 반환
     */
    Page<PointAccumulationMyPageResponseDto> rewardClientPoint(HttpHeaders headers, int page, int size);

    /**
     * 페이지와, 한 페이지의 사이즈를 인자로 받아 모든 유저의 포인트 지급내역 정보를 반환하는 함수 (관리자 권한 필요)
     *
     * @author jjeonmin
     * @param page 요구하는 페이지의 값
     * @param size 한페이지 내의 보여질 정보의 갯수
     * @return 해당하는 페이지의 유저 포인트 지급내역 페이지를 반환
     */
    Page<PointAccumulationAdminPageResponseDto> rewardUserPoint(int page, int size);

    /**
     * 사용자의 포인트 지급내역을 삭제하는 반환하는 함수 (관리자 권한 필요)
     *
     * @author jjeonmin
     * @param pointAccumulationHistoryId 포인트 지급내역 아이디
     */
    void deletePoint(long pointAccumulationHistoryId);

    }
