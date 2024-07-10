package com.service.point.service.impl;

import com.service.point.dto.response.TotalPointAmountResponseDto;
import com.service.point.exception.ClientNotFoundException;
import com.service.point.repository.PointAccumulationHistoryRepository;
import com.service.point.repository.PointUsageHistoryRepository;
import com.service.point.service.TotalPointAmountService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TotalPointAmountServiceImpl implements TotalPointAmountService {
    private final PointAccumulationHistoryRepository pointAccumulationHistoryRepository;
    private final PointUsageHistoryRepository pointUsageHistoryRepository;
    private static final String ID_HEADER = "X-User-Id";

    @Override
    public TotalPointAmountResponseDto findPoint(HttpHeaders headers){
        if (headers.getFirst(ID_HEADER)==null){
            throw new ClientNotFoundException("유저를 찾을수 없습니다.");
        }
        long clientId = NumberUtils.toLong(headers.getFirst(ID_HEADER),-1L);
        Integer usePointAmount =pointUsageHistoryRepository.findTotalPointsByClientId(clientId);
        Integer accumulationAmount = pointAccumulationHistoryRepository.findTotalPointsByClientId(clientId);
        if (usePointAmount ==null){
            usePointAmount=0;
        }
        if(accumulationAmount==null){
            accumulationAmount=0;
        }
        TotalPointAmountResponseDto dto = new TotalPointAmountResponseDto();
        dto.setTotalPoint(accumulationAmount-usePointAmount);
        if (dto.getTotalPoint() < 0){
            dto.setTotalPoint(0);
        }
        return dto;
    }
}
