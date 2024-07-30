package com.service.point.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.point.controller.impl.PointAccumulationControllerImpl;
import com.service.point.dto.request.PointRewardOrderRequestDto;
import com.service.point.service.PointAccumulationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class PointAccumulationHistoryControllerTest {

    @InjectMocks
    private PointAccumulationControllerImpl pointAccumulationHistoryController;

    @Mock
    private PointAccumulationService pointAccumulationService;

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

        doThrow(new RuntimeException("Failure")).when(pointAccumulationService).orderPoint(any(HttpHeaders.class), any(PointRewardOrderRequestDto.class));

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
        doThrow(new RuntimeException("Failure")).when(pointAccumulationService).deletePoint(anyLong());

        mockMvc.perform(delete("/api/point/adminPage/delete/{id}", 1)
                .header(HttpHeaders.AUTHORIZATION, "Bearer test-token"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$").value("fail"));
    }

}
