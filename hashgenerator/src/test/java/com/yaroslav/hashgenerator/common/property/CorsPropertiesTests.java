package com.yaroslav.hashgenerator.common.property;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class CorsPropertiesTests {

    @Autowired
    private CorsProperties corsProperties;


    @Test
    void patternIsNotNull() {
        assertThat(this.corsProperties.getPattern()).isNotNull();
    }

    @Test
    void allowedOriginsIsNotNull() {
        assertThat(this.corsProperties.getAllowedOrigins()).isNotNull();
    }

    @Test
    void allowedHeadersIsNotNull() {
        assertThat(this.corsProperties.getAllowedHeaders()).isNotNull();
    }

    @Test
    void exposedHeadersIsNotNull() {
        assertThat(this.corsProperties.getExposedHeaders()).isNotNull();
    }

    @Test
    void allowedMethodsIsNotNull() {
        assertThat(this.corsProperties.getAllowedMethods()).isNotNull();
    }

    @Test
    void isAllowCredentials() {
        assertThat(this.corsProperties.isAllowCredentials()).isTrue();
    }
}
