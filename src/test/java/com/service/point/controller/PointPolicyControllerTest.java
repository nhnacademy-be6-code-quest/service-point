//package com.service.point.controller;
//
//import static org.hamcrest.Matchers.is;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import com.service.point.dto.request.ClientPointAccumulationResponseDto;
//import com.service.point.dto.request.PointPolicyActiveRequestDto;
//import com.service.point.dto.request.PointPolicyModifyRequestDto;
//import com.service.point.dto.request.PointPolicyRegisterRequestDto;
//import com.service.point.dto.response.PointPolicyAdminListResponseDto;
//import com.service.point.service.PointPolicyService;
//import java.util.Collections;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//@WebMvcTest(PointPolicyControllerImplTest.class)
//class PointPolicyControllerImplTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private PointPolicyService pointPolicyService;
//
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testSavePointPolicy() throws Exception {
//        // Given
//        PointPolicyRegisterRequestDto requestDto = new PointPolicyRegisterRequestDto();
//        requestDto.setPointValue(100L);
//
//        // When & Then
//        mockMvc.perform(post("/api/point/policy/register")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"policyName\": \"Sample Policy\"}"))
//            .andExpect(status().isCreated())
//            .andExpect(jsonPath("$.policyName", is("Sample Policy")));
//    }
//
//    @Test
//    @WithMockUser
//    void testFindAllPointPolicy() throws Exception {
//        // Given
//        PointPolicyAdminListResponseDto dto = new PointPolicyAdminListResponseDto();
//        dto.setPointValue(500L);
//        Page<PointPolicyAdminListResponseDto> pageResponse = new PageImpl<>(
//            Collections.singletonList(dto));
//
//        // Mock the service call
//        when(pointPolicyService.getAllPointPolicies(0, 10)).thenReturn(pageResponse);
//
//        // When & Then
//        mockMvc.perform(get("/api/point/policy")
//                .param("page", "0")
//                .param("size", "10")
//                .contentType(MediaType.APPLICATION_JSON))
//            .andExpect(status().isOk());
//    }
//
//    @Test
//    void testModifyPointPolicy() throws Exception {
//        // Given
//        PointPolicyModifyRequestDto requestDto = new PointPolicyModifyRequestDto();
//
//        // Mock the service call
//
//        // When & Then
//        mockMvc.perform(put("/api/point/policy/modify")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"policyName\": \"Updated Policy\"}"))
//            .andExpect(status().isOk())
//            .andExpect(jsonPath("$", is("success")));
//    }
//
//    @Test
//    void testPointPolicyActive() throws Exception {
//        // Given
//        PointPolicyActiveRequestDto requestDto = new PointPolicyActiveRequestDto();
//
//        // Mock the service call
//
//        // When & Then
//        mockMvc.perform(put("/api/point/policy/active")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"active\": true}"))
//            .andExpect(status().isOk())
//            .andExpect(jsonPath("$", is("success")));
//    }
//
//    @Test
//    void testClientPointAccumulation() throws Exception {
//        // Given
//        ClientPointAccumulationResponseDto responseDto = new ClientPointAccumulationResponseDto();
//        responseDto.setPointValue(100L);
//
//        // Mock the service call
//        when(pointPolicyService.findByAccumulation(1L)).thenReturn(responseDto);
//
//        // When & Then
//        mockMvc.perform(get("/api/point/client/grade")
//                .param("pointPolicyId", "1")
//                .contentType(MediaType.APPLICATION_JSON))
//            .andExpect(status().isOk())
//            .andExpect(jsonPath("$.accumulatedPoints", is(100)));
//    }
//}