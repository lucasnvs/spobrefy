package com.spobrefy.dao;

import java.util.ArrayList;
import java.util.List;

import com.spobrefy.fdata.MusicFileHandler;
import com.spobrefy.model.Music;
import com.spobrefy.model.users.User;

public class MusicsDAO implements IDao<Music> {
    private static final MusicsDAO instance = new MusicsDAO();
    private final ArrayList<Music> musicList;

    public MusicsDAO() {
        musicList = new ArrayList<>();
    }

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
        return MusicFileHandler.getInstance().readData();
    }

    @Override
    public void save(Music object) {
        if(object == null) return;
        MusicFileHandler.getInstance().writeData(object);
    }

    @Override
    public void update(Music object) {
        if(object == null) return;
        MusicFileHandler.getInstance().updateData(object);
    }

    @Override
    public void delete(Music object) {
        if(object == null) return;
        MusicFileHandler.getInstance().removeData(object);
    }

    @Override
    public void deleteById(int id) {
        delete(findById(id));
    }
}
