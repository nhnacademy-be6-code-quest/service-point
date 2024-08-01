package com.service.point.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PointAccumulationAdminPageResponseDto {
    Long pointAccumulationAmount;
    String pointAccumulationHistoryDate;
    String pointAccumulationType;
    String clientName;
}
