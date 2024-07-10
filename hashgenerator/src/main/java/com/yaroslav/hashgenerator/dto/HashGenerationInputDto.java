package com.yaroslav.hashgenerator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class HashGenerationInputDto extends AbstractInputDto {

    @JsonProperty(namespace = "data", required = true)
    private String data;

    @JsonProperty(namespace = "costFactor", required = true)
    private String costFactor;
}
