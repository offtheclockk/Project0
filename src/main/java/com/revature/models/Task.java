package com.revature.models;

public class Task {
    private int task_id;
    private String task_title;
    private String task_description;
    private boolean is_completed;
    private User user;
    private int user_id_fk;

    public Task() {
    }

    public Task(int task_id, String task_title, String task_description, boolean is_completed, User user) {
        this.task_id = task_id;
        this.task_title = task_title;
        this.task_description = task_description;
        this.is_completed = is_completed;
        this.user = user;
    }

    public Task(String task_title, String task_description, boolean is_completed, User user) {
        this.task_title = task_title;
        this.task_description = task_description;
        this.is_completed = is_completed;
        this.user = user;
    }

    public Task(String task_title, String task_description, boolean is_completed, int user_id_fk) {
        this.task_title = task_title;
        this.task_description = task_description;
        this.is_completed = is_completed;
        this.user_id_fk = user_id_fk;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public String getTask_title() {
        return task_title;
    }

    public void setTask_title(String task_title) {
        this.task_title = task_title;
    }

    public String getTask_description() {
        return task_description;
    }

    public void setTask_description(String task_description) {
        this.task_description = task_description;
    }

    public boolean isIs_completed() {
        return is_completed;
    }

    public void setIs_completed(boolean is_completed) {
        this.is_completed = is_completed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getUser_id_fk() {
        return user_id_fk;
    }

    public void setUser_id_fk(int user_id_fk) {
        this.user_id_fk = user_id_fk;
    }

    @Override
    public String toString() {
        return "Task{" +
                "task_id=" + task_id +
                ", task_title='" + task_title + '\'' +
                ", task_description='" + task_description + '\'' +
                ", is_completed=" + is_completed +
                ", user=" + user +
                ", user_id_fk=" + user_id_fk +
                '}';
    }
}