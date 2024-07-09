package com.service.point.service.impl;

import com.service.point.repository.PointAccumulationHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PointAccumulationHistoryServiceImpl {

    private final PointAccumulationHistoryRepository pointAccumulationHistoryRepository;

}
