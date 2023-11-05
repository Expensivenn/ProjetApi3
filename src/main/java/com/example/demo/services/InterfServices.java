package com.example.demo.services;


import java.util.List;
public interface InterfServices<T> {
    public T create(T t) throws Exception;
    public T read(Integer id) throws Exception;
    public T update(T t) throws Exception;
    public void delete(T t) throws Exception;
    public List<T> all() throws Exception;
}

