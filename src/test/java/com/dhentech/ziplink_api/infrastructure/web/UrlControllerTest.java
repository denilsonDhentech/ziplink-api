package com.dhentech.ziplink_api.infrastructure.web;

import com.dhentech.ziplink_api.BaseIntegrationTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
class UrlControllerTest extends BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Should shorten URL and return 201 Created")
    void shouldShortenUrlSuccessfully() throws Exception {
        var requestBody = Map.of(
                "originalUrl", "https://github.com/DhenSouza",
                "alias", "my-github"
        );

        mockMvc.perform(post("/api/v1/urls/shorten")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.shortCode").value("my-github"))
                .andExpect(jsonPath("$.shortenedUrl").exists());
    }

    @Test
    @DisplayName("Should return 400 when URL is invalid")
    void shouldReturn400ForInvalidUrl() throws Exception {
        var requestBody = Map.of("originalUrl", "");

        mockMvc.perform(post("/api/v1/urls/shorten")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isBadRequest());
    }
}