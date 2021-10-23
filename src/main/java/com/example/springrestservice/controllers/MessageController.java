package com.example.springrestservice.controllers;

import com.example.springrestservice.records.Message;
import com.example.springrestservice.records.MessageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;

@RestController
public class MessageController {

    /**
     * The following URL is from a free fake API for testing purposes. Although the endpoint is called "posts", I'm
     * treating this resource as "messages" (data is mapped to my Message.java class).
     */
    private static final String POSTS_API_URL = "https://jsonplaceholder.typicode.com/posts/";

    @GetMapping("/messages")
    public MessageResponse getMessages() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            Message[] messages = restTemplate.getForObject(POSTS_API_URL, Message[].class);

            return new MessageResponse(Arrays.asList(messages));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new MessageResponse();
    }

    @GetMapping("/messages/{id}")
    public MessageResponse getMessage(@PathVariable Long id) {
        Message message = new Message();

        try {
            RestTemplate restTemplate = new RestTemplate();
            message = restTemplate.getForObject(POSTS_API_URL + id, Message.class);

            return new MessageResponse(Arrays.asList(message));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new MessageResponse();
    }
}
