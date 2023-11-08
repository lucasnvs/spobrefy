package com.spobrefy.dao;

import java.util.ArrayList;
import java.util.List;
import com.spobrefy.model.Music;

public class MusicsDAO implements IDao<Music> {
    private static final MusicsDAO instance = new MusicsDAO();
    private List<Music> musicList;

    public MusicsDAO() {
        musicList = new ArrayList<>();
        musicList.add(new Music("Novo Balanco", ArtistsDAO.getInstance().findById(3)));
        musicList.add(new Music("Deluxe", ArtistsDAO.getInstance().findById(3)));
        musicList.add(new Music("Conexões de Máfia", ArtistsDAO.getInstance().findById(2)));
        musicList.add(new Music("Bala Azul", ArtistsDAO.getInstance().findById(4)));
    }

    public static MusicsDAO getInstance() {
        return instance;
    }

    @Override
    public Music findById(int id) {
        for (Music music : musicList) {
            if (music.getId() == id) {
                return music;
            }
        }
        return null;
    }

    @Override
    public List<Music> findAll() {
        return musicList;
    }

    @Override
    public void save(Music object) {
        musicList.add(object);
    }

    @Override
    public void update(Music object) {
        
    }

    @Override
    public void delete(Music object) {
        musicList.remove(object);
    }

    @Override
    public void deleteById(int id) {
        delete(findById(id));
    }
}
