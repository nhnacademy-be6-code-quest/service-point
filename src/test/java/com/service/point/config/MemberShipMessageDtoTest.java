package com.service.point.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class MemberShipMessageDtoTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
     void testNoArgsConstructor() {
        MemberShipMessageDto dto = new MemberShipMessageDto();
        assertThat(dto).isNotNull();
    }

    @Test
     void testGetter() {
        MemberShipMessageDto dto = new MemberShipMessageDto();
        dto.setClientId(123L);
        assertThat(dto.getClientId()).isEqualTo(123L);
    }

    @Test
     void testSerialization() throws IOException {
        MemberShipMessageDto dto = new MemberShipMessageDto();
        dto.setClientId(123L);

        String json = objectMapper.writeValueAsString(dto);
        assertThat(json).isNotNull().contains("\"clientId\":123");
    }

    @Test
     void testDeserialization() throws IOException {
        String json = "{\"clientId\":123}";

        MemberShipMessageDto dto = objectMapper.readValue(json, MemberShipMessageDto.class);
        assertThat(dto.getClientId()).isEqualTo(123L).isNotNull();
    }
}
