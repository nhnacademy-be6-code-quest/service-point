package com.service.point.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.point.controller.impl.PointAccumulationHistoryControllerImpl;
import com.service.point.dto.request.PointRewardOrderRequestDto;
import com.service.point.dto.response.PointAccumulationAdminPageResponseDto;
import com.service.point.dto.response.PointAccumulationMyPageResponseDto;
import com.service.point.exception.ClientNotFoundException;
import com.service.point.exception.PointPolicyNotFoundException;
import com.service.point.service.PointAccumulationHistoryService;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PointAccumulationHistoryControllerTest {

    @InjectMocks
    private PointAccumulationHistoryControllerImpl pointAccumulationHistoryController;

    @Mock
    private PointAccumulationHistoryService pointAccumulationHistoryService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(pointAccumulationHistoryController).build();
    }

    @Test
    void testRewardOrderPointSuccess() throws Exception {
        PointRewardOrderRequestDto requestDto = new PointRewardOrderRequestDto();
        // Populate requestDto with necessary data

        mockMvc.perform(post("/api/point/order")
                .header(HttpHeaders.AUTHORIZATION, "Bearer test-token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").value("success"));
    }

    @Test
    void testRewardOrderPointFailure() throws Exception {
        PointRewardOrderRequestDto requestDto = new PointRewardOrderRequestDto();
        // Populate requestDto with necessary data

        doThrow(new RuntimeException("Failure")).when(pointAccumulationHistoryService).orderPoint(any(HttpHeaders.class), any(PointRewardOrderRequestDto.class));

        mockMvc.perform(post("/api/point/order")
                .header(HttpHeaders.AUTHORIZATION, "Bearer test-token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$").value("fail"));
    }


    @Test
    void testDeleteUserPointSuccess() throws Exception {
        mockMvc.perform(delete("/api/point/adminPage/delete/{id}", 1)
                .header(HttpHeaders.AUTHORIZATION, "Bearer test-token"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").value("success"));
    }

    @Test
    void testDeleteUserPointFailure() throws Exception {
        doThrow(new RuntimeException("Failure")).when(pointAccumulationHistoryService).deletePoint(anyLong());

        mockMvc.perform(delete("/api/point/adminPage/delete/{id}", 1)
                .header(HttpHeaders.AUTHORIZATION, "Bearer test-token"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$").value("fail"));
    }


}
