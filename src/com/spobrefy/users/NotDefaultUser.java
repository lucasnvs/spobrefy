package com.spobrefy.users;

import java.text.ParseException;
import java.util.Scanner;
import com.spobrefy.Util;
import com.spobrefy.content.Playlist;

public class NotDefaultUser extends User {
    private String cpf;
    private String birthDate;

    protected NotDefaultUser(String nickname, String email, String password, String cpf, String birthDate) {
        super(nickname, email, password);
        this.cpf = cpf;
        this.birthDate = birthDate;
    }

    public String getCpf() {
        return cpf;
    }

    public int getAge() throws ParseException {
        return Util.findAge( birthDate, "dd/MM/yyyy");
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setCPF(String newCPF) {
        this.cpf = newCPF;
    }

    public void print() throws ParseException {
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
