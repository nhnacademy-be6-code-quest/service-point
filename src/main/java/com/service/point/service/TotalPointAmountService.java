package com.service.point.service;

import com.service.point.dto.response.TotalPointAmountResponseDto;
import org.springframework.http.HttpHeaders;

public interface TotalPointAmountService {
    TotalPointAmountResponseDto findPoint(HttpHeaders headers);
}
