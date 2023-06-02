package com.revature.daos;

import com.revature.models.Task;

import java.util.ArrayList;

public interface TaskDAOInterface {
    Task getTaskById(int id);
    ArrayList<Task> getAllTasks();

    boolean updateTaskIsCompleted(boolean is_completed, int id);
    Task insertTask(Task task);
    Task deleteTask(int id);
}
