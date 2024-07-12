package com.service.point.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PointPolicyModifyRequestDto {
    long pointPolicyId;
    Integer pointValue;
}
