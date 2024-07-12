package com.service.point.controller.impl;

import com.service.point.controller.TotalPointController;
import com.service.point.dto.response.TotalPointAmountResponseDto;
import com.service.point.service.TotalPointAmountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TotalPointControllerImpl implements TotalPointController {

    private final TotalPointAmountService totalPointAmountService;

    @GetMapping("/api/point")
    @Override
    public TotalPointAmountResponseDto findPoint(@RequestHeader HttpHeaders headers) {
        return totalPointAmountService.findPoint(headers);
    }
}
