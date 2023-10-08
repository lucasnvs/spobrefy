package com.spobrefy.content;

import java.util.List;
import java.util.Scanner;

import com.spobrefy.users.Artist;

public class Music {
    private static int count = 0;
    private final int idMusic;
    private final String name;
    private final Artist author;

    // necessario rever este metodo e o funcionamento junto com os parametros do metodo create
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

    private static Artist findAuthor(List<Artist> list, String authorName) {
        for (Artist artist : list) {
            if (artist.getNickname().equals(authorName)) {
                return artist;
            }
        }
        return null;
    }

    public static Music create(List<Artist> list, Scanner scanner) {
        // nao consegui consertar o bug
        System.out.println("Qual o título?");
        String title = scanner.nextLine();
        System.out.println("Qual o nome do autor?");
        String authorName = scanner.nextLine();

        Artist author = findAuthor(list, authorName);
        return new Music(title, author);
    }
}
