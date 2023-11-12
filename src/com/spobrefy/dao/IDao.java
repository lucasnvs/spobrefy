package com.spobrefy.dao;

import java.util.ArrayList;
import java.util.List;

public interface IDao<T> {

    int getLastId();
    T findById(int id);
    ArrayList<T> findAll();
    void save(T object);
    void update(T object);
    void delete(T object);
    void deleteById(int id);
}