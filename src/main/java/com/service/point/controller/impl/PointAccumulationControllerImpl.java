package com.service.point.controller.impl;

import com.service.point.controller.PointAccumulationController;
import com.service.point.dto.request.PointRewardOrderRequestDto;
import com.service.point.dto.response.PointAccumulationAdminPageResponseDto;
import com.service.point.dto.response.PointAccumulationMyPageResponseDto;
import com.service.point.service.PointAccumulationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PointAccumulationControllerImpl implements PointAccumulationController {

    private final PointAccumulationService pointAccumulationService;

    @PostMapping("/api/point/order")
    @Override
    public ResponseEntity<String> rewardOrderPoint(@RequestHeader HttpHeaders headers,
        @RequestBody PointRewardOrderRequestDto pointRewardOrderRequestDto) {
        try {
            pointAccumulationService.orderPoint(headers, pointRewardOrderRequestDto);
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST );
        }
    }


    @GetMapping("/api/point/myPage/reward")
    public ResponseEntity<Page<PointAccumulationMyPageResponseDto>> findClientPoint(
        @RequestHeader HttpHeaders headers, @RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(
            pointAccumulationService.rewardClientPoint(headers, page, size));
    }

    @GetMapping("/api/point/adminPage/reward")
    public ResponseEntity<Page<PointAccumulationAdminPageResponseDto>> findUserPoint(@RequestHeader HttpHeaders headers,
        @RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(pointAccumulationService.rewardUserPoint(headers, page, size));
    }

    @DeleteMapping("/api/point/adminPage/delete/{pointAccumulationHistoryId}")
    public ResponseEntity<String> deleteUserPoint(@PathVariable long pointAccumulationHistoryId
    ){
        try {
            pointAccumulationService.deletePoint(pointAccumulationHistoryId);
            return new ResponseEntity<>("success",HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("fail",HttpStatus.BAD_REQUEST);
        }
    }

}
