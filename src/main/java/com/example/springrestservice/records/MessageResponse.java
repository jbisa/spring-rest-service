package com.example.springrestservice.records;

import java.util.ArrayList;
import java.util.List;

public record MessageResponse(List<Message> messages) {
    public MessageResponse() { this(new ArrayList<>()); }
}
