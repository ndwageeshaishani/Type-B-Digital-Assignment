package com.wageesha.typebdigitalassignment.controller;

import com.wageesha.typebdigitalassignment.dto.GreetingResponseDTO;
import com.wageesha.typebdigitalassignment.service.NameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.wageesha.typebdigitalassignment.common.ConstantData.PATH_HELLO_WORLD;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class NameController {

    private final NameService nameService;

    @GetMapping(PATH_HELLO_WORLD)
    public ResponseEntity<GreetingResponseDTO> greet(@RequestParam(required = false) String name) {

        GreetingResponseDTO response = nameService.greet(name);
        log.info("Greeting is successful.");
        return ResponseEntity.ok(response);
    }
}
