package com.stu.careers_search_engine;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Objects;

public class Home extends Activity {

    Button BTN_takeQuiz, BTN_viewResults, BTN_suggestedCareers, BTN_favouritesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        //Assign values to buttons
        BTN_takeQuiz = findViewById(R.id.BTN_take_quiz);
        BTN_viewResults = findViewById(R.id.BTN_quiz_results);
        BTN_suggestedCareers = findViewById(R.id.BTN_see_career_area_matches);
        BTN_favouritesList = findViewById(R.id.BTN_favourites);

        //On click go to personality quiz results
        BTN_viewResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPersonalityQuizResults();
            }
        });


    }

    /**
     * Method to go to personality quiz results on click of view results button on home page.
     */
    private void viewPersonalityQuizResults() {
        Intent viewResultsIntent = new Intent(this, PersonalityResultsDisplay.class);
        startActivity(viewResultsIntent);
    }


}