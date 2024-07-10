package com.service.point.controller.impl;

import com.service.point.controller.PointPolicyController;
import com.service.point.dto.request.PointPolicyRegisterRequestDto;
import com.service.point.dto.response.PointPolicyAdminListResponseDto;
import com.service.point.service.impl.PointPolicyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PointPolicyControllerImpl implements PointPolicyController {

    private final PointPolicyServiceImpl pointPolicyService;

    @PostMapping("/api/point/policy/register")
    @Override
    public ResponseEntity<PointPolicyRegisterRequestDto> savePointPolicy(@RequestBody PointPolicyRegisterRequestDto pointPolicyRegisterRequestDto){
        pointPolicyService.savePointPolicy(pointPolicyRegisterRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pointPolicyRegisterRequestDto);
    }

    @GetMapping("/api/point/policy")
    @Override
    public ResponseEntity<Page<PointPolicyAdminListResponseDto>> findAllPointPolicy(@RequestParam int page, @RequestParam int size){
        return ResponseEntity.ok(pointPolicyService.getAllPointPolicies(page, size));
    }

    @DeleteMapping("/api/point/policy/{pointPolicyId}")
    @Override
    public ResponseEntity<String> deletePointPolicy(@PathVariable long pointPolicyId){
        try {
            pointPolicyService.deletePointPolicy(pointPolicyId);
            return new ResponseEntity<>("success",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("fail",HttpStatus.ACCEPTED);
        }
    }

}
