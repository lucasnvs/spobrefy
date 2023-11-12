package com.spobrefy.fdata;

import com.spobrefy.dao.UsersDAO;
import com.spobrefy.model.users.User;

import java.io.IOException;

public class Test {
    public static void main(String[] args) {

//        for(User u : UsersDAO.getInstance().usersList) {
//            UserFileHandler.getInstance().writeData(u);
//        }

        System.out.println(UsersDAO.getInstance().getLastId());
    }
}
