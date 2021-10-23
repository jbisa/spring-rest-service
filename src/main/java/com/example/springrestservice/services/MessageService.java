package com.example.springrestservice.services;

import com.example.springrestservice.records.Message;
import com.example.springrestservice.records.MessageResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

@Service
public class MessageService implements IService<MessageResponse> {

    private final WebClient webClient;

    public MessageService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(MessageServiceConstants.BASE_URL).build();
    }

    @Override
    public MessageResponse get() {
        try {
            List<Message> messages = this.webClient
                    .get()
                    .uri(MessageServiceConstants.POSTS_URL)
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

    @Override
    public MessageResponse getById(long id) {
        try {
            List<Message> messages = this.webClient
                    .get()
                    .uri(MessageServiceConstants.POSTS_BY_ID_URL, id)
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
