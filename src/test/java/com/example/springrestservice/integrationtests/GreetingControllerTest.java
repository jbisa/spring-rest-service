package com.example.springrestservice.integrationtests;

import com.example.springrestservice.records.Greeting;
import com.example.springrestservice.controllers.GreetingController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GreetingController.class)
class GreetingControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void greetingWithoutRequestParam() throws Exception {
        // Arrange
        RequestBuilder request = MockMvcRequestBuilders.get("/greeting");
        Greeting expected = new Greeting("Hello, World!");

        // Act
        String json = mvc
                .perform(request)
                .andReturn()
                .getResponse()
                .getContentAsString();

        Greeting result = new ObjectMapper().readValue(json, Greeting.class);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void greetingWithRequestParam() throws Exception {
        // Arrange
        RequestBuilder request = MockMvcRequestBuilders.get("/greeting?name=Niam");
        Greeting expected = new Greeting("Hello, Niam!");

        // Act
        String json = mvc
                .perform(request)
                .andReturn()
                .getResponse()
                .getContentAsString();

        Greeting result = new ObjectMapper().readValue(json, Greeting.class);

        // Assert
        assertEquals(expected, result);
    }
}