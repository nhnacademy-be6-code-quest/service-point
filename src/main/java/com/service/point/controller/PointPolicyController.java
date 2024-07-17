package com.service.point.controller;

import com.service.point.dto.request.PointPolicyActiveRequestDto;
import com.service.point.dto.request.PointPolicyModifyRequestDto;
import com.service.point.dto.request.PointPolicyRegisterRequestDto;
import com.service.point.dto.response.PointPolicyAdminListResponseDto;
import com.service.point.dto.response.PointPolicyDetailResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "PointPolicy", description = "포인트 정책 관련 API")
public interface PointPolicyController {
    @Operation(
        summary = "포인트 정책 등록",
        description = "PointPolicy - 포인트 정책 등록",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "포인트 정책 내용"
            )
        }
    )
    @PostMapping("/api/point/policy/register")
    ResponseEntity<PointPolicyRegisterRequestDto> savePointPolicy(
        @Parameter(description = "포인트 정책 등록정보")
        @RequestBody PointPolicyRegisterRequestDto pointPolicyRegisterRequestDto);

    @Operation(
        summary = "포인트 정책 조회",
        description = "PointPolicy - 모든 포인트 정책 조회",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "포인트 정책 정보"
            )
        }
    )
    @GetMapping("/api/point/policy")
    ResponseEntity<Page<PointPolicyAdminListResponseDto>> findAllPointPolicy(
        @Parameter(description = "페이지")
        @RequestParam int page,
        @Parameter(description = "페이지에 보여줄 개수")
        @RequestParam int size);


    @Operation(
        summary = "포인트 정책 수정",
        description = "PointPolicy - 포인트 정책 수정",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "success"
            ),
            @ApiResponse(
                responseCode = "400",
                description = "fail"
            )
        }
    )
    @PutMapping("/api/point/policy/modify")
    ResponseEntity<String> modifyPointPolicy(
        @Parameter(description = "포인트정책 아이디, 포인트양")
        @RequestBody PointPolicyModifyRequestDto pointPolicyModifyRequestDto);

    @Operation(
        summary = "포인트 정책 활성화",
        description = "PointPolicy - 포인트 정책 활성화",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "success"
            ),
            @ApiResponse(
                responseCode = "400",
                description = "fail"
            )
        }
    )
    @PutMapping("/api/point/policy/active")
    @Parameter(description = "포인트정책 아이디, 포인트적립타입")
    ResponseEntity<String> pointPolicyActive(@RequestBody PointPolicyActiveRequestDto pointPolicyActiveRequestDto);
}
