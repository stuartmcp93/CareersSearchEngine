package com.stu.careers_search_engine;

/**
 *
 * This class creates question objects from the questions table in the database.
 * The Question are used to display questions in the PersonalityQuestion activity and calculate
 * scoring for the personality traits.
 *
 * @Author Stuart McPherson
 */
public class Question {

    //The question number
    private final int questionNum;

    //The question text
    private final String question;

    //The personality trait measured by the question
    private final String personalityTrait;

    //Whether the question adds or subtracts from the total trait score
    private final int scoring;

    /**
     * This the the constructor for the Question class
     * @param questionNum the question number
     * @param question the question text
     * @param personality_trait the personality trait
     * @param scoring the scoring for the question
     */
    public Question(int questionNum, String question, String personality_trait, int scoring) {
        this.questionNum = questionNum;
        this.question = question;
        this.personalityTrait = personality_trait;
        this.scoring = scoring;
    }

    /**
     * This method returns the question number.
     *
     * @return the question number.
     */
    public int getQuestionNum() {

        return questionNum;
    }

    /**
     * This method returns the question text.
     *
     * @return the question text.
     */
    public String getQuestion() {

        return question;
    }

    /**
     * This methods returns the personality trait measured by the question.
     *
     * @return the personality trait measured.
     */
    public String getPersonalityTrait() {

        return personalityTrait;
    }

    /**
     * This method returns the scoring of the question. Some questions add to the total and
     * some subtract.
     *
     * @return a '1' if adds to score, '0' if subtracts from the score.
     */
    public int getScoring() {

        return scoring;
    }
}
