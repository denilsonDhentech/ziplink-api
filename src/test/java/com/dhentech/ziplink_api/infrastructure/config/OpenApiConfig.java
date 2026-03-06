package com.dhentech.ziplink_api.infrastructure.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ZipLink API")
                        .version("1.0")
                        .description("REST API para encurtamento de URLs de alta performance com suporte a aliases personalizados.")
                        .contact(new Contact()
                                .name("DhenSouza")
                                .email("denilson_contato@outlook.com")
                                .url("https://github.com/DhenSouza"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Servidor de Desenvolvimento Local")
                ));
    }
}
