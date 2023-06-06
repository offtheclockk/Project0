package com.revature.services;

import com.revature.daos.UserDAO;
import com.revature.daos.UserDAOInterface;
import com.revature.models.User;

import java.util.ArrayList;

public class UserService {
    private final UserDAOInterface userDAO = new UserDAO();

    public ArrayList<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public User insertUser(User user) {
        return userDAO.insertUser(user);
    }
}
