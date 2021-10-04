package com.example.springrestservice.controllers;

import com.example.springrestservice.records.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@RestController
public class MessageController {

    /**
     * The following URL is from a free fake API for testing purposes. Although the endpoint is called "posts", I'm
     * treating this resource as "messages" (data is mapped to my Message.java class).
     */
    private static final String POSTS_API_URL = "https://jsonplaceholder.typicode.com/posts/";

    @GetMapping("/messages")
    public List<Message> getMessages() {
        Message[] messages = null;

        try {
            RestTemplate restTemplate = new RestTemplate();

            messages = restTemplate
                    .getForEntity(POSTS_API_URL, Message[].class)
                    .getBody();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return Arrays.asList(messages);
    }

    @GetMapping("/messages/{id}")
    public Message getMessage(@PathVariable Long id) {
        Message message = new Message();

        try {
            RestTemplate restTemplate = new RestTemplate();

            message = restTemplate
                    .getForEntity(POSTS_API_URL + id, Message.class)
                    .getBody();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return message;
    }
}
