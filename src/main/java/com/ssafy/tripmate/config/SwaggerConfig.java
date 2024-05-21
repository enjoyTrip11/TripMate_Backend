package com.ssafy.tripmate.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info().title("TripMate API 명세서").description(
                        "<h3>TripMate API Reference for Developers</h3>")
                .version("v1").contact(new io.swagger.v3.oas.models.info.Contact().name("yeonseo")
                        .email("yeonseo.jung0@gmail.com").url("http://google.com"));

        return new OpenAPI().components(new Components()).info(info);
    }

    @Bean
    public GroupedOpenApi booksApi() {
        return GroupedOpenApi.builder().group("board").pathsToMatch("/board/**").build();
    }

    @Bean
    public GroupedOpenApi repliesApi() {
        return GroupedOpenApi.builder().group("reply").pathsToMatch("/reply/**").build();
    }
}
