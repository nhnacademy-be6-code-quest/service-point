package com.service.point.config;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class MemberShipMessageDto implements Serializable {
    private Long clientId;
}
