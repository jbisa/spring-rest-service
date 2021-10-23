package com.example.springrestservice.records;

public record Greeting(String content) {
    public String getContent() {
        return content;
    }
}
