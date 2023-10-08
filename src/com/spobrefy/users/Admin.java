package com.spobrefy.users;

import java.util.Scanner;

public class Admin extends NotDefaultUser {
    private String spobrefyToken;
    
    // token removido do construtor por incompatibilidade com o metodo create do NotDefaultUser e adicionado no método create local desta classe
    // depois será necessário uma verificação para criar o admin se seu token for válido.
    private Admin(String nickname, String email, String password, String cpf, String birthDate) {
        super(nickname, email, password, cpf, birthDate);
    }

    private Admin(String nickname, String email, String password, String cpf, String birthDate, String token) {
        super(nickname, email, password, cpf, birthDate);
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
}
    

