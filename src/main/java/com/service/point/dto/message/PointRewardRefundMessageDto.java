package com.service.point.dto.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PointRewardRefundMessageDto {
    private Long clientId;
    private Long payment;
    private Long discountAmountByPoint;
}
