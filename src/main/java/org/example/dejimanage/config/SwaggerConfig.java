package org.example.dejimanage.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebMvc
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi firstGroup() {
        return GroupedOpenApi.builder()
                .group("CNCs")
                .pathsToMatch("/CNCs/**")
                .build();
    }
    @Bean
    public GroupedOpenApi secondGroup() {
        return GroupedOpenApi.builder()
                .group("energy")
                .pathsToMatch("/energy/**")
                .build();
    }
    @Bean
    public GroupedOpenApi thirdGroup() {
        return GroupedOpenApi.builder()
                .group("projectOrder")
                .pathsToMatch("/project/**")
                .build();
    }
}
