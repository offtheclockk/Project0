package com.revature.deprecated;

import com.revature.daos.TaskDAO;
import com.revature.daos.UserDAO;
import com.revature.models.Task;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Launcher {

    public static void main(String[] args) {

        /*This is a try-with-resources block
         A resource (in this case, a DB connection) is opened within the parenthesis
         Another benefit is that the resource will close for us when the block completes
         This is helpful for code cleanup and preventing memory leaks*/
        try(Connection conn = ConnectionUtil.getConnection()){
            System.out.println("CONNECTION SUCCESSFUL :)");
        }
        catch(SQLException e){
            System.out.println("Connection Failed :(" );
        }


        //calling DAO methods below---------------

        //instantiate a RoleDAO object so we can use its methods
        UserDAO uDAO = new UserDAO();
        User tristan = new User("Tristan", "White");

        uDAO.insertUser(tristan);

        //getting a User object by id
        System.out.println(uDAO.getUserById(1));

        ArrayList<User> userList = uDAO.getAllUsers();

        uDAO.updateUserFirstName("John", 1);

        for (User u : userList) {
            System.out.println(u);
        }

    }

}
