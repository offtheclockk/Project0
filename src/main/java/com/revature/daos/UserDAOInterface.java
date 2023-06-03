package com.revature.daos;

import com.revature.models.User;

import java.util.ArrayList;

public interface UserDAOInterface {
    User getUserById(int id);
    ArrayList<User> getAllUsers();
    boolean updateUserFirstName(String first_name, int id);

    boolean updateUserLastName(String last_name, int id);

    User insertUser(User user);
    User deleteUser(int id);

}
