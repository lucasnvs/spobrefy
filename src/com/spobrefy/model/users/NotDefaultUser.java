package com.spobrefy.model.users;

import java.text.ParseException;

import com.spobrefy.Util;
import com.spobrefy.model.Playlist;

public abstract class NotDefaultUser extends User {
    private String cpf;
    private final String birthDate;

    protected NotDefaultUser(String nickname, String email, String password, String cpf, String birthDate) {
        super(nickname, email, password);
        this.cpf = cpf;
        this.birthDate = birthDate;
    }
    protected NotDefaultUser(int id, String nickname, String email, String password, String cpf, String birthDate) {
        super(id, nickname, email, password);
        this.cpf = cpf;
        this.birthDate = birthDate;
    }

    public String getCPF() {
        return cpf;
    }

    public int getAge() {
        try {
            return Util.findAge( birthDate, "dd/MM/yyyy");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setCPF(String newCPF) {
        this.cpf = newCPF;
    }

    public String toString() {
        StringBuilder txtPlaylist = new StringBuilder();
        for (Playlist playlist : this.getPlaylist()) {
            txtPlaylist.append("-- ").append(playlist.getName()).append(" --\n");
        }
        return String.format("| Id: %d\n| Nickname: %s\n| Senha: %s\n| Email: %s\n| Idade: %d anos\n| CPF: %s\n| Data de Aniversário: %s\n| Playlists do Usuário: \n %s", getId(), nickname, password, email, getAge(), cpf, birthDate, txtPlaylist);
    }

    @Override
    public String toCsvString() {
        String[] partes = { Integer.toString(getId()), getNickname(), getEmail(), getPassword(), getClass().getSimpleName().toUpperCase(),getCPF(),getBirthDate(),null};


        return String.join(";",partes);
    }
}
