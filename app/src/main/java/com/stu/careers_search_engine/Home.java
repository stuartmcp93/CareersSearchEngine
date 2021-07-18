package com.stu.careers_search_engine;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends Activity {

    Button BTN_takeQuiz, BTN_viewResults, BTN_suggestedCareers, BTN_favouritesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        //Assign values to buttons
        BTN_takeQuiz = findViewById(R.id.BTN_take_quiz);
        BTN_viewResults = findViewById(R.id.BTN_quiz_results);
        BTN_suggestedCareers = findViewById(R.id.BTN_suggested_careers);
        BTN_favouritesList = findViewById(R.id.BTN_favourites);

        //On click go to personality quiz results
        BTN_viewResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPersonalityQuizResults();
            }
        });

        BTN_takeQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });

        BTN_suggestedCareers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSuggestedCareers();
            }
        });

        BTN_favouritesList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFavList();
            }
        });


    }



    private void showSuggestedCareers() {
        Intent showSuggestedCIntent = new Intent(this, CareerMatchesDisplay.class);
        startActivity(showSuggestedCIntent);
    }

    private void showFavList() {
        Intent showFavLitIntent = new Intent(this, FavouritesList.class);
        startActivity(showFavLitIntent);
    }

    private void startQuiz() {
        Intent startQuizIntent = new Intent(this, PersonalityQuestion.class);
        startActivity(startQuizIntent);
    }

    /**
     * Method to go to personality quiz results on click of view results button on home page.
     */
    private void viewPersonalityQuizResults() {
        Intent viewResultsIntent = new Intent(this, PersonalityQuizResults.class);
        startActivity(viewResultsIntent);
    }


}