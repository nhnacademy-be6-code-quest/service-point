package com.service.point.config;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberShipMessageDto implements Serializable {
    private Long clientId;
}
