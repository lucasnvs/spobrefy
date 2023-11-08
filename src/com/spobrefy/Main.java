package com.spobrefy;

import com.spobrefy.data.FileHandler;
import com.spobrefy.model.users.User;

public class Main {
    public static void main(String[] args) {
//        Sistema sistema = new Sistema("Spobrefy");
//
//        Menu menu = new Menu(sistema);
//        menu.init();

        System.out.println(FileHandler.readData("users.csv"));
        for(User u : FileHandler.reqUserData()) {
            System.out.println(u.toString());
        }
    }
}
