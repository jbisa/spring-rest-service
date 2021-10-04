package com.example.springrestservice.records;

public record Message(int id, int userId, String title, String body) {
    public Message() {
        this(0, 0, "", "");
    }
}
