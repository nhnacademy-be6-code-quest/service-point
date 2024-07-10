package com.service.point.controller.impl;

import com.service.point.dto.request.PointRewardOrderRequestDto;
import com.service.point.dto.request.PointRewardRefundRequestDto;
import com.service.point.exception.ClientNotFoundException;
import com.service.point.exception.PointPolicyNotFoundException;
import com.service.point.service.impl.PointAccumulationHistoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PointAccumulationHistoryControllerImpl {

    private final PointAccumulationHistoryServiceImpl pointAccumulationHistoryService;

    @PostMapping("/api/point/order")
    public ResponseEntity<String> rewardOrderPoint(@RequestHeader HttpHeaders headers,
        @RequestBody PointRewardOrderRequestDto pointRewardOrderRequestDto) {
        try {
            pointAccumulationHistoryService.orderPoint(headers, pointRewardOrderRequestDto);
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("fail", HttpStatus.ACCEPTED);
        }
    }


    @PostMapping("/api/point/review")
    public ResponseEntity<String> rewardReviewPoint(@RequestHeader HttpHeaders headers) {
        try {
            pointAccumulationHistoryService.reviewPoint(headers);
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("fail", HttpStatus.ACCEPTED);
        }
    }

    @PostMapping("/api/point/refund")
    public ResponseEntity<String> rewardRefundPoint(@RequestHeader HttpHeaders headers, @RequestBody
    PointRewardRefundRequestDto pointRewardRefundRequestDto) {
        try {
            pointAccumulationHistoryService.refundPoint(headers, pointRewardRefundRequestDto);
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("fail", HttpStatus.ACCEPTED);
        }

    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<String> handleClientNotFoundException(ClientNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PointPolicyNotFoundException.class)
    public ResponseEntity<String> handlePointPolicyNotFoundException(
        PointPolicyNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
