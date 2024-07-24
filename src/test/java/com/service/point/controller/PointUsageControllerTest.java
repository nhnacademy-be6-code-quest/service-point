package com.service.point.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.service.point.controller.impl.PointUsageControllerImpl;
import com.service.point.dto.response.PointUsageAdminPageResponseDto;
import com.service.point.dto.response.PointUsageMyPageResponseDto;
import com.service.point.service.PointUsageHistoryService;
import com.service.point.service.impl.PointUsageHistoryServiceImpl;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@WebMvcTest(PointUsageController.class)
class PointUsageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PointUsageHistoryServiceImpl pointUsageHistoryService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @WithMockUser
    @Test
    void testUsedClientPoint() throws Exception {
        // Given
        HttpHeaders headers = new HttpHeaders();
        int page = 0;
        int size = 10;

        // Mock DTO 설정
        PointUsageMyPageResponseDto dto = new PointUsageMyPageResponseDto();
        dto.setPointUsageAmount(100L);  // 필요한 필드 값을 설정하세요
        Page<PointUsageMyPageResponseDto> pageResponse = new PageImpl<>(Collections.singletonList(dto));

        // Mock 서비스 호출
        when(pointUsageHistoryService.useClientPoint(headers, page, size)).thenReturn(pageResponse);

        // When & Then
        mockMvc.perform(get("/api/point/myPage/use")
                .headers(headers)
                .param("page", String.valueOf(page))
                .param("size", String.valueOf(size))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @WithMockUser
    @Test
    void testUsedUserPoint() throws Exception {
        // Given
        HttpHeaders headers = new HttpHeaders();
        int page = 0;
        int size = 10;
        PointUsageAdminPageResponseDto dto = new PointUsageAdminPageResponseDto();
        Page<PointUsageAdminPageResponseDto> pageResponse = new PageImpl<>(Collections.singletonList(dto));

        // Mock the service call
        when(pointUsageHistoryService.useUserPoint(page, size)).thenReturn(pageResponse);

        // When and Then
        mockMvc.perform(get("/api/point/adminPage/use")
                .headers(headers)
                .param("page", String.valueOf(page))
                .param("size", String.valueOf(size))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.content[0]").isNotEmpty());
    }
}
