package com.revature.controllers;

import com.revature.models.Task;
import com.revature.services.TaskService;
import io.javalin.http.Context;

public class TaskController {

    private static final TaskService taskService = new TaskService();

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
        ctx.status(405);
    }

    public static void handleCreate(Context ctx){
        ctx.status(405);
    }

    public static void handleUpdate(Context ctx){
        // We'll start here for simplicity
        // We expect a role title and a role salary to come in from the client

        // We need to deserialize that and create a Role object
        Task submittedTask = ctx.bodyAsClass(Task.class);

        // Call the roleService to actually do something with this info
        boolean updateSuccessful = taskService.updateTaskTitle(submittedTask.getTask_title(), submittedTask.getTask_id());

        // So updateSuccessful should let us know if we successfully updated the DB
        if (updateSuccessful){
            // This is good
            ctx.status(200);
        } else{
            // Was not able to update DB for some reason
            ctx.status(400);
        }
    }

    public static void handleDelete(Context ctx){
        ctx.status(405); // Method is not allowed
    }
}
