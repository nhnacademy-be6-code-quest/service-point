package com.service.point.config;

import java.io.Serializable;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Getter
@Service
public class MemberShipMessageDto implements Serializable {
    private Long clientId;
}
