package com.spobrefy.fdata;

import com.spobrefy.model.users.Admin;
import com.spobrefy.model.users.Artist;
import com.spobrefy.model.users.User;

import java.util.ArrayList;

public class UserFileHandler extends FileHandler implements IFileHandler<User> {
    private static final String fileName = "users.csv";
    private static final UserFileHandler instance = new UserFileHandler();
    public static UserFileHandler getInstance() {
        return instance;
    }

    public ArrayList<User> readFileData() {
        ArrayList<User> users = new ArrayList<>();
        String spliter = ";";

        for(String line : super.readFileData(fileName)) {
            String[] values = line.split(spliter);

            User newUser;
            switch (values[4]) {
                case "USER" -> newUser = new User(Integer.parseInt(values[0]), values[1], values[2], values[3]);
                case "ARTIST" -> newUser = new Artist(Integer.parseInt(values[0]), values[1], values[2], values[3], values[5], values[6]);
                case "ADMIN" -> newUser = new Admin(Integer.parseInt(values[0]), values[1], values[2], values[3], values[5], values[6], values[7]);
                default -> {
                    continue;
                }
            }

            users.add(newUser);
        }
        return users;
    }

    public void writeFileData(User obj) {
        super.writeFileData(fileName, obj.toCsvString());
    }

    public void updateFileData(User obj) {
        super.updateFileData(fileName, obj);
    }

    public void deleteFileData(User obj) {
        super.deleteFileData(fileName, obj);
    }
}
