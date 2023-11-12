package com.spobrefy.fdata;

import com.spobrefy.model.Music;

import java.util.ArrayList;

public class MusicFileHandler implements IFileHandler<Music> {
    private static final String fileName = "musics.csv";
    private static final MusicFileHandler instance = new MusicFileHandler();
    public static MusicFileHandler getInstance() {
        return instance;
    }
    @Override
    public ArrayList<Music> readData() {
        ArrayList<Music> musics = new ArrayList<>();
        String spliter = ";";

        for(String line : FileHandler.readFileData(fileName)) {
            String[] values = line.split(spliter);
            Music newMusic = new Music(Integer.parseInt(values[0]), values[1], Integer.parseInt(values[2]));
            musics.add(newMusic);
        }
        return musics;
    }
    @Override
    public void writeData(Music obj) {
        FileHandler.writeFileData(fileName, obj.toCsvString());
    }
    @Override
    public void updateData(Music obj) {
        FileHandler.updateFileData(fileName, obj);
    }

    @Override
    public void removeData(Music obj) {
        FileHandler.deleteFileData(fileName, obj);
    }
}
