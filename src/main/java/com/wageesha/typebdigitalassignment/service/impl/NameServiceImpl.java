package com.wageesha.typebdigitalassignment.service.impl;

import com.wageesha.typebdigitalassignment.dto.GreetingResponseDTO;
import com.wageesha.typebdigitalassignment.exception.InvalidNameException;
import com.wageesha.typebdigitalassignment.service.NameService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NameServiceImpl implements NameService {

    @Override
    @SneakyThrows
    public GreetingResponseDTO greet(String name) {
        if (isValidName(name)) {
            return GreetingResponseDTO.builder().message("Hello " + formatName(name)).build();
        }

        log.warn("Invalid input received: {}", name);
        throw new InvalidNameException("Invalid Input");
    }

    private boolean isValidName(String name) {
        if (name == null || name.isBlank()) {
            return false;
        }

        char firstChar = Character.toUpperCase(name.charAt(0));
        return firstChar >= 'A' && firstChar <= 'M';
    }

    private String formatName(String name) {
        return Character.toUpperCase(name.charAt(0)) + name.substring(1).toLowerCase();
    }
}
