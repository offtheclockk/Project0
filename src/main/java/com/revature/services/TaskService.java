package com.revature.services;

import com.revature.daos.TaskDAO;
import com.revature.daos.TaskDAOInterface;
import com.revature.models.Task;

public class TaskService {
    private final TaskDAOInterface taskDAO = new TaskDAO();

    public Task getTaskById(int id) {
        if(id > 0) {
            return taskDAO.getTaskById(id);
        } else {
            return null;
        }
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
}
