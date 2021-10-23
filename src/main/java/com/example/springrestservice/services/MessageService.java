package com.example.springrestservice.services;

import com.example.springrestservice.records.Message;
import com.example.springrestservice.records.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    /**
     * The following URL is from a free fake API for testing purposes. Although the endpoint is called "posts", I'm
     * treating this resource as "messages" (data is mapped to my Message.java class).
     */
    private static final String POSTS_API_URL = "https://jsonplaceholder.typicode.com/posts/";

    public MessageResponse getMessages() {
        try {
            List<Message> messages = webClientBuilder
                    .build()
                    .get()
                    .uri(POSTS_API_URL)
                    .retrieve()
                    .bodyToFlux(Message.class)
                    .collectList()
                    .block();

            return new MessageResponse(messages);
        } catch (Exception ex) {
            System.out.println("Exception when getting messages");
        }

        return new MessageResponse();
    }

    public MessageResponse getMessageById(Long id) {
        try {
            List<Message> messages = webClientBuilder
                    .build()
                    .get()
                    .uri(POSTS_API_URL + id)
                    .retrieve()
                    .bodyToFlux(Message.class)
                    .collectList()
                    .block();

            return new MessageResponse(messages);
        } catch (Exception ex) {
            System.out.println("Exception when getting message by Id: " + id);
        }

        return new MessageResponse();
    }
}
