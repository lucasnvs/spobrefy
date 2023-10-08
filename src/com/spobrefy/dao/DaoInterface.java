package com.spobrefy.dao;

import java.util.List;

public interface DaoInterface<T> {
    T findById(int id);
    List<T> findAll();
    void save(T object);
    void update(T object);
    void delete(T object);
}