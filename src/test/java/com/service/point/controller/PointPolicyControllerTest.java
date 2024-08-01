package com.service.point.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.point.controller.impl.PointPolicyControllerImpl;
import com.service.point.dto.request.ClientPointAccumulationResponseDto;
import com.service.point.dto.request.PointPolicyActiveRequestDto;
import com.service.point.dto.request.PointPolicyModifyRequestDto;
import com.service.point.dto.request.PointPolicyRegisterRequestDto;
import com.service.point.dto.response.PointPolicyAdminListResponseDto;
import com.service.point.service.PointPolicyService;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PointPolicyControllerImpl.class)
class PointPolicyControllerTest {

    @MockBean
    private PointPolicyService pointPolicyService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    @WithMockUser
    void testSavePointPolicy() throws Exception {
        PointPolicyRegisterRequestDto requestDto = new PointPolicyRegisterRequestDto();
        requestDto.setPointAccumulationType("TEST_TYPE");
        requestDto.setPointValue(100L);

        mockMvc.perform(post("/api/point/policy/register")
                .contentType(MediaType.APPLICATION_JSON)
                .with(csrf())
                .content(objectMapper.writeValueAsString(requestDto)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.pointAccumulationType").value("TEST_TYPE"))
            .andExpect(jsonPath("$.pointValue").value(100));
    }

    @Test
    @WithMockUser
    void testFindAllPointPolicy() throws Exception {
        PointPolicyAdminListResponseDto responseDto = new PointPolicyAdminListResponseDto();
        responseDto.setPointAccumulationType("TEST_TYPE");
        responseDto.setPointValue(100L);
        Page<PointPolicyAdminListResponseDto> pageResponse = new PageImpl<>(Collections.singletonList(responseDto));

        when(pointPolicyService.getAllPointPolicies(anyInt(), anyInt())).thenReturn(pageResponse);

        mockMvc.perform(get("/api/point/policy")
                .param("page", "0")
                .param("size", "10"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content[0].pointAccumulationType").value("TEST_TYPE"))
            .andExpect(jsonPath("$.content[0].pointValue").value(100));
    }

    @Test
    @WithMockUser
    void testModifyPointPolicy() throws Exception {
        PointPolicyModifyRequestDto requestDto = new PointPolicyModifyRequestDto();
        requestDto.setPointPolicyId(1L);
        requestDto.setPointValue(200L);

        mockMvc.perform(put("/api/point/policy/modify")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))

            .andExpect(status().isOk())
            .andExpect(jsonPath("$").value("success"));
    }

    @Test
    @WithMockUser
    void testModifyPointPolicy_Fail() throws Exception {
        PointPolicyModifyRequestDto requestDto = new PointPolicyModifyRequestDto();
        requestDto.setPointPolicyId(1L);
        requestDto.setPointValue(200L);

        doThrow(new RuntimeException()).when(pointPolicyService).modifyPointPolicy(any(PointPolicyModifyRequestDto.class));

        mockMvc.perform(put("/api/point/policy/modify")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$").value("fail"));
    }

    @Test
    @WithMockUser
    void testPointPolicyActive() throws Exception {
        PointPolicyActiveRequestDto requestDto = new PointPolicyActiveRequestDto();
        requestDto.setPointPolicyId(1L);
        requestDto.setPointAccumulationType("TEST_TYPE");

        mockMvc.perform(put("/api/point/policy/active")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").value("success"));
    }

    @Test
    @WithMockUser
    void testPointPolicyActive_Fail() throws Exception {
        PointPolicyActiveRequestDto requestDto = new PointPolicyActiveRequestDto();
        requestDto.setPointPolicyId(1L);
        requestDto.setPointAccumulationType("TEST_TYPE");

        doThrow(new RuntimeException()).when(pointPolicyService).pointPolicyActive(any(PointPolicyActiveRequestDto.class));

        mockMvc.perform(put("/api/point/policy/active")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$").value("fail"));
    }

    @Test
    @WithMockUser
    void testClientPointAccumulation() throws Exception {
        ClientPointAccumulationResponseDto responseDto = new ClientPointAccumulationResponseDto();
        responseDto.setPointValue(100L);

        when(pointPolicyService.findByAccumulation(1L)).thenReturn(responseDto);

        mockMvc.perform(get("/api/point/client/grade")
                .param("pointPolicyId", "1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.pointValue").value(100));
    }
}
