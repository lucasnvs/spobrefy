package com.spobrefy;

import com.spobrefy.menu.Menu;

public class Main {
    public static void main(String[] args) {
        Sistema sistema = new Sistema("Spobrefy");

        Menu menu = new Menu(sistema);
        menu.init();
    }
}
