package com.service.point.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PointAccumulationMyPageResponseDto {
    Long pointAccumulationAmount;
    String pointAccumulationHistoryDate;
    String pointAccumulationType;
}
