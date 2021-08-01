package com.stu.careers_search_engine;

import com.google.gson.annotations.SerializedName;

public class JobInfo {
    private int userID;
    private int id;
    private String title;

    //When key name is different in JSON
    @SerializedName("body")
    private String text;

    public int getUserID() {
        return userID;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
