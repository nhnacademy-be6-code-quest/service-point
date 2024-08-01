package com.service.point.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PointUsageAdminPageResponseDto {
    String pointUsageHistoryDate;
    Long pointUsageAmount;
    long clientId;
    String pointUsageType;
}
