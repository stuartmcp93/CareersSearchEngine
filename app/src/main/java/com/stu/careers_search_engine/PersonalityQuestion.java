package com.stu.careers_search_engine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.PorterDuff;
import android.os.Bundle;
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

public class PersonalityQuestion extends AppCompatActivity {
    ImageView IMG_home_btn;
    Button BTN_submit_answer;
    TextView TV_questionDisplay, TV_questionNum, TV_total_questions;
    RadioButton RB_sAgree, RB_agree, RB_neutral, RB_disagree, RB_sDisagree;


    //These will hold the users the score for each personality trait and
    //them to the database for the user.
    //When having working properly will have these int[] that will hold each score the question.
    // This will allow for calculation of final score
    int extroversionScore, agreeablenessScore, conscientiousnessScore,
            neuroticismScore, opennessScore;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personality_question);
        Objects.requireNonNull(getSupportActionBar()).hide();


        IMG_home_btn = findViewById(R.id.IMG_home_logo_quiz);
        BTN_submit_answer = findViewById(R.id.BTN_submit_answer);

        TV_questionDisplay = findViewById(R.id.TV_question_display);
        TV_total_questions = findViewById(R.id.TV_question_total);
        TV_questionNum = findViewById(R.id.TV_question_number);
        //TV_questionNum.setText(getString(R.string.question_num));
        RB_sAgree = findViewById(R.id.RBTN_strongly_agree);
        RB_agree = findViewById(R.id.RBTN_agree);
        RB_neutral = findViewById(R.id.RBTN_neutral);
        RB_disagree = findViewById(R.id.RBTN_disagree);
        RB_sDisagree = findViewById(R.id.RBTN_strongly_disagree);
        //questionsList = getQuestions();

        extroversionScore = 20;
        agreeablenessScore = 50;
        conscientiousnessScore = 20;
        neuroticismScore = 20;
        opennessScore = 20;


        //Set up first question
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        Question question = dataBaseHelper.getQuestion(Integer.parseInt(TV_questionNum.getText().toString()));
        TV_questionDisplay.setText(question.getQuestion());
        TV_total_questions.setText(" / " + dataBaseHelper.countQuestions());


        //Set on click listeners
        IMG_home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IMG_home_btn.setColorFilter(0x800080, PorterDuff.Mode.MULTIPLY);
                returnHome();
            }
        });

        BTN_submit_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion();
            }
        });


    }

    private void returnHome() {
        Intent returnHome = new Intent(this, Home.class);
        startActivity(returnHome);
    }

    /**
     * This method reads which radio button was clicked and returns the score
     * depending on the button that was clicked.
     *
     * @return the user answer as an integer to be added to array for that personality trait
     */

    public int radioButtonClicked() {
        int score = 0;
        if (RB_sAgree.isChecked()) {
            score = 5;
            RB_sAgree.setChecked(false);
        }
        if (RB_agree.isChecked()) {
            score = 4;
            RB_agree.setChecked(false);
        }
        if (RB_neutral.isChecked()) {
            score = 3;
            RB_neutral.setChecked(false);
        }
        if (RB_disagree.isChecked()) {
            score = 2;
            RB_disagree.setChecked(false);
        }
        if (RB_sDisagree.isChecked()) {
            score = 1;
            RB_sDisagree.setChecked(false);
        }
        return score;
    }

    /**
     * This method adds the score to the correct personality trait.
     *
     * @param question   the personality trait associated with the current question
     * @param userAnswer the response of the user as an integer
     */
    public void addScoreToPersonalityTrait(Question question, int userAnswer) {
        switch (question.getPersonalityTrait()) {
            case "E":
                if (question.getScoring() == 1) {
                    extroversionScore += userAnswer;
                } else {
                    extroversionScore -= userAnswer;
                }
                break;
            case "A":
                if (question.getScoring() == 1) {
                    agreeablenessScore += userAnswer;
                } else {
                    agreeablenessScore -= userAnswer;
                }
                break;
            case "C":
                if (question.getScoring() == 1) {
                    conscientiousnessScore += userAnswer;
                } else {
                    conscientiousnessScore -= userAnswer;
                }
                break;
            case "N":
                if (question.getScoring() == 1) {
                    neuroticismScore += userAnswer;
                } else {
                    neuroticismScore -= userAnswer;
                }
                break;
            case "O":
                if (question.getScoring() == 1) {
                    opennessScore += userAnswer;
                } else {
                    opennessScore -= userAnswer;
                }
                break;
        }
    }


    /**
     * This method will record the users answer and add that score to the correct personality trait
     * the score will be updated in the database for that user.
     * The method then loads the next question in the personality quiz.
     * <p>
     * When the quiz is complete the app will load the results.
     */
    private void nextQuestion() {


        int currentQuestionNum = Integer.parseInt((String) TV_questionNum.getText());


        Log.d("Question number:", String.valueOf(currentQuestionNum));

        //get the users answer for the question
        int userAnswer = radioButtonClicked();

        /*
            Default value for the user answer is 0.
            A user answer of 0 means the user has not checked a radio button.
            The user cannot loads the next question without submitting an answer
         */
        if (userAnswer == 0) {

            //Display toast to ask user to choose an answer
            Toast.makeText(PersonalityQuestion.this, "Please check an answer!",
                    Toast.LENGTH_SHORT).show();

            //If user has selected an answer continue with the quiz
        } else {

            //Printing answer for testing
            DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

            Log.d("User answer", String.valueOf(userAnswer));

            //Get the number of the current question
            //String currentQuestionStr = (String) TV_questionNum.getText();
            Question currentQuestion = dataBaseHelper.getQuestion(currentQuestionNum);
            addScoreToPersonalityTrait(currentQuestion, userAnswer);

            int nextQuestionNum = currentQuestionNum + 1;
            Log.d("########### next questionNum", Integer.toString(nextQuestionNum));

            if (nextQuestionNum > dataBaseHelper.countQuestions()) {
                loadResultsDisplay(((User) this.getApplication()).getUsername());
            } else {
                Question nextQuestion = dataBaseHelper.getQuestion(nextQuestionNum);
                Log.d("######### next question text", nextQuestion.getQuestion());
                TV_questionNum.setText(Integer.toString(nextQuestionNum));
                TV_questionDisplay.setText(nextQuestion.getQuestion());
            }


        }

    }


    public void loadResultsDisplay(String username) {
        //Add results to arrayList before going to the activity
        calcScoreList(username);
        Intent resultsIntent = new Intent(PersonalityQuestion.this, PersonalityQuizResults.class);

        startActivity(resultsIntent);
    }


    //Temporary to get all other functionality working
    private void calcScoreList(String username) {


        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        dataBaseHelper.insertUserScores(db, ((User) this.getApplication()).getUsername(),
                extroversionScore,
                agreeablenessScore,
                conscientiousnessScore,
                neuroticismScore,
                opennessScore);


    }


}

