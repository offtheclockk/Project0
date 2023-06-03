package com.revature.daos;

import com.revature.models.Task;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class TaskDAO implements TaskDAOInterface{
    @Override
    public Task getTaskById(int id) {
        UserDAO uDAO = new UserDAO();
        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM tasks WHERE task_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {

                Task task = new Task(
                        rs.getInt("task_id"),
                        rs.getString("task_title"),
                        rs.getString("task_description"),
                        rs.getBoolean("is_completed"),
                        uDAO.getUserById(rs.getInt("user_id_fk"))
                );
                return task;
            }
        } catch(SQLException e) {
            System.out.println("Error getting task!!");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Task> getAllTasks() {
        UserDAO uDAO = new UserDAO();

        try(Connection conn = ConnectionUtil.getConnection()) {
            ArrayList<Task> tasks = new ArrayList<>();

            String sql = "SELECT * FROM tasks";

            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                Task task = new Task(
                        rs.getInt("task_id"),
                        rs.getString("task_title"),
                        rs.getString("task_description"),
                        rs.getBoolean("is_completed"),
                        uDAO.getUserById(rs.getInt("user_id_fk"))
                );
                tasks.add(task);
            }
            return tasks;

        } catch(SQLException e) {
            System.out.println("Getting all tasks failed!!");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateTaskTitle(String task_title, int id) {
        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "UPDATE tasks SET task_title = ? WHERE task_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, task_title);
            ps.setInt(2, id);
            ps.executeUpdate();
            return true;

        } catch(SQLException e) {
            System.out.println("Update task title failed!!");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateTaskDescription(String task_description, int id) {
        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "UPDATE tasks SET task_description = ? WHERE task_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, task_description);
            ps.setInt(2, id);
            ps.executeUpdate();
            return true;

        } catch(SQLException e) {
            System.out.println("Update task description failed!!");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateTaskIsCompleted(boolean is_completed, int id) {
        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "UPDATE tasks SET is_completed = ? WHERE task_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBoolean(1, is_completed);
            ps.setInt(2, id);
            ps.executeUpdate();
            return true;

        } catch(SQLException e) {
            System.out.println("Update task completion failed!!");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Task insertTask(Task task) {
        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO tasks (task_title, task_description, is_completed, user_id_fk) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, task.getTask_title());
            ps.setString(2, task.getTask_description());
            ps.setBoolean(3, task.isIs_completed());
            ps.setInt(4, task.getUser_id_fk());
            ps.executeUpdate();

            return task;

        } catch(SQLException e) {
            System.out.println("Insert task failed!!");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Task deleteTask(int id) {
        return null;
    }
}
