package com.dhentech.ziplink_api.infrastructure.web;

import com.dhentech.ziplink_api.BaseIntegrationTest;
import com.dhentech.ziplink_api.domain.Url;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class RedirectControllerTest extends BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Should redirect to original URL and return 302 Found")
    void shouldRedirectToOriginalUrl() throws Exception {
        String code = "google";
        String destination = "https://www.google.com";
        urlRepository.save(new Url(destination, code));

        mockMvc.perform(get("/" + code))
                .andExpect(status().isFound())
                .andExpect(header().string("Location", destination));
    }

    @Test
    @DisplayName("Should return 404 when short code does not exist")
    void shouldReturn404WhenNotFound() throws Exception {
        mockMvc.perform(get("/non-existent"))
                .andExpect(status().isNotFound());
    }
}
