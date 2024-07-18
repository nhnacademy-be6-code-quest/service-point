package com.service.point.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TotalPointAmountResponseDto {
    Long totalPoint;
    Long pointAccumulationRate;
}
