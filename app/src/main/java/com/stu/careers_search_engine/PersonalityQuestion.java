package com.stu.careers_search_engine;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Objects;

public class PersonalityQuestion extends AppCompatActivity {

    ImageView IMG_home_btn;
    Button BTN_submit_answer, BTN_previous_question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personality_question);
        Objects.requireNonNull(getSupportActionBar()).hide();

        IMG_home_btn = findViewById(R.id.IMG_home_logo_quiz);
        BTN_submit_answer = findViewById(R.id.BTN_submit_answer);
        BTN_previous_question = findViewById(R.id.BTN_previous_question);


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
    /*
        So need to include functions that either load the next question if there is still some left
        or returns to the previous question. Also functionality to submit final answer and view the
        results. It should also hide the previous button if on the first question.
         */
    private void nextQuestion() {
        //if (questionNum < 25){ go to next question; pull question from DB and display it
        // questionNum++;
        //if (questionNum == 25){ start activity(showResults);
        Intent submitQuizAnswers = new Intent(this, PersonalityResultsDisplay.class);
        startActivity(submitQuizAnswers);
    }

    private void returnHome() {
        Intent returnHome = new Intent(this, Home.class);
        startActivity(returnHome);
    }




}