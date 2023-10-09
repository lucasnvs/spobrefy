package com.spobrefy.users;

import java.text.ParseException;
import java.util.Scanner;
import com.spobrefy.Util;
import com.spobrefy.content.Playlist;

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

    public void print() {
        String txtPlaylist = "";
        for (Playlist playlist : this.getPlaylist()) {
            txtPlaylist += "-- "+playlist.getName()+" --\n";
        }
        String text = String.format("| Id: %d\n| Nickname: %s\n| Senha: %s\n| Email: %s\n| Idade: %d anos\n| CPF: %s\n| Data de Aniversário: %s\n| Playlists do Usuário: \n %s", getId(), nickname, password, email, getAge(), cpf, birthDate, txtPlaylist);
        System.out.println(text);
    }

    public static <T extends NotDefaultUser> T create(Class<T> userType, Scanner scanner) {
        System.out.println("Qual seu nick?");
        String nick = scanner.nextLine();
        System.out.println("Qual seu email?");
        String email = scanner.nextLine();
        System.out.println("Qual sua senha?");
        String password = scanner.nextLine();
        System.out.println("Qual seu cpf?");
        String cpf = scanner.nextLine();
        System.out.println("Qual sua data de nascimento? dd/mm/aaaa");
        String birthDate = scanner.nextLine();

        try {
            return userType.getDeclaredConstructor(String.class, String.class, String.class, String.class, String.class)
                    .newInstance(nick, email, password, cpf, birthDate);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
