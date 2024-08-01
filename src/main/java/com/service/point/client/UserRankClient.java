package com.service.point.client;

import com.service.point.dto.response.ClientGradeRateResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="userRank", url="http://localhost:8001")
public interface UserRankClient {
    @GetMapping("/api/client/grade")
    ResponseEntity<ClientGradeRateResponseDto> getClientGradeRate(@RequestParam Long clientId);

}
