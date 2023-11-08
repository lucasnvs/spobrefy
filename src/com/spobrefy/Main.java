package com.spobrefy;

import com.spobrefy.data.FileHandler;

public class Main {
    public static void main(String[] args) {
//        Sistema sistema = new Sistema("Spobrefy");
//
//        Menu menu = new Menu(sistema);
//        menu.init();

        System.out.println(FileHandler.readData("user.csv"));
    }
}
