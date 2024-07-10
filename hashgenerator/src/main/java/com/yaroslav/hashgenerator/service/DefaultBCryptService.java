package com.yaroslav.hashgenerator.service;

import com.yaroslav.hashgenerator.common.exception.BCryptProcessException;
import com.yaroslav.hashgenerator.dto.HashGenerationInputDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class DefaultBCryptService implements BCryptService<HashGenerationInputDto> {

    @Override
    public String generateHash(HashGenerationInputDto dto) throws BCryptProcessException {
        if (dto.getData() == null) {
            log.warn("Input data is empty");
            throw new BCryptProcessException("Input data is empty");
        }

        if (dto.getCostFactor() == null) {
            log.warn("Input cost factor is empty, default value is 10 now");
            throw new BCryptProcessException("Cost Factor in empty");
        }

        String bcryptHashString = BCrypt.hashpw(
                dto.getData().getBytes(StandardCharsets.UTF_8),
                BCrypt.gensalt(Integer.parseInt(dto.getCostFactor()))
        );


        log.info(bcryptHashString);
        return bcryptHashString;
    }
}
