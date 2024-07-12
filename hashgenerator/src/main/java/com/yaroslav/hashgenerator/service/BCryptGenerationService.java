package com.yaroslav.hashgenerator.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.yaroslav.hashgenerator.common.exception.BCryptProcessException;
import com.yaroslav.hashgenerator.dto.HashGenerationInputDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class BCryptGenerationService {

    public String generateHash(HashGenerationInputDto dto) throws BCryptProcessException {
        if (dto.getData() == null) {
            log.warn("Input data is null");
            throw new BCryptProcessException("Input data is null");
        }

        if (dto.getCostFactor() == null) {
            log.warn("Input cost factor is null");
            throw new BCryptProcessException("Cost Factor in null");
        }

        if (dto.getData().isEmpty()) {
            log.warn("Input data cannot be empty");
            throw new BCryptProcessException("Input data cannot be empty");
        }

        if (dto.getCostFactor().isEmpty()) {
            log.warn("Cost factor is empty");
            throw new BCryptProcessException("Cost factor cannot be empty");
        }

        final int salt = Integer.parseInt(dto.getCostFactor());
        String bcryptHashString = BCrypt.withDefaults().hashToString(salt, dto.getData().toCharArray());

        log.info(bcryptHashString);
        return bcryptHashString;
    }

}
