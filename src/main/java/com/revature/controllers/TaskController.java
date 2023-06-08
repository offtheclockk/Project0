package com.revature.controllers;

import com.revature.daos.TaskDAO;
import com.revature.models.Task;
import com.revature.services.TaskService;
import io.javalin.http.Context;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.ArrayList;

public class TaskController {

    private static final TaskService taskService = new TaskService(new TaskDAO());

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    public static void handleGetOne(Context ctx){

        int id;
        try{
            id = Integer.parseInt(ctx.pathParam("id"));
        }catch (NumberFormatException e){
            ctx.status(400);
            return;
        }

        Task task = taskService.getTaskById(id);

        if (task != null){
            ctx.status(200);
            ctx.json(task);
        } else{
            ctx.status(404);
            logger.warn("Get user by id failed!!");
        }
    }

    public static void handleGetAll(Context ctx){
        ArrayList<Task> tasks = taskService.getAllTasks();

        if (tasks != null) {
            ctx.status(200);
            ctx.json(tasks);
        } else {
            ctx.status(400);
            logger.warn("Get all tasks failed!");
        }
    }

    public static void handleInsert(Context ctx){
        Task task = ctx.bodyAsClass(Task.class);
        Task returnedTask = taskService.insertTask(task);

        if (returnedTask != null) {
            ctx.status(201);
            ctx.json(returnedTask);
        } else {
            ctx.status(400);
            logger.warn("Insert failed!");
        }
    }

    public static void handleUpdate(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("id"));
        Task submittedTask = ctx.bodyAsClass(Task.class);

        boolean updateSuccessful;

        if (submittedTask.getTask_title() != null) {
            updateSuccessful = taskService.updateTaskTitle(submittedTask.getTask_title(), id);
        } else if (submittedTask.getTask_description() != null){
            updateSuccessful = taskService.updateTaskDescription(submittedTask.getTask_description(), id);
        } else {
            updateSuccessful = taskService.updateTaskIsCompleted(submittedTask.isIs_completed(), id);
        }
        if (updateSuccessful){
            ctx.status(200);
        } else{
            ctx.status(400);
            logger.warn("Update failed!");
        }
    }

    public static void handleDelete(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean deleteSuccessful = taskService.deleteTask(id);

        if (deleteSuccessful) {
            ctx.status(200);
        } else {
            ctx.status(400);
            logger.warn("Delete failed!");
        }
    }
}
