package com.service.point.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class PointPolicyModifyRequestDto {
    long pointPolicyId;
    Long pointValue;
}
