package com.service.point.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PointPolicyActiveRequestDto {
    long pointPolicyId;
    String pointPolicyType;
}
