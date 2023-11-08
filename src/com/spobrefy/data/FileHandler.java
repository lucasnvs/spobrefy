package com.spobrefy.data;

import com.spobrefy.model.users.Admin;
import com.spobrefy.model.users.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    public static ArrayList<String> readData(String fileName) {
        ArrayList<String> totalLines = new ArrayList<>();

        String[] partesCaminho ={".","src","com","spobrefy","data","files_data", fileName};
        String caminhoArquivo =  String.join(File.separator,partesCaminho);
        File file = new File(caminhoArquivo);

        Scanner sc = null;
        try {
            sc = new Scanner(file);
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (sc.hasNext()) {
            String line = sc.nextLine();
            totalLines.add(line);
            System.out.println(line);
        }

        sc.close();

        return totalLines;
    }

    public static ArrayList<User> reqUserData() {
        ArrayList<User> users = new ArrayList<>();

        String spliter = ";";
        for(String line : readData("users.csv")) {
            String[] values = line.split(spliter);

            User newUser;
            switch (values[4]) {
                case "DEFAULT":
                    newUser = new User(Integer.parseInt(values[0]), values[1], values[2], values[3]);
                    break;
                case "ADMIN":
                    newUser = new Admin(Integer.parseInt(values[0]), values[1], values[2], values[3], values[5], values[6], values[7]);
                    break;
                default:
                    continue;
            }

            users.add(newUser);
        }
        return users;
    }
}
