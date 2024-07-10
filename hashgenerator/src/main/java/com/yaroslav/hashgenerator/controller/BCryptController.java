package com.yaroslav.hashgenerator.controller;

import com.yaroslav.hashgenerator.common.exception.BCryptProcessException;
import com.yaroslav.hashgenerator.dto.HashGenerationInputDto;
import com.yaroslav.hashgenerator.service.BCryptService;
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

    private final BCryptService<HashGenerationInputDto> bCryptService;


    @PostMapping(path = "get-hash")
    public ResponseEntity<?> generateHashEndpoint(@RequestBody HashGenerationInputDto dto) {
        Map<String, String> likeJsonMap = new HashMap<>();
        try {
            String hash = this.bCryptService.generateHash(dto);
            likeJsonMap.put("hash", hash);
            return new ResponseEntity<>(
                    likeJsonMap,
                    HttpStatus.OK
            );
        } catch (BCryptProcessException exception) {
            likeJsonMap.put("message", exception.getMessage());
            return new ResponseEntity<>(
                    likeJsonMap,
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping(path = "get-debug")
    public String getDebug() {
        return "debug";
    }

}
