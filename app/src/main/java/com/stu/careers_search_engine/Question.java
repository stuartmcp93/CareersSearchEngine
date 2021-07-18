package com.stu.careers_search_engine;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.util.ArrayList;

public class Question {

    private int questionNum;
    private String question;
    private String personality_trait;

    public Question(int questionNum, String question, String personality_trait) {
        this.questionNum = questionNum;
        this.question = question;
        this.personality_trait = personality_trait;
    }

    public int getQuestionNum() {
        return questionNum;
    }

    public String getQuestion() {

        return question;
    }

    public String getPersonality_trait() {

        return personality_trait;
    }


}
