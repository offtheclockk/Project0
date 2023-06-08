package com.revature.services;

import com.revature.daos.TaskDAO;
import com.revature.daos.TaskDAOInterface;
import com.revature.daos.UserDAOInterface;
import com.revature.models.Task;

import java.util.ArrayList;

public class TaskService {
    private TaskDAOInterface taskDAO = new TaskDAO();

    public TaskService(TaskDAOInterface taskDAO){
        this.taskDAO = taskDAO;
    }

    public ArrayList<Task> getAllTasks() {
        return taskDAO.getAllTasks();
    }

    public Task getTaskById(int id) {
        if(id > 0) {
            return taskDAO.getTaskById(id);
        } else {
            return null;
        }
    }

    public Task insertTask(Task task) {
        return taskDAO.insertTask(task);
    }

    public boolean updateTaskTitle(String title, int id) {
        if (title == null || title.trim().equals("")) {
            return false;
        }

        char[] titleArray = title.toLowerCase().toCharArray();
        String formattedTitle = "";

        formattedTitle += Character.toUpperCase(titleArray[0]);

        for (int i = 1; i < titleArray.length; i++) {
            if (titleArray[i-1] == ' '){
                formattedTitle += Character.toUpperCase(titleArray[i]);
            } else {
                formattedTitle += titleArray[i];
            }
        }
        if (id > 0) {
            return taskDAO.updateTaskTitle(formattedTitle, id);
        }
        return false;
    }

    public boolean updateTaskDescription(String task_description, int id) {
        return taskDAO.updateTaskDescription(task_description, id);
    }

    public boolean updateTaskIsCompleted(boolean is_completed, int id) {
        return taskDAO.updateTaskIsCompleted(is_completed, id);
    }

    public boolean deleteTask(int id) {
        return taskDAO.deleteTask(id);
    }
}
