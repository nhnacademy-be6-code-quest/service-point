package com.service.point.dto.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PointUsageRefundMessageDto {
    Long clientId;
    Long pointUsagePayment;
}
