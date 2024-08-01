package com.service.point.controller.impl;

import com.service.point.controller.PointPolicyController;
import com.service.point.dto.request.ClientPointAccumulationResponseDto;
import com.service.point.dto.request.PointPolicyActiveRequestDto;
import com.service.point.dto.request.PointPolicyModifyRequestDto;
import com.service.point.dto.request.PointPolicyRegisterRequestDto;
import com.service.point.dto.response.PointPolicyAdminListResponseDto;
import com.service.point.service.PointPolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PointPolicyControllerImpl implements PointPolicyController {

    private final PointPolicyService pointPolicyService;

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

    @PutMapping("/api/point/policy/modify")
    public ResponseEntity<String> modifyPointPolicy(@RequestBody PointPolicyModifyRequestDto pointPolicyModifyRequestDto){
        try {
            pointPolicyService.modifyPointPolicy(pointPolicyModifyRequestDto);
            return new ResponseEntity<>("success",HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/api/point/policy/active")
    public ResponseEntity<String> pointPolicyActive(@RequestBody PointPolicyActiveRequestDto pointPolicyActiveRequestDto){
        try {
            pointPolicyService.pointPolicyActive(pointPolicyActiveRequestDto);
            return new ResponseEntity<>("success",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("fail",HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/api/point/client/grade")
    public ResponseEntity<ClientPointAccumulationResponseDto> clientPointAccumulation(@RequestParam long pointPolicyId){
        return ResponseEntity.ok(pointPolicyService.findByAccumulation(pointPolicyId));
    }
}
