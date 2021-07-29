package com.stu.careers_search_engine;

import java.io.Serializable;

/**
 * This a career objects class that can be used to get information about different careers in the
 * database.
 *
 * @Author Stuart McPherson 29/07/2021
 */

@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings
public class Career implements Serializable {

    //The ID of the career in the database
    private int id;

    //The career area for this career
    private String careerArea;

    //The title of the job title of the career
    private String jobTitle;

    //The personality trait that this career matches with
    private String matchingTrait;

    //description of the career
    private String description;

    //The average salary of the career
    private String salary;


    /**
     * This is the constructor method for the Career class
     * @param id id retrieved from the database
     * @param careerArea the career area for this career
     * @param jobTitle the job title for this career
     * @param matchingTrait the matching personality trait associated with this career
     * @param description the description of the career
     * @param salary the average salary for the career in the UK
     */
    public Career( int id, String careerArea, String jobTitle, String matchingTrait,
                   String description, String salary) {
        this.id = id;
        this.careerArea = careerArea;
        this.jobTitle = jobTitle;
        this.matchingTrait = matchingTrait;
        this.description = description;
        this.salary = salary;
    }

    /**
     *
     * @return the career ID.
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return the career area.
     */
    public String getCareerArea() {
        return careerArea;
    }

    /**
     *
     * @return the job title
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     *
     * @return the matching trait for the career
     */
    public String getMatchingTrait() {
        return matchingTrait;
    }

    /**
     *
     * @return the description of the career
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return the salary of the career
     */
    public String getSalary() {
        return salary;
    }
}
