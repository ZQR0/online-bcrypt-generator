package com.yaroslav.hashgenerator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HashVerifierDto {

    @JsonProperty(namespace = "plainText")
    private String plainText;

    @JsonProperty(namespace = "hash")
    private String hash;
}
