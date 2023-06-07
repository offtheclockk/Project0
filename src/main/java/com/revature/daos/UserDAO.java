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
            System.out.println("Error getting User");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<User> getAllUsers() {

        try(Connection conn = ConnectionUtil.getConnection()){
            ArrayList<User> users = new ArrayList<>();

            String sql = "SELECT * FROM users";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name")
                );
                users.add(user);
            }
            return users;
        } catch(SQLException e) {
            System.out.println("Error getting all users!");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateUserFirstName(String first_name, int id) {
        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "UPDATE users SET first_name = ? WHERE user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, first_name);
            ps.setInt(2, id);
            ps.executeUpdate();
            return true;

        } catch(SQLException e) {
            System.out.println("Update first name failed!!");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateUserLastName(String last_name, int id) {
        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "UPDATE users SET last_name = ? WHERE user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, last_name);
            ps.setInt(2, id);
            ps.executeUpdate();
            return true;

        } catch(SQLException e) {
            System.out.println("Update last name failed!!");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User insertUser(User user) {
        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO users (first_name, last_name) VALUES (?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getFirst_name());
            ps.setString(2, user.getLast_name());
            ps.executeUpdate();

            return user;

        } catch(SQLException e) {
            System.out.println("Insert user failed!!");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean deleteUser(int id) {
        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "DELETE FROM users WHERE user_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;

        } catch(SQLException e) {
            System.out.println("Delete user failed!!");
            e.printStackTrace();
        }
        return false;
    }
}
