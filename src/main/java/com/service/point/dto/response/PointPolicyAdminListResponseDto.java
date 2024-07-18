package com.service.point.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class PointPolicyAdminListResponseDto {
    long pointPolicyId;
    String pointAccumulationType;
    Long pointValue;
    String pointPolicyCreationDate;
    String pointStatus;

}
