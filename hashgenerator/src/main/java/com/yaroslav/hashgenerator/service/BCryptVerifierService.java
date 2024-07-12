package com.yaroslav.hashgenerator.service;


import at.favre.lib.crypto.bcrypt.BCrypt;
import com.yaroslav.hashgenerator.common.exception.BCryptProcessException;
import com.yaroslav.hashgenerator.dto.HashVerifierDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class BCryptVerifierService {

    public String verifyBCryptHash(HashVerifierDto dto) throws BCryptProcessException {
        String plainText = dto.getPlainText();
        String hash = dto.getHash();

        if (plainText == null) {
            log.info("Plain text is null");
            throw new BCryptProcessException("Plain text for verifying is null");
        }

        if (hash == null) {
            log.info("Hash is null");
            throw new BCryptProcessException("Hash for verifying is null");
        }

        BCrypt.Result result = BCrypt.verifyer().verify(
                plainText.getBytes(StandardCharsets.UTF_8), hash.getBytes(StandardCharsets.UTF_8)
        );

        if (!result.validFormat) {
            log.info(String.format("Input hash %s is invalid", hash));
            throw new BCryptProcessException("Input hash is invalid");
        }

        if (!result.verified) {
            log.info(String.format("Input hash %s is not verified", hash));
            throw new BCryptProcessException("Input hash string is not verified");
        }

        log.info("VERIFIED");
        return "VERIFIED";
    }

}
