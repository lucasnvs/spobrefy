package com.spobrefy.model;

import java.util.Scanner;

import com.spobrefy.dao.ArtistsDAO;
import com.spobrefy.dao.MusicsDAO;
import com.spobrefy.model.users.Artist;
import com.spobrefy.shared.IAbleToSave;

public class Music implements IAbleToSave {
    private final int idMusic;
    private final String name;
    private final Artist author;

    public Music(String name, Artist author) {
        this.name = name;
        this.author = author;
        this.idMusic = MusicsDAO.getInstance().getLastId() + 1;
        author.getAuthoredPlaylist().addMusic(this);
    }

    public Music(int idMusic, String name, int idAuthor) {
        this.name = name;
        this.author = ArtistsDAO.getInstance().findById(idAuthor);
        this.idMusic = idMusic;
        author.getAuthoredPlaylist().addMusic(this);
    }

    @Override
    public String toCsvString() {
        String[] partes = { Integer.toString(getId()), getName(), String.valueOf(getAuthor().getId())};

        return String.join(";",partes);
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

    public static Music create(Scanner scanner, int idAuthor) {
        // nao consegui consertar o bug // um tempo depois... qual bug? nao sei
        System.out.println("Qual o título?");
        String title = scanner.nextLine();

        Artist author = ArtistsDAO.getInstance().findById(idAuthor);
        return new Music(title, author);
    }
}
