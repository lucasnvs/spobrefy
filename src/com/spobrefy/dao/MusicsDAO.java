package com.spobrefy.dao;

import java.util.ArrayList;

import com.spobrefy.fdata.MusicFileHandler;
import com.spobrefy.model.Music;

public class MusicsDAO implements IDao<Music> {
    private static final MusicsDAO instance = new MusicsDAO();

    public static MusicsDAO getInstance() {
        return instance;
    }

    @Override
    public int getLastId() {
        ArrayList<Music> list = this.findAll();
        if (list.size() == 0) {
            return 0;
        }
        return list.get(list.size() - 1).getId();
    }

    @Override
    public Music findById(int id) {
        for (Music music : findAll()) {
            if (music.getId() == id) {
                return music;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Music> findAll() {
        return MusicFileHandler.getInstance().readFileData();
    }

    @Override
    public void save(Music object) {
        if(object == null) return;
        MusicFileHandler.getInstance().writeFileData(object);
    }

    @Override
    public void update(Music object) {
        if(object == null) return;
        MusicFileHandler.getInstance().updateFileData(object);
    }

    @Override
    public void delete(Music object) {
        if(object == null) return;
        MusicFileHandler.getInstance().deleteFileData(object);
    }

    @Override
    public void deleteById(int id) {
        delete(findById(id));
    }
}
