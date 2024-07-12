package com.yaroslav.hashgenerator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HashGenerationInputDto {

    @JsonProperty(namespace = "data", required = true)
    private String data;

    @JsonProperty(namespace = "costFactor", required = true)
    private String costFactor;
}
