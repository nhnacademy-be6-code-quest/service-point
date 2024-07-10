package com.service.point.controller.impl;

import com.service.point.dto.request.PointUsagePaymentRequestDto;
import com.service.point.dto.request.PointUsageRefundRequestDto;
import com.service.point.service.impl.PointUsageHistoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PointUsageControllerImpl {

    private final PointUsageHistoryServiceImpl pointUsageHistoryService;

    @PostMapping("/api/point/use/payment")
    public ResponseEntity<String> usePaymentPoint(
        @RequestBody PointUsagePaymentRequestDto pointUsagePaymentRequestDto, @RequestHeader
    HttpHeaders headers) {
        try {
            pointUsageHistoryService.usedPaymentPoint(pointUsagePaymentRequestDto, headers);
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("fail", HttpStatus.ACCEPTED);
        }
    }

    @PostMapping("/api/point/use/refund")
    public ResponseEntity<String> useRefundPoint(
        @RequestBody PointUsageRefundRequestDto pointUsageRefundRequestDto,
        @RequestHeader HttpHeaders headers) {
        try {
            pointUsageHistoryService.usedRefundPoint(pointUsageRefundRequestDto, headers);
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("fail", HttpStatus.ACCEPTED);
        }
    }
}
