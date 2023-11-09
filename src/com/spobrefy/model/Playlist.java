package com.spobrefy.model;

import java.util.ArrayList;

import com.spobrefy.model.users.Artist;
import com.spobrefy.model.users.User;

public class Playlist {
    private final Artist owner;
    private final String name;
    private final ArrayList<Music> musics;

    public Playlist(Artist owner, String name) { // criar uma lista vazia independente do contrutor e add musicas pelo metodo
        this.owner = owner;
        this.name = name;
        this.musics = new ArrayList<>();
    }

    public User getOwner() {
        return owner;
    }
    
    public String getName() {
        return name;
    }

    public int size() {
        return musics.size();
    }

    public ArrayList<Music> getMusics() {
        return musics;
    }

    public void addMusic(Music music) {
        musics.add(music);
    }

    @Override
    public String toString() {
        StringBuilder txt = new StringBuilder();
        for (Music music : musics) {
            txt.append("-- ").append(music.getName()).append(" --\n");
        }

        return String.format("Dono: %s\nNome da Playlist: %s\nMusicas: \n  %s", owner.getNickname(), name, txt);
    }
}
