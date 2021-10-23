package com.example.springrestservice.services;

import com.example.springrestservice.records.Message;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Service
public class MessageService implements IService<Message> {

    private final WebClient webClient;

    public MessageService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(MessageServiceConstants.BASE_URL).build();
    }

    @Override
    public List<Message> get() {
        try {
            return this.webClient
                    .get()
                    .uri(MessageServiceConstants.POSTS_URL)
                    .retrieve()
                    .bodyToFlux(Message.class)
                    .collectList()
                    .block();
        } catch (Exception ex) {
            System.out.println("Exception when getting messages");
        }

        return Collections.emptyList();
    }

    @Override
    public List<Message> getById(long id) {
        try {
            return this.webClient
                    .get()
                    .uri(MessageServiceConstants.POSTS_BY_ID_URL, id)
                    .retrieve()
                    .bodyToFlux(Message.class)
                    .collectList()
                    .block();
        } catch (Exception ex) {
            System.out.println("Exception when getting message by Id: " + id);
        }

        return Collections.emptyList();
    }

    @Override
    public Message create(Message message) {
        try {
            return this.webClient
                    .post()
                    .uri(MessageServiceConstants.POSTS_URL)
                    .body(Mono.just(message), Message.class)
                    .retrieve()
                    .bodyToMono(Message.class)
                    .block();
        } catch (Exception ex) {
            System.out.println("Exception creating a new message");
        }

        return null;
    }
}
