package com.stu.careers_search_engine;

import java.io.Serializable;

@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings
public class Career implements Serializable {

    private int id;
    private String careerArea;
    private String jobTitle;
    private String matchingTrait;
    private String description;
    private String salary;

    public Career( int id, String careerArea, String jobTitle, String matchingTrait,
                   String description, String salary) {
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

    public String getDescription() {
        return description;
    }

    public String getSalary() {
        return salary;
    }
}
