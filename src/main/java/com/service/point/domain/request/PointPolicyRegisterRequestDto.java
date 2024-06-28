package com.service.point.domain.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class PointPolicyRegisterRequestDto {

    private String pointPolicyName;
    private Integer pointPolicyAccumulationRate;
    private Integer pointPolicyAccumulationAmount;
}
