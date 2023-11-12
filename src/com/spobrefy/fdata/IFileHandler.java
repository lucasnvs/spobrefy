package com.spobrefy.fdata;

import java.util.ArrayList;

public interface IFileHandler<T> {
    ArrayList<T> readData();
    void writeData(T obj);
    void updateData(T obj);
    void removeData(T obj);
}
