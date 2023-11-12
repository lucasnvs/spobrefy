package com.spobrefy.fdata;

import com.spobrefy.model.Music;

import java.util.ArrayList;

public class MusicFileHandler implements IFileHandler<Music> {
    private static final String fileName = "musics.csv";
    @Override
    public ArrayList<Music> readData() {
        return null;
    }

    @Override
    public void writeData(Music obj) {
    // TODO: metodos
    }

    @Override
    public void updateData(Music obj) {
        FileHandler.updateFileData(fileName, obj);
    }

    @Override
    public void removeData(Music obj) {

    }
}
