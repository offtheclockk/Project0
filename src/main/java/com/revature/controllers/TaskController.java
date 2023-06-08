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

    // Now we have our 5 methods to implement
    // Our roles service gives us the ability to update and get a specific role so we need to implement
    // handleGetOne and handleUpdate
    public static void handleGetOne(Context ctx){
        // Recall that the path for this will be http://localhost:7070/tasks/{id}
        // This will match http://localhost:7070/tasks/1
        // But it will also match http://localhost:7070/tasks/NaN

        //int x = ctx.pathParam("id"); // We need to find a way to parse this
        int id;
        try{
            id = Integer.parseInt(ctx.pathParam("id"));
        }catch (NumberFormatException e){
            // This block running means they didn't have a valid integer in their path
            ctx.status(400);
            // Adding a return statement here because there's no point continuing with a bad int
            return;
        }

        // Let's call the role service and attempt to pull the value
        Task task = taskService.getTaskById(id);

        // We need to check if the role is null or not
        if (task != null){
            // This is good, it found the roll
            ctx.status(200);
            ctx.json(task);
        } else{
            ctx.status(404);
        }
    }

    public static void handleGetAll(Context ctx){
        ArrayList<Task> tasks = taskService.getAllTasks();

        ctx.status(200);
        ctx.json(tasks);
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

        // Call the roleService to actually do something with this info
        boolean updateSuccessful;

        if (submittedTask.getTask_title() != null) {
            updateSuccessful = taskService.updateTaskTitle(submittedTask.getTask_title(), id);
        } else if (submittedTask.getTask_description() != null){
            updateSuccessful = taskService.updateTaskDescription(submittedTask.getTask_description(), id);
        } else {
            updateSuccessful = taskService.updateTaskIsCompleted(submittedTask.isIs_completed(), id);
        }
        // So updateSuccessful should let us know if we successfully updated the DB
        if (updateSuccessful){
            // This is good
            ctx.status(200);
        } else{
            // Was not able to update DB for some reason
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
