package com.example.springrestservice.services;

import java.util.List;

public interface IService<T> {
    public List<T> get();
    public List<T> getById(long id);
    public T create(T resource);
}
