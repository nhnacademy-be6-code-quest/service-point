package com.service.point.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ReviewMessageDto {
    Long clientId;
    Boolean hasImage;
}
