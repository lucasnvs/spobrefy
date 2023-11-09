package com.spobrefy.model.users;

import java.util.Scanner;

public class Admin extends NotDefaultUser {
    private String spobrefyToken;
    
    // token removido do construtor por incompatibilidade com o metodo create do NotDefaultUser e adicionado no método create local desta classe
    // depois será necessário uma verificação para criar o admin se seu token for válido.
    private Admin(String nickname, String email, String password, String cpf, String birthDate) {
        super(nickname, email, password, cpf, birthDate);
    }

    public Admin(String nickname, String email, String password, String cpf, String birthDate, String token) {
        super(nickname, email, password, cpf, birthDate);
        this.spobrefyToken = token;
    }
    public Admin(int id, String nickname, String email, String password, String cpf, String birthDate, String token) {
        super(id, nickname, email, password, cpf, birthDate);
        this.spobrefyToken = token;
    }

    public String getSpobrefyToken() {
        return spobrefyToken;
    }

    private void setSpobrefyToken(String token) {
        this.spobrefyToken = token;
    }

    public static Admin create(Scanner scanner) {
        Admin admin = NotDefaultUser.create(Admin.class, scanner);
        System.out.println("Qual o token de acesso?");
        String token = scanner.next();   
        admin.setSpobrefyToken(token);
        return admin;
    }

    @Override
    public String toCsvString() {
        String[] partes = { Integer.toString(getId()), getNickname(), getEmail(), getPassword(), getClass().getSimpleName().toUpperCase(),getCPF(),getBirthDate(),getSpobrefyToken()};
        String csvUserString = String.join(";",partes);;

        return csvUserString;
    }
}
    

