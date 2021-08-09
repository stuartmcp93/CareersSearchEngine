package com.stu.careers_search_engine;

import android.app.Application;

public class User extends Application {

    private int id;
    private String username;

    public User(){

    }
    public User setUsername(String username) {
        this.username = username;
        return null;
    }

    public User setId(int id) {
        this.id = id;
        return null;
    }

    public String getUsername() {
        return username;
    }

    public int getId() { return id; }
}
