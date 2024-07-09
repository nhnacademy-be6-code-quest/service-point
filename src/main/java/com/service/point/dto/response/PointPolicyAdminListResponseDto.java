package com.service.point.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PointPolicyAdminListResponseDto {
    String pointPolicyType;
    Integer pointValue;
    String pointPolicyCreationDate;

}
