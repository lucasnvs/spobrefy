package com.spobrefy.model.users;

import java.util.ArrayList;
import java.util.Scanner;

import com.spobrefy.model.Playlist;

public class User {
    protected int idUser;
    private static int count = 0;
    protected String nickname;
    protected String email;
    protected String password;
    private final ArrayList<Playlist> playlists;

    public User(String nick, String email, String password) {
        this.nickname = nick;
        this.email = email;
        this.password = password;
        this.playlists = new ArrayList<>();
        idUser = ++count;
    }

    public User(int id, String nick, String email, String password) {
        this.nickname = nick;
        this.email = email;
        this.password = password;
        this.playlists = new ArrayList<>();
        idUser = id;
    }

    public ArrayList<Playlist> getPlaylist() {
        return playlists;
    }

    public void addPlaylist(Playlist playlist) {
        this.playlists.add(playlist);
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
    public int getId() {
        return idUser;
    }

    public void setNickname(String newNickname) {
        this.nickname = newNickname;
    }

    private void setPassword(String newPass) {
        this.password = newPass;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public boolean changePassword(String lastPass, String newPass) {
        if(lastPass.equals(this.password)) {
            setPassword(newPass);
            return true;
        }
        return false;
    }
    @Override
    public String toString() {
        String txtPlaylist = "";
        for (Playlist playlist : playlists) {
            txtPlaylist += "-- "+playlist.getName()+" --\n";
        }

        String text = String.format("| Id: %d\n| Nickname: %s\n| Senha: %s\n| Email: %s\n| Playlists do usuário: \n %s", idUser, nickname, password, email, txtPlaylist);
        return text;
    }

    public static User create(Scanner scanner) {
        System.out.println("Qual seu nick?");
        String nick = scanner.nextLine();
        System.out.println("Qual seu email?");
        String email = scanner.nextLine();
        System.out.println("Qual sua senha?");
        String password = scanner.nextLine();
        User usuario = new User(nick, email, password);
        return usuario;
    }

    public String toCsvString() {
        String[] partes = { Integer.toString(getId()), getNickname(), getEmail(), getPassword(), getClass().getSimpleName().toUpperCase(),null,null,null};
        String csvUserString = String.join(";",partes);;

        return csvUserString;
    }
}

