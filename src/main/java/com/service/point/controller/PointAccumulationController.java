package com.service.point.controller;

import com.service.point.dto.request.PointRewardOrderRequestDto;
import com.service.point.dto.response.PointAccumulationAdminPageResponseDto;
import com.service.point.dto.response.PointAccumulationMyPageResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "PointAccumulation", description = "포인트 적립 관련 API")
public interface PointAccumulationController {
    @Operation(
        summary = "결제시 포인트 적립",
        description = "Order - 사용자 포인트 적립",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "success"
            )
        }
    )
    @PostMapping("/api/point/order")
    ResponseEntity<String> rewardOrderPoint(
        @Parameter(description = "포인트를 적립하는 회원의 아이디")
        @RequestHeader HttpHeaders headers,
        @Parameter(description = "포인트 적립양")
        @RequestBody PointRewardOrderRequestDto pointRewardOrderRequestDto);


    @Operation(
        summary = "마이페이지 적립 포인트 조회",
        description = "MyPage - 사용자 포인트 조회",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "success"
            )
        }
    )
    @GetMapping("/api/point/myPage/reward")
    ResponseEntity<Page<PointAccumulationMyPageResponseDto>> findClientPoint(
        @Parameter(description = "포인트를 적립하는 회원의 아이디")
        @RequestHeader HttpHeaders headers,
        @Parameter(description = "페이지")
        @RequestParam int page,
        @Parameter(description = "페이지에 보여줄 개수")
        @RequestParam int size);

    @Operation(
        summary = "회원 포인트 조회",
        description = "AdminPage - 사용자 포인트 조회",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "success"
            )
        }
    )
    @GetMapping("/api/point/adminPage/reward")
    ResponseEntity<Page<PointAccumulationAdminPageResponseDto>> findUserPoint(@RequestHeader HttpHeaders headers,
        @Parameter(description = "페이지")
        @RequestParam int page,
        @Parameter(description = "페이지에 보여줄 개수")
        @RequestParam int size);

}
