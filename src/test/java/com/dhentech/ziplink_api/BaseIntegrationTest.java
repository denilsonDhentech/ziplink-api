package com.dhentech.ziplink_api;

import com.dhentech.ziplink_api.domain.repositories.UrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public abstract class BaseIntegrationTest {

    @Autowired
    protected UrlRepository urlRepository;

    @BeforeEach
    void clearDatabase() {
        urlRepository.deleteAll();
    }
}
