package com.yaroslav.hashgenerator.common.property;


import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.mvc.cors.configs")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CorsProperties {
    private String pattern;
    private String allowedOrigins;
    private String allowedHeaders;
    private String exposedHeaders;
    private String allowedMethods;
    private boolean allowCredentials;
}
