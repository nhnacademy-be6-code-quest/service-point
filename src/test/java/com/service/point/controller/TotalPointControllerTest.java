package com.service.point.controller;

import static org.mockito.Mockito.when;

import com.service.point.controller.impl.TotalPointControllerImpl;
import com.service.point.dto.response.TotalPointAmountResponseDto;
import com.service.point.service.impl.TotalPointAmountServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(TotalPointControllerImpl.class)
class TotalPointControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TotalPointAmountServiceImpl totalPointAmountService;


    public TotalPointControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser
    void testFindPoint() throws Exception {
        // Given
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-User-Id","1");
        TotalPointAmountResponseDto responseDto = new TotalPointAmountResponseDto();
        responseDto.setTotalPoint(10000L);

        // Mock the service call
        when(totalPointAmountService.findPoint(headers)).thenReturn(responseDto);

        // When and Then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/point")
                .headers(headers))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.totalPoint").value(10000L));
    }
}
