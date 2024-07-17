package com.service.point.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewMessageDto {
    private Long clientId;
    private boolean hasImage;
}