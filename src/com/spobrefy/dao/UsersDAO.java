package com.spobrefy.dao;

import java.util.ArrayList;
import java.util.List;

import com.spobrefy.users.User;

public class UsersDAO implements DaoInterface<User> {
    private static final UsersDAO instance = new UsersDAO();
    private List<User> usersList;

    private UsersDAO() {
        usersList = new ArrayList<>();
        usersList.add(new User("lucas", "lucas@email.com", "pastel2020"));
        usersList.addAll(ArtistsDAO.getInstance().findAll());
    }

    public static UsersDAO getInstance() {
        return instance;
    }

    @Override
    public User findById(int id) {
        for (User user : usersList) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        return usersList;
    }

    @Override
    public void save(User user) {
        usersList.add(user);
    }

    @Override
    public void update(User user) {
        
    }

    @Override
    public void delete(User user) {
        usersList.remove(user);
    }
}
