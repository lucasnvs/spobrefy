package com.spobrefy.model;

import java.util.Scanner;

import com.spobrefy.dao.ArtistsDAO;
import com.spobrefy.model.users.Artist;

public class Music {
    private static int count = 0;
    private final int idMusic;
    private final String name;
    private final Artist author;

    public Music(String name, Artist author) {
        this.name = name;
        this.author = author;
        idMusic = ++count;
        author.getAuthoredPlaylist().addMusic(this);
    }

    public int getId() {
        return idMusic;
    }

    public String getName() {
        return name;
    }

    public Artist getAuthor() {
        return author;
    }

    private static Artist findAuthor(String authorName) {
        for (Artist artist : ArtistsDAO.getInstance().findAll()) {
            if (artist.getNickname().equals(authorName)) {
                return artist;
            }
        }
        return null;
    }

    public static Music create(Scanner scanner, int idAuthor) {
        // nao consegui consertar o bug // um tempo depois... qual bug? nao sei
        System.out.println("Qual o título?");
        String title = scanner.nextLine();

        Artist author = ArtistsDAO.getInstance().findById(idAuthor);
        return new Music(title, author);
    }
}
