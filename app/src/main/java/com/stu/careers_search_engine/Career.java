package com.stu.careers_search_engine;

public class Career {

    private int id;
    private String careerArea;
    private String jobTitle;
    private String matchingTrait;

    public Career( int id, String careerArea, String jobTitle, String matchingTrait) {
        this.id = id;
        this.careerArea = careerArea;
        this.jobTitle = jobTitle;
        this.matchingTrait = matchingTrait;
    }


    public int getId() {
        return id;
    }

    public String getCareerArea() {
        return careerArea;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getMatchingTrait() {
        return matchingTrait;
    }
}
