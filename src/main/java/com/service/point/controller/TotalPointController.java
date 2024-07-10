package com.service.point.controller;

import com.service.point.dto.response.TotalPointAmountResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Tag(name = "포인트 총량", description = "포인트총량 관련 API")
public interface TotalPointController {


    @Operation(
        summary = "포인트량 조회",
        description = "Order - 사용자 총보유포인트조회",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "총 포인트 량"
            )
        }
    )
    @GetMapping("/api/point")
    TotalPointAmountResponseDto findPoint(
        @Parameter(description = "포인트를 조회하는 회원의 아이디")
        @RequestHeader HttpHeaders headers);
}