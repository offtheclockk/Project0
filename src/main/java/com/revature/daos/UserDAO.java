package com.revature.daos;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO implements UserDAOInterface{

    @Override
    public User getUserById(int id) {
        try(Connection conn = ConnectionUtil.getConnection()) {

            String sql = "SELECT * FROM users WHERE user_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name")
                );
                return user;
            }
        } catch(SQLException e) {
            System.out.println("Error getting Role");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        return null;
    }

    @Override
    public boolean updateUser() {
        return false;
    }

    @Override
    public User insertUser(User user) {
        return null;
    }

    @Override
    public User deleteUser(int id) {
        return null;
    }
}
