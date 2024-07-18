package com.service.point.controller.impl;

import com.service.point.controller.PointUsageController;
import com.service.point.dto.request.PointUsagePaymentRequestDto;
import com.service.point.dto.request.PointUsageRefundRequestDto;
import com.service.point.dto.response.PointUsageAdminPageResponseDto;
import com.service.point.dto.response.PointUsageMyPageResponseDto;
import com.service.point.service.PointUsageHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PointUsageControllerImpl implements PointUsageController {

    private final PointUsageHistoryService pointUsageHistoryService;

    @PostMapping("/api/point/use/refund")
    @Override
    public ResponseEntity<String> useRefundPoint(
        @RequestBody PointUsageRefundRequestDto pointUsageRefundRequestDto,
        @RequestHeader HttpHeaders headers) {
        try {
            pointUsageHistoryService.usedRefundPoint(pointUsageRefundRequestDto, headers);
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/point/myPage/use")
    @Override
    public ResponseEntity<Page<PointUsageMyPageResponseDto>> usedClientPoint(
        @RequestHeader HttpHeaders headers, @RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(pointUsageHistoryService.useClientPoint(headers, page, size));
    }

    @GetMapping("/api/point/adminPage/use")
    @Override
    public ResponseEntity<Page<PointUsageAdminPageResponseDto>> usedUserPoint(
        @RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(pointUsageHistoryService.useUserPoint(page, size));
    }
}
