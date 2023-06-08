package com.revature.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.revature.controllers.UserController;
import com.revature.controllers.TaskController;
import io.javalin.Javalin;
import io.javalin.json.JsonMapper;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;

import static io.javalin.apibuilder.ApiBuilder.*;

public class JavalinAppConfig {

    Gson gson = new GsonBuilder().create();

    JsonMapper gsonMapper = new JsonMapper() {
        @Override
        public String toJsonString(@NotNull Object obj, @NotNull Type type) {
            return gson.toJson(obj, type);
        }

        @Override
        public <T> T fromJsonString(@NotNull String json, @NotNull Type targetType) {
            return gson.fromJson(json, targetType);
        }
    };

    private static final Logger logger = LoggerFactory.getLogger(JavalinAppConfig.class);

    private Javalin app = Javalin.create(config -> config.jsonMapper(gsonMapper))

            .before(ctx -> {
                logger.info(ctx.method() + " request has called to path: " + ctx.fullUrl());
            })

            .routes(() ->{
                path("users", () ->{
                    get(UserController::handleGetAll);
                    post(UserController::handleInsert);
                    path("{id}", () ->{
                        get(UserController::handleGetOne);
                        put(UserController::handleUpdateName);
                        delete(UserController::handleDelete);
                    });
                });
                path("tasks", () ->{
                    get(TaskController::handleGetAll);
                    post(TaskController::handleInsert);
                    path("{id}", () ->{
                        get(TaskController::handleGetOne);
                        put(TaskController::handleUpdate);
                        delete(TaskController::handleDelete);
                    });
                });
            });

    public void start(int port){
        app.start(port);
    }
}
