package com.service.point.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PointUsagePaymentRequestDto {
    Long pointUsageAmount;
    Long clientId;
}
