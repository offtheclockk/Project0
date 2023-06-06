package com.revature.deprecated;

import com.revature.daos.TaskDAO;
import com.revature.daos.UserDAO;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class Launcher {

    public static void main(String[] args) {

        try(Connection conn = ConnectionUtil.getConnection()){
            System.out.println("CONNECTION SUCCESSFUL :)");
        }
        catch(SQLException e) {
            System.out.println("Connection Failed :(");
        }
        UserDAO uDAO = new UserDAO();
        TaskDAO tDAO = new TaskDAO();


    }

}
