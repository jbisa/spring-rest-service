package com.example.springrestservice.integrationtests;

import com.example.springrestservice.controllers.MessageController;
import com.example.springrestservice.records.Message;
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
@WebMvcTest(MessageController.class)
public class MessageControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getMessage() throws Exception {
        // Arrange
        RequestBuilder request = MockMvcRequestBuilders.get("/messages/1");
        Message expected = new Message(
                1,
                1,
                "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                "quia et suscipit suscipit recusandae consequuntur expedita et cum reprehenderit molestiae ut ut quas totam nostrum rerum est autem sunt rem eveniet architecto");

        // Act
        String json = mvc.perform(request).andReturn().getResponse().getContentAsString();

        Message result = new ObjectMapper().readValue(json, Message.class);

        // Assert
        assertEquals(expected.id(), result.id());
        assertEquals(expected.userId(), result.userId());
        assertEquals(expected.title(), result.title());
    }
}
