package com.service.point.service.impl;

import com.service.point.repository.ClientPointAccumulationHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClientAccumulationHistoryServiceImpl {

    private final ClientPointAccumulationHistoryRepository clientPointAccumulationHistoryRepository;


}
