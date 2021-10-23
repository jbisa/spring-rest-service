package com.example.springrestservice.records;

import java.util.ArrayList;
import java.util.List;

public record MessageRequest(List<Message> messages) {
    public MessageRequest() { this(new ArrayList<>()); }
}
