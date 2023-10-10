package com.spobrefy.dao;

import java.util.ArrayList;
import java.util.List;

import com.spobrefy.Sistema;
import com.spobrefy.users.Admin;
import com.spobrefy.users.Artist;
import com.spobrefy.users.User;

public class UsersDAO implements IDao<User> {
    private static final UsersDAO instance = new UsersDAO();
    private List<User> usersList;

    private UsersDAO() {
        usersList = new ArrayList<>();
        usersList.add(new User("lucas", "lucas@email.com", "pastel2020"));
        usersList.add(new Artist("Matue", "matue30@email.com", "30praUm", "9822554812", "22/03/1998"));
        usersList.add(new Artist("Veigh", "veighbaby@email.com", "tururum", "2349281022", "16/08/2000"));
        usersList.add(new Artist("Teto", "tetinho@email.com", "plaktudum", "234233581022", "20/11/2002"));
        usersList.add(new Admin(999,"Pimbola", "pimbola@email.com", "senhapimbola", "435234234","10/10/2000", "carlospiloto"));
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
    public void update(User newUser) {
        User u = findById(newUser.getId());
        delete(u);
        save(newUser);
    }

    @Override
    public void delete(User user) {
        usersList.remove(user);
    }

    @Override
    public void deleteById(int id) {
        User userToBeDeleted = findById(id);
        delete(userToBeDeleted);
        if(userToBeDeleted.getClass().getSimpleName().equals("Artist")) {
            ArtistsDAO.getInstance().delete((Artist) userToBeDeleted);
        }
    }
}
