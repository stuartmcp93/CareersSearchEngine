package com.stu.careers_search_engine;

import android.app.Application;

/**
 * This class creates a User object that can be accessed throughout the entire application. The User
 * Object is created when the User logs into the application.
 *
 * @Author Stuart McPherson
 */
public class User extends Application {

    //The user ID
    private int id;

    //The users username
    private String username;

    //Empty constructor
    public User(){

    }

    /**
     * This method sets the users username.
     *
     * @param username the username
     * @return null
     */
    public User setUsername(String username) {
        this.username = username;
        return null;
    }

    /**
     * This method sets the ID for the user.
     *
     * @param id the ID of the user
     * @return null
     */
    public User setId(int id) {
        this.id = id;
        return null;
    }

    /**
     * This methods gets the users username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method returns the ID of the user.
     *
     * @return the users ID.
     */
    public int getId() { return id; }
}
