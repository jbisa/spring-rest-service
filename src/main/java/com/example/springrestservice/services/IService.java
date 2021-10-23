package com.example.springrestservice.services;

public interface IService<T> {
    public T get();
    public T getById(long id);
}
