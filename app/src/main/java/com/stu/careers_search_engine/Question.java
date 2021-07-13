package com.stu.careers_search_engine;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.util.ArrayList;

public class Question {

    private int question_num;
    private String question;
    private String personality_trait;

    public int getQuestion_num() {
        return question_num;
    }

    public String getQuestion() {
        return question;
    }

    public String getPersonality_trait() {
        return personality_trait;
    }
    /*
    So in tutorial the we are getting "posts"
    private int userID;
    private int id;
    private String title;

    Use this if variable name is different from JSON key
    @SerializedName("body")
    private String text;*/

    /*
    private int question_num;
    private String question;
    private String personality_trait;

    public int getQuestion_num() {
        return question_num;
    }

    public String getQuestion() {
        return question;
    }

    public String getPersonality_trait() {
        return personality_trait;
    }*/
    /*
    private String[] questions;

    public String[] getQuestions() {
        return questions;
    }*/

}
