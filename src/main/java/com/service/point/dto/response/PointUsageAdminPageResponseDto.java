package com.service.point.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PointUsageAdminPageResponseDto {
    String pointUsageHistoryDate;
    Integer pointUsageAmount;
    long clientId;
    String pointUsageType;
}
