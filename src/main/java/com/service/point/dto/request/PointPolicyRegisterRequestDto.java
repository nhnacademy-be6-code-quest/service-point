package com.service.point.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PointPolicyRegisterRequestDto {

    private String pointAccumulationType;
    private Integer pointValue;
}
