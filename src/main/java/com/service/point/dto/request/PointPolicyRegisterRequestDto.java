package com.service.point.dto.request;

import com.service.point.domain.PointPolicyType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PointPolicyRegisterRequestDto {

    private PointPolicyType pointPolicyType;
    private Integer pointValue;
}
