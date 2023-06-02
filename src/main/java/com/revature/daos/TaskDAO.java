package com.revature.daos;

import com.revature.models.Task;

import java.util.ArrayList;

public class TaskDAO implements TaskDAOInterface{
    @Override
    public Task getTaskById(int id) {
        return null;
    }

    @Override
    public ArrayList<Task> getAllTasks() {
        return null;
    }

    @Override
    public boolean updateTaskIsCompleted(boolean is_completed, int id) {
        return false;
    }

    @Override
    public Task insertTask(Task task) {
        return null;
    }

    @Override
    public Task deleteTask(int id) {
        return null;
    }
}
