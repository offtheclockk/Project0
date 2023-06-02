package com.revature.daos;

import com.revature.models.User;

import java.util.ArrayList;

public interface UserDAOInterface {
    User getUserById(int id);
    ArrayList<User> getAllUsers();
    boolean updateUser();
    User insertUser(User user);
    User deleteUser(int id);

}
