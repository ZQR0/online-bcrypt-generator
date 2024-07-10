package com.yaroslav.hashgenerator.service;

import com.yaroslav.hashgenerator.common.exception.BCryptProcessException;
import com.yaroslav.hashgenerator.dto.AbstractInputDto;

public interface BCryptService<T extends AbstractInputDto> {
    String generateHash(T dto) throws BCryptProcessException;
}
