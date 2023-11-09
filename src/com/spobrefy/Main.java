package com.spobrefy;

import com.spobrefy.dao.UsersDAO;
import com.spobrefy.data.FileHandler;
import com.spobrefy.model.users.User;

public class Main {
    public static void main(String[] args) {
//        Sistema sistema = new Sistema("Spobrefy");
//
//        Menu menu = new Menu(sistema);
//        menu.init();

        System.out.println(FileHandler.readData("users.csv"));
        for(User u : FileHandler.readUserData()) {
            System.out.println(u.toString());
        }

        System.out.println();
        System.out.println("FINAL");
        System.out.println();

        for (User u : UsersDAO.getInstance().findAll()) {
            FileHandler.writeUserData(u);
        }

        System.out.println(FileHandler.readData("users.csv"));
        for(User u : FileHandler.readUserData()) {
            System.out.println(u.toString());
        }
    }
}
