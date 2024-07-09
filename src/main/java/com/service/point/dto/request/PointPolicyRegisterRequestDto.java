package com.service.point.dto.request;

import com.service.point.domain.PointPolicyType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PointPolicyRegisterRequestDto {

    private PointPolicyType pointPolicyType;
    private Integer pointValue;
}
