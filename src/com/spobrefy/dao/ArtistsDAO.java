package com.spobrefy.dao;

import java.util.ArrayList;
import java.util.List;

import com.spobrefy.users.Artist;

public class ArtistsDAO implements DaoInterface<Artist> {
    private static final ArtistsDAO instance = new ArtistsDAO();
    private List<Artist> artistsList;

    private ArtistsDAO() {
        artistsList = new ArrayList<>();
        artistsList.add(new Artist("Matue", "matue30@email.com", "30praUm", "9822554812", "22/03/1998"));
        artistsList.add(new Artist("Veigh", "veighbaby@email.com", "tururum", "2349281022", "16/08/2000"));      
        artistsList.add(new Artist("Teto", "tetinho@email.com", "plaktudum", "234233581022", "20/11/2002"));
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
}
