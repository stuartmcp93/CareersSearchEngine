package com.stu.careers_search_engine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.preference.TwoStatePreference;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This class runs the personality quiz of the application. It displays questions from the
 * 'big five personality test' and stores user responses to calculate their personality score.
 *
 *
 * @Author Stuart McPherson
 */
public class PersonalityQuestion extends AppCompatActivity {

    ImageView IMG_home_btn;
    Button BTN_submit_answer;
    TextView TV_questionDisplay, TV_questionNum, TV_total_questions;
    RadioButton RB_sAgree, RB_agree, RB_neutral, RB_disagree, RB_sDisagree;

    //Variables to hold integer scores for each of the personality traits measured
    int extroversionScore, agreeablenessScore, conscientiousnessScore,
            neuroticismScore, opennessScore;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personality_question);
        Objects.requireNonNull(getSupportActionBar()).hide();


        //Assign UI components
        IMG_home_btn = findViewById(R.id.IMG_home_logo_quiz);
        BTN_submit_answer = findViewById(R.id.BTN_submit_answer);
        TV_questionDisplay = findViewById(R.id.TV_question_display);
        TV_total_questions = findViewById(R.id.TV_question_total);
        TV_questionNum = findViewById(R.id.TV_question_number);
        RB_sAgree = findViewById(R.id.RBTN_strongly_agree);
        RB_agree = findViewById(R.id.RBTN_agree);
        RB_neutral = findViewById(R.id.RBTN_neutral);
        RB_disagree = findViewById(R.id.RBTN_disagree);
        RB_sDisagree = findViewById(R.id.RBTN_strongly_disagree);

        //Starting scores for personality test - https://openpsychometrics.org/printable/big-five-personality-test.pdf
        extroversionScore = 20;
        agreeablenessScore = 14;
        conscientiousnessScore = 14;
        neuroticismScore = 38;
        opennessScore = 8;


        //Set up first question on creation of the activity
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);


        Question question = dataBaseHelper.getQuestion(Integer.parseInt(TV_questionNum.getText().toString()));

        //Display the first question
        TV_questionDisplay.setText(question.getQuestion());

        //Display total number of questions in the quiz
        TV_total_questions.setText(" / " + dataBaseHelper.countQuestions());


        //Set on click listeners for home navigation button
        IMG_home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IMG_home_btn.setColorFilter(0x800080, PorterDuff.Mode.MULTIPLY);
                returnHome();
            }
        });

        //Set on click listener for the submit answer button
        BTN_submit_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion();
            }
        });


    }

    /**
     * This method allows the user to return to the home screen of the application.
     */
    private void returnHome() {
        Intent returnHome = new Intent(this, Home.class);
        startActivity(returnHome);
    }


    /**
     * This method reads which radio button was clicked by the user and returns the score
     * depending on the button that was clicked.
     *
     * @return the user answer as an integer to be added/subtracted
     */
    public int radioButtonClicked() {
        //Initiate integer variable
        int score = 0;

        //If user selects 'Strongly Agree'
        if (RB_sAgree.isChecked()) {
            score = 5;
        }

        //If user selects 'Agree'
        if (RB_agree.isChecked()) {
            score = 4;
        }

        //If user selects 'Neutral'
        if (RB_neutral.isChecked()) {
            score = 3;
        }

        //If user selects 'Disagree'
        if (RB_disagree.isChecked()) {
            score = 2;
        }
        //If user selects 'Strongly Disagree'
        if (RB_sDisagree.isChecked()) {
            score = 1;
        }


        return score;
    }

    /**
     * This method adds the score of the user option to the personality trait measured by the current
     * question.
     *
     * @param question   the personality trait associated with the current question
     * @param userAnswer the response of the user as an integer
     */
    public void addScoreToPersonalityTrait(Question question, int userAnswer) {

        //Get the personality trait of the question
        switch (question.getPersonalityTrait()) {

            //Extroversion
            case "E":
                //Check if score is added or subtracted to the total
                if (question.getScoring() == 1) {
                    extroversionScore += userAnswer;
                } else {
                    extroversionScore -= userAnswer;
                }
                break;

            //Agreeableness
            case "A":
                //Check if score is added or subtracted to the total
                if (question.getScoring() == 1) {
                    agreeablenessScore += userAnswer;
                } else {
                    agreeablenessScore -= userAnswer;
                }
                break;

            //Conscientiousness
            case "C":
                //Check if score is added or subtracted to the total
                if (question.getScoring() == 1) {
                    conscientiousnessScore += userAnswer;
                } else {
                    conscientiousnessScore -= userAnswer;
                }
                break;

            //Neuroticism
            case "N":
                //Check if score is added or subtracted to the total
                if (question.getScoring() == 1) {
                    neuroticismScore += userAnswer;
                } else {
                    neuroticismScore -= userAnswer;
                }
                break;

            //Openness
            case "O":
                //Check if score is added or subtracted to the total
                if (question.getScoring() == 1) {
                    opennessScore += userAnswer;
                } else {
                    opennessScore -= userAnswer;
                }
                break;
        }
    }


    /**
     * This method is called when the user clicks 'submit' after answering the previous question.
     * The method updates the UI with the next question in the quiz.
     */
    private void nextQuestion() {

        //Current question number from UI display
        int currentQuestionNum = Integer.parseInt((String) TV_questionNum.getText());

        //get the users answer for the question
        int userAnswer = radioButtonClicked();

        /*
            Default value for the user answer is 0.
            A user answer of 0 means the user has not checked a radio button.
            The user cannot load the next question without submitting an answer
         */
        if (userAnswer == 0) {

            //Display toast to ask user to choose an answer
            Toast.makeText(PersonalityQuestion.this, "Please check an answer!",
                    Toast.LENGTH_SHORT).show();


        } else {

            //Create new database helper
            DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

            //Get the current Question object from the database
            Question currentQuestion = dataBaseHelper.getQuestion(currentQuestionNum);

            //Pass Question object and user answer to add the score to the trait being measured
            addScoreToPersonalityTrait(currentQuestion, userAnswer);

            //Get the next question number
            int nextQuestionNum = currentQuestionNum + 1;

            //Check if quiz is finished
            if (nextQuestionNum > dataBaseHelper.countQuestions()) {

                //If all questions are answered start results display activity
                loadResultsDisplay();

                //Alert user of results being calculated
                Toast.makeText(this, "Calculating results!", Toast.LENGTH_SHORT).show();

                //If questions remaining in the quiz update the UI with the next question
            } else {
                //Get next Question object form the database
                Question nextQuestion = dataBaseHelper.getQuestion(nextQuestionNum);

                //Set UI to display the question data
                TV_questionNum.setText(Integer.toString(nextQuestionNum));
                TV_questionDisplay.setText(nextQuestion.getQuestion());
            }


        }

    }

    /**
     * This method adds the users scores for the personality traits to the database and
     * loads the PersonalityQuizResults activity.
     *
     */
    public void loadResultsDisplay() {

        //Create new Databasehelper
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

        //Open writable database to add scores
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        //Insert the username and scores into the database
        dataBaseHelper.insertUserScores(db, ((User) this.getApplication()).getUsername(),
                extroversionScore,
                agreeablenessScore,
                conscientiousnessScore,
                neuroticismScore,
                opennessScore);

        //Start PersonalityQuizResults activity
        Intent resultsIntent = new Intent(PersonalityQuestion.this, PersonalityQuizResults.class);
        startActivity(resultsIntent);
    }



}

