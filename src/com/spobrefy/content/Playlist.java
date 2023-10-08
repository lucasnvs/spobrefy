package com.spobrefy.content;

import java.util.ArrayList;

import com.spobrefy.users.User;

public class Playlist {
    private final User owner;
    private final String name;
    private ArrayList<Music> musics;  

    public Playlist(User owner, String name) { // criar uma lista vazia independente do contrutor e add musicas pelo metodo
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

    public void print() {
        StringBuilder txt = new StringBuilder();
        for (Music music : musics) {
            txt.append("-- ").append(music.getName()).append(" --\n");
        }

        String text = String.format("Dono: %s\nNome da Playlist: %s\nMusicas: \n  %s", owner.getNickname(), name, txt.toString());
        System.out.println(text);
    }
}
