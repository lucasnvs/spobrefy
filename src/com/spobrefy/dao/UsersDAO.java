package com.spobrefy.dao;

import java.util.ArrayList;

import com.spobrefy.fdata.UserFileHandler;
import com.spobrefy.model.users.User;

public class UsersDAO implements IDao<User> {
    private static UsersDAO instance = new UsersDAO();

    public static UsersDAO getInstance() {
        if(instance == null)  {
            instance = new UsersDAO();
        }
        return instance;
    }

    public int getLastId() {
        ArrayList<User> list = this.findAll();
        if (list.size() == 0) {
            return 0;
        }
        return list.get(list.size() - 1).getId();
    }

    @Override
    public User findById(int id) {
        for (User user : findAll()) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public ArrayList<User> findAll() {
        return UserFileHandler.getInstance().readData();
    }
    @Override
    public void save(User user) {
        if(user == null) return;
        UserFileHandler.getInstance().writeData(user);
    }
    @Override
    public void update(User user) {
        if(user == null) return;
        UserFileHandler.getInstance().updateData(user);
    }
    @Override
    public void delete(User user) {
        if(user == null) return;
        UserFileHandler.getInstance().removeData(user);
    }
    @Override
    public void deleteById(int id) {
        delete(findById(id));
    }
}
