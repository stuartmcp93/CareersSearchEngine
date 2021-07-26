package com.stu.careers_search_engine;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends Activity {

    Button BTN_takeQuiz, BTN_viewResults, BTN_suggestedCareers, BTN_favouritesList;
    TextView username;
    Spinner SP_homeSpinner;
    String[] spinnerOptions = { "", "Settings", "Log out" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        //Assign values to buttons
        BTN_takeQuiz = findViewById(R.id.BTN_take_quiz);
        BTN_viewResults = findViewById(R.id.BTN_quiz_results);
        BTN_suggestedCareers = findViewById(R.id.BTN_suggested_careers);
        BTN_favouritesList = findViewById(R.id.BTN_favourites);
        SP_homeSpinner = findViewById(R.id.SP_home_spinner);

        username = findViewById(R.id.TV_username);
        username.setText(ListHolder.getInstance().username.get(0));

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item, spinnerOptions);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        SP_homeSpinner.setAdapter(aa);
        SP_homeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 2){
                    ListHolder.getInstance().username.clear();
                    logout();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


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

    @Override
    public void onBackPressed() {
        //do nothing
    }

    public void logout() {
        Toast.makeText(Home.this, "Logging out!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Home.this, MainActivity.class);
        startActivity(intent);
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