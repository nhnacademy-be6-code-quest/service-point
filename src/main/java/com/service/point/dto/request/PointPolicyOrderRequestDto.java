package com.service.point.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PointPolicyOrderRequestDto {
    Integer accumulatedPoint;
}
