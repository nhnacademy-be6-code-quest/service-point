package com.service.point.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.point.controller.impl.PointAccumulationHistoryControllerImpl;
import com.service.point.dto.request.PointRewardOrderRequestDto;
import com.service.point.exception.ClientNotFoundException;
import com.service.point.exception.PointPolicyNotFoundException;
import com.service.point.service.PointAccumulationHistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class PointAccumulationHistoryControllerTest {

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
