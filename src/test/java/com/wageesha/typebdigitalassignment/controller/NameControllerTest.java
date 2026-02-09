package com.wageesha.typebdigitalassignment.controller;

import com.wageesha.typebdigitalassignment.dto.GreetingResponseDTO;
import com.wageesha.typebdigitalassignment.exception.InvalidNameException;
import com.wageesha.typebdigitalassignment.service.NameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.wageesha.typebdigitalassignment.common.ConstantData.PATH_HELLO_WORLD;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NameController.class)
class NameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NameService nameService;

    // Starts with "a"
    @Test
    void validNameAdam() throws Exception {
        when(nameService.greet("adam")).thenReturn(GreetingResponseDTO.builder().message("Hello Adam").build());

        mockMvc.perform(get(PATH_HELLO_WORLD)
                .param("name", "adam"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello Adam"));
    }

    // Starts with "M"
    @Test
    void validNameMichael() throws Exception {
        when(nameService.greet("Michael")).thenReturn(GreetingResponseDTO.builder().message("Hello Michael").build());

        mockMvc.perform(get(PATH_HELLO_WORLD)
                .param("name", "Michael"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello Michael"));
    }

    // Starts with "N"
    @Test
    void invalidNameNick() throws Exception {
        when(nameService.greet("Nick")).thenThrow(new InvalidNameException("Invalid Input"));

        mockMvc.perform(get(PATH_HELLO_WORLD)
                .param("name", "Nick"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Input"));
    }

    // No parameter passed
    @Test
    void missingNameParam() throws Exception {
        when(nameService.greet(null)).thenThrow(new InvalidNameException("Invalid Input"));

        mockMvc.perform(get(PATH_HELLO_WORLD))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Input"));
    }

    // Starts with a number
    @Test
    void numericName() throws Exception {
        when(nameService.greet("1alex")).thenThrow(new InvalidNameException("Invalid Input"));

        mockMvc.perform(get(PATH_HELLO_WORLD).param("name", "1alex"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Input"));
    }

    // Starts with a URL-safe special character
    @Test
    void specialCharName() throws Exception {
        when(nameService.greet("@adam")).thenThrow(new InvalidNameException("Invalid Input"));

        mockMvc.perform(get(PATH_HELLO_WORLD).param("name", "@adam"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Input"));
    }

    // Name empty string
    @Test
    void emptyName() throws Exception {
        when(nameService.greet("")).thenThrow(new InvalidNameException("Invalid Input"));

        mockMvc.perform(get(PATH_HELLO_WORLD).param("name", ""))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Input"));
    }

    // Name starts with a white space
    @Test
    void whitespaceName() throws Exception {
        when(nameService.greet(" Alice")).thenThrow(new InvalidNameException("Invalid Input"));

        mockMvc.perform(get(PATH_HELLO_WORLD).param("name", " Alice"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Input"));
    }

    // Name white space
    @Test
    void fullWhitespaceName() throws Exception {
        when(nameService.greet("   ")).thenThrow(new InvalidNameException("Invalid Input"));

        mockMvc.perform(get(PATH_HELLO_WORLD).param("name", "   "))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Input"));
    }
}
