package com.service.point.client;


import com.service.point.dto.response.ClientNameResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "userName", url = "localhost:8001")
public interface UserNameClient {

    @GetMapping("/api/client/name")
    ResponseEntity<ClientNameResponseDto> getClientName(@RequestParam Long clientId);

}
