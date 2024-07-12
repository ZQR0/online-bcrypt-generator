package com.yaroslav.hashgenerator.controller;

import com.yaroslav.hashgenerator.common.exception.BCryptProcessException;
import com.yaroslav.hashgenerator.dto.HashGenerationInputDto;
import com.yaroslav.hashgenerator.dto.HashVerifierDto;
import com.yaroslav.hashgenerator.service.BCryptGenerationService;
import com.yaroslav.hashgenerator.service.BCryptVerifierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/")
@RequiredArgsConstructor
public class BCryptController {

    private final BCryptGenerationService bCryptGenerationService;
    private final BCryptVerifierService bCryptVerifierService;


    @PostMapping(path = "get-hash")
    public ResponseEntity<?> generateHashEndpoint(@RequestBody HashGenerationInputDto dto) {
        Map<String, String> resultMap = new HashMap<>();
        try {
            String hash = this.bCryptGenerationService.generateHash(dto);
            resultMap.put("hash", hash);
            resultMap.put("error", "");
            return new ResponseEntity<>(
                    resultMap,
                    HttpStatus.OK
            );
        } catch (BCryptProcessException exception) {
            resultMap.put("hash", "");
            resultMap.put("error", exception.getMessage());
            return new ResponseEntity<>(
                    resultMap,
                    HttpStatus.BAD_REQUEST
            );
        }
    }


    @PostMapping(path = "verify-hash")
    public ResponseEntity<?> verifyHashEndpoint(@RequestBody HashVerifierDto dto) {
        Map<String, String> resultMap = new HashMap<>();
        try {
            String message = this.bCryptVerifierService.verifyBCryptHash(dto);
            resultMap.put("result", message);
            resultMap.put("error", "");
            return new ResponseEntity<>(
                    resultMap,
                    HttpStatus.OK
            );
        } catch (BCryptProcessException exception) {
            resultMap.put("result", "");
            resultMap.put("error", exception.getMessage());
            return new ResponseEntity<>(
                    resultMap,
                    HttpStatus.OK
            );
        }

    }

}
