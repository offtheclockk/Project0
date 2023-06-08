package com.revature.controllers;

import com.revature.daos.UserDAO;
import com.revature.models.User;
import com.revature.services.UserService;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class UserController {

    private static final UserService userService = new UserService(new UserDAO());

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public static void handleGetAll(Context ctx){
        // Inside here we need to make a call to our Employee Service to get us all the employees listed
        ArrayList<User> users = userService.getAllUsers();

        ctx.status(200);
        ctx.json(users);
    }

    public static void handleInsert(Context ctx){
        // To create a new employee from our Context body we need to essentially take it in as a JSON and convert it
        // To an object of the appropriate class

        User user = ctx.bodyAsClass(User.class);

        User returnedUser = userService.insertUser(user);

        // If the employee object we receive from the service is null, something has gone wrong
        // If it is not null, yay we did it

        if (returnedUser != null){
            // This means the user was created
            ctx.status(201);
            ctx.json(returnedUser);
        } else{
            // What happens if it comes back null?
            ctx.status(400);
            logger.warn("Insert failed");
        }
    }

    // Create some method stubs here just for now
    public static void handleGetOne(Context ctx){
        int id;
        try{
            id = Integer.parseInt(ctx.pathParam("id"));
        } catch (NumberFormatException e){
            ctx.status(400);
            logger.warn("Unable to parse id - :" + ctx.pathParam("id"));
            return;
        }

        // Now we can call the service to get this checked out
        User user = userService.getUserById(id);

        // Now we need to make sure our role is not null, if it isn't, we can return the role and call it a day,
        // otherwise we're returning a 404 since that role can't be found
        if (user != null){
            ctx.status(200);
            ctx.json(user);
        } else{
            ctx.status(404);
            logger.warn("No resource was found at id = " + id + " from ip: " + ctx.ip());

        }
    }

    public static void handleUpdateName(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("id"));
        User submittedUser = ctx.bodyAsClass(User.class);

        boolean updateSuccessful;

        if (submittedUser.getFirst_name() != null) {
            updateSuccessful = userService.updateUserFirstName(submittedUser.getFirst_name(), id);
        } else {
            updateSuccessful = userService.updateUserLastName(submittedUser.getLast_name(), id);
        }

        if (updateSuccessful){
            ctx.status(200);
        } else {
            ctx.status(400);
            logger.warn("Update failed!");
        }
    }

    public static void handleDelete(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean deleteSuccessful = userService.deleteUser(id);

        if (deleteSuccessful) {
            ctx.status(200);
        } else {
            ctx.status(400);
            logger.warn("Delete failed!");
        }
    }

}
