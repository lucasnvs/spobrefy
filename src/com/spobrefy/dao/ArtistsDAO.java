package com.spobrefy.dao;

import java.util.ArrayList;
import java.util.List;

import com.spobrefy.users.Artist;
import com.spobrefy.users.User;

public class ArtistsDAO implements IDao<Artist> {
    private static final ArtistsDAO instance = new ArtistsDAO();
    private List<Artist> artistsList;

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
    public Artist findById(int id) {
        for (Artist artist : artistsList) {
            if (artist.getId() == id) {
                return artist;
            }
        }
        return null;
    }

    @Override
    public List<Artist> findAll() {
        return artistsList;
    }

    @Override
    public void save(Artist artist) {
        artistsList.add(artist);
    }

    @Override
    public void update(Artist artist) {

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
