package com.stu.careers_search_engine;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.util.ArrayList;

public class Question {

    private int questionNum;
    private String question;
    private String personalityTrait;
    private int scoring;

    public Question(int questionNum, String question, String personality_trait, int scoring) {
        this.questionNum = questionNum;
        this.question = question;
        this.personalityTrait = personality_trait;
        this.scoring = scoring;
    }

    public int getQuestionNum() {

        return questionNum;
    }

    public String getQuestion() {

        return question;
    }

    public String getPersonalityTrait() {

        return personalityTrait;
    }

    public int getScoring() {

        return scoring;
    }
}
