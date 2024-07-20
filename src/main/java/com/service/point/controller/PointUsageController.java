package com.service.point.controller;

import com.service.point.dto.response.PointUsageAdminPageResponseDto;
import com.service.point.dto.response.PointUsageMyPageResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "포인트 사용내역", description = "포인트 사용내역 관련 API")
public interface PointUsageController {



    @Operation(
        summary = "포인트 적립내역",
        description = "MyPage - 사용자 적립포인트 내역",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Success"
            )
        }
    )
    @GetMapping("/api/point/myPage/use")
    ResponseEntity<Page<PointUsageMyPageResponseDto>> usedClientPoint(
        @Parameter(description = "사용자 아이디")
        @RequestHeader HttpHeaders headers,
        @Parameter(description = "페이지")
        @RequestParam int page,
        @Parameter(description = "페이지에 보여줄 개수")
        @RequestParam int size);

    @Operation(
        summary = "포인트 적립내역",
        description = "AdminPage - 회원 적립포인트 내역",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Success"
            )
        }
    )
    @GetMapping("/api/point/adminPage/use")
    ResponseEntity<Page<PointUsageAdminPageResponseDto>> usedUserPoint(
        @Parameter(description = "페이지")
        @RequestParam int page,
        @Parameter(description = "페이지에 보여줄 개수")
        @RequestParam int size);
}