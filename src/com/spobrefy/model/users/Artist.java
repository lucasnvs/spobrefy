package com.spobrefy.model.users;

import java.util.Scanner;

import com.spobrefy.model.Playlist;

public class Artist extends NotDefaultUser{
    private final Playlist authoredPlaylist = new Playlist(this, "Playlist Autoral");

    public Artist(String nickname, String email, String password, String cpf, String birthDate) {
        super(nickname, email, password, cpf, birthDate);
        addPlaylist(authoredPlaylist);
    }

    public Artist(int id, String nickname, String email, String password, String cpf, String birthDate) {
        super(id, nickname, email, password, cpf, birthDate);
        addPlaylist(authoredPlaylist);
    }

    public static Artist create(Scanner scanner) {
        return NotDefaultUser.create(Artist.class, scanner);
    }

    public Playlist getAuthoredPlaylist() {
        return authoredPlaylist;
    }
}
