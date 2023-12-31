package com.spobrefy.fdata;

import com.spobrefy.model.Music;

import java.util.ArrayList;

public class MusicFileHandler extends FileHandler implements IFileHandler<Music> {
    private static final String fileName = "musics.csv";
    private static final MusicFileHandler instance = new MusicFileHandler();
    public static MusicFileHandler getInstance() {
        return instance;
    }
    @Override
    public ArrayList<Music> readFileData() {
        ArrayList<Music> musics = new ArrayList<>();
        String spliter = ";";

        for(String line : super.readFileData(fileName)) {
            String[] values = line.split(spliter);
            Music newMusic = new Music(Integer.parseInt(values[0]), values[1], Integer.parseInt(values[2]));
            musics.add(newMusic);
        }
        return musics;
    }
    @Override
    public void writeFileData(Music obj) {
        super.writeFileData(fileName, obj.toCsvString());
    }
    @Override
    public void updateFileData(Music obj) {
        super.updateFileData(fileName, obj);
    }

    @Override
    public void deleteFileData(Music obj) {
        super.deleteFileData(fileName, obj);
    }
}
