package com.spobrefy.test;

import com.spobrefy.Sistema;
import com.spobrefy.Menu;

public class allTest {
    public static void main(String[] args) {
        Sistema sistema = new Sistema("Spobrefy");

        Menu menu = new Menu(sistema);
        menu.init();
    }
}
