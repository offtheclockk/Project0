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
        ArrayList<User> users = userService.getAllUsers();

        ctx.status(200);
        ctx.json(users);
    }

    public static void handleInsert(Context ctx){
        User user = ctx.bodyAsClass(User.class);

        User returnedUser = userService.insertUser(user);

        if (returnedUser != null){
            ctx.status(201);
            ctx.json(returnedUser);
        } else{
            ctx.status(400);
            logger.warn("Insert failed");
        }
    }

    public static void handleGetOne(Context ctx){
        int id;
        try{
            id = Integer.parseInt(ctx.pathParam("id"));
        } catch (NumberFormatException e){
            ctx.status(400);
            logger.warn("Unable to parse id - :" + ctx.pathParam("id"));
            return;
        }

        User user = userService.getUserById(id);

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
