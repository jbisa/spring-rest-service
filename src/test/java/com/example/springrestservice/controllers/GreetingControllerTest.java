package com.example.springrestservice.controllers;

import com.example.springrestservice.records.Greeting;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreetingControllerTest {

    @Test
    void TestGreeting() {
        // Arrange
        GreetingController controller = new GreetingController();
        Greeting expected = new Greeting("Hello, Test!");

        // Act
        Greeting result = controller.greeting("Test");

        // Assert
        assertEquals(expected, result);
    }
}
