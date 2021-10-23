package com.example.springrestservice.controllers;

import com.example.springrestservice.records.Message;
import com.example.springrestservice.records.MessageRequest;
import com.example.springrestservice.records.MessageResponse;
import com.example.springrestservice.services.IService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MessageController {

    private final IService<Message> messageService;

    public MessageController(IService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/messages")
    @ApiOperation("Retrieves all messages.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved all messages.") })
    public ResponseEntity<MessageResponse> getMessages() {
        List<Message> result = this.messageService.get();

        if (result.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(result));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No messages found");
        }
    }

    @GetMapping("/messages/{id}")
    @ApiOperation("Retrieves a message based on an Id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved message by Id."),
            @ApiResponse(code = 404, message = "No message found for the given Id.")
    })
    public ResponseEntity<MessageResponse> getMessageById(@PathVariable long id) {
        List<Message> result = this.messageService.getById(id);

        if (result.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(result));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No message found for Id: " + id);
        }
    }

    @PostMapping("/messages")
    @ApiOperation("Create new message(s).")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Succesfully created new message(s)."),
            @ApiResponse(code = 500, message = "Error creating new message(s).")
    })
    public ResponseEntity<MessageResponse> createMessage(@RequestBody MessageRequest messageRequest) {
        List<Message> result = messageRequest.messages().stream().map(message -> this.messageService.create(message)).collect(Collectors.toList());

        if (result.stream().allMatch(r -> r != null)) {
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(result));
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating new message(s).");
        }
    }
}
