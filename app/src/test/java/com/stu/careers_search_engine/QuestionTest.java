package com.stu.careers_search_engine;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuestionTest {

    Question questionTest = new Question(1,
            "I am the life of the party", "E", 1);

    @Test
    public void getQuestionNum() {
        assertEquals(1, questionTest.getQuestionNum());
    }

    @Test
    public void getQuestion() {
        assertEquals("I am the life of the party", questionTest.getQuestion());
    }

    @Test
    public void getPersonalityTrait() {
        assertEquals("E", questionTest.getPersonalityTrait());
    }

    @Test
    public void getScoring() {
        assertEquals(1, questionTest.getScoring());
    }
}