package com.spobrefy.dao;

import java.util.ArrayList;

import com.spobrefy.model.users.Artist;
import com.spobrefy.model.users.User;

public class ArtistsDAO implements IDao<Artist> {
    private static final ArtistsDAO instance = new ArtistsDAO();
    private final ArrayList<Artist> artistsList;

    private ArtistsDAO() {
        artistsList = new ArrayList<>();
        for( User user : UsersDAO.getInstance().findAll()) {
            if(user.getClass().getSimpleName().equals("Artist")) {
                artistsList.add((Artist) user);
            }
        }
    }

    public static ArtistsDAO getInstance() {
        return instance;
    }

    @Override
    public int getLastId() {
        return artistsList.get(artistsList.size() - 1).getId();
    }

    @Override
    public Artist findById(int id) {
        for (Artist artist : artistsList) {
            if (artist.getId() == id) {
                return artist;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Artist> findAll() {
        return artistsList;
    }

    @Override
    public void save(Artist artist) {
        artistsList.add(artist);
    }

    @Override
    public void update(Artist artist) {
        System.out.println("updating artist");
    }

    @Override
    public void delete(Artist artist) {
        artistsList.remove(artist);
    }

    @Override
    public void deleteById(int id) {
        delete(findById(id));
    }
}
