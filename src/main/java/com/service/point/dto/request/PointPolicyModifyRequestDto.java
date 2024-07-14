package com.service.point.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PointPolicyModifyRequestDto {
    long pointPolicyId;
    Integer pointValue;
}
