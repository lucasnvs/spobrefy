package com.spobrefy.fdata;

import java.util.ArrayList;

public interface IFileHandler<T> {
    ArrayList<T> readFileData();
    void writeFileData(T obj);
    void updateFileData(T obj);
    void deleteFileData(T obj);
}
