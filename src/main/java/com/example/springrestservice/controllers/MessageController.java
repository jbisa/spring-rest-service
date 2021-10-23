package com.example.springrestservice.controllers;

import com.example.springrestservice.records.MessageResponse;
import com.example.springrestservice.services.MessageService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/messages")
    @ApiOperation("Retrieves all messages.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved all messages.") })
    public MessageResponse getMessages() {
        return this.messageService.getMessages();
    }

    @GetMapping("/messages/{id}")
    @ApiOperation("Retrieves a message based on an Id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved message by Id."),
            @ApiResponse(code = 404, message = "No message found for the given Id.")
    })
    public ResponseEntity<MessageResponse> getMessageById(@PathVariable Long id) {
        MessageResponse result = this.messageService.getMessageById(id);

        if (result.messages().size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No message found for Id: " + id);
        }
    }
}
