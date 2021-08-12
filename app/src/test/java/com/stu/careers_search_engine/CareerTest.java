package com.stu.careers_search_engine;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This test class tests career objects have been successfully created.
 *
 * @Result a new career object
 * @Author Stuart McPherson
 */
public class CareerTest {

    private Career career = new Career(1, "Finance", "Accountant",
            "N", "This is a test description","25,000");
    @Test
    public void getId() {
        assertEquals(1, career.getId());
    }

    @Test
    public void getCareerArea() {
        assertEquals("Finance", career.getCareerArea());
    }

    @Test
    public void getJobTitle() {
        assertEquals("Accountant", career.getJobTitle());
    }

    @Test
    public void getMatchingTrait() {
        assertEquals("N", career.getMatchingTrait());
    }

    @Test
    public void getDescription() {
        assertEquals("This is a test description", career.getDescription());
    }

    @Test
    public void getSalary() {
        assertEquals("25,000", career.getSalary());
    }
}