package com.spobrefy.data;

import com.spobrefy.model.users.Admin;
import com.spobrefy.model.users.Artist;
import com.spobrefy.model.users.User;

import java.io.*;
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
        }

        sc.close();

        return totalLines;
    }

    public static void writeData(String fileName, String newLine) {
        String[] partesCaminho ={".","src","com","spobrefy","data","files_data", fileName};
        String caminhoArquivo =  String.join(File.separator,partesCaminho);
        File file = new File(caminhoArquivo);

        FileWriter pw = null;
        try {
            pw = new FileWriter(file, true);
            pw.append(newLine+"\n");
            pw.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static ArrayList<User> readUserData() {
        ArrayList<User> users = new ArrayList<>();

        String spliter = ";";
        for(String line : readData("users.csv")) {
            String[] values = line.split(spliter);

            User newUser;
            switch (values[4]) {
                case "USER":
                    newUser = new User(Integer.parseInt(values[0]), values[1], values[2], values[3]);
                    break;
                case "ARTIST":
                    newUser = new Artist(Integer.parseInt(values[0]), values[1], values[2], values[3],values[5], values[6]);
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

    public static void writeUserData(User user) {
        writeData("users.csv", user.toCsvString());
    }
}
