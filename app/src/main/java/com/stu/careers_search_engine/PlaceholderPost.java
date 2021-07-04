package com.stu.careers_search_engine;

public class PlaceholderPost {
    //So the example had this cause that's what was in the JSON:
    //{"userID": 1,....etc
    /*private int userID;
    private int id;
    private String title;
    private String body;

    public int getUserId() {
        return userID;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }*/
    private int question_num;
    private String question;
    private String personality_trait;

    public int getQuestion_num(){
        return question_num;
    }

    public String getQuestion(){
        return question;
    }

    public String getPersonality_trait(){
        return personality_trait;
    }


}
