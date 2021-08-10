package com.stu.careers_search_engine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This class is the home menu of the application. This class contains buttons for the user to
 * navigate to the main features of the application. This class also allows users to log out.
 *
 * @Author Stuart McPherson
 */

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

        //Display the current user's name in the welcome message
        username.setText(((User) this.getApplication()).getUsername());

        //Set options in the spinner menu
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item, spinnerOptions);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        SP_homeSpinner.setAdapter(aa);
        SP_homeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 2){
                    //Call logout method if logout selected
                    logout();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Do nothing
            }
        });


        //On click listeners for home menu buttons
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

    /**
     * This method prevents the user from pressing the back button their device to return to the
     * login. Users are given a toast to give instructions.
     */
    @Override
    public void onBackPressed() {
        Toast.makeText(Home.this, "Select \"log out\" from settings.", Toast.LENGTH_SHORT).show();
    }

    /**
     * This method allows the user to logout of the application.
     */
    public void logout() {
        Toast.makeText(Home.this, "Logging out!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Home.this, Login.class);
        startActivity(intent);
    }

    /**
     * This method takes the user to the CareerMatchesDisplay activity.
     */
    private void showSuggestedCareers() {
        Intent showSuggestedCIntent = new Intent(this, CareerMatchesDisplay.class);
        startActivity(showSuggestedCIntent);
    }

    /**
     * This method takes the user to the FavouritesList activity.
     */
    private void showFavList() {
        Intent showFavLitIntent = new Intent(this, FavouritesList.class);
        startActivity(showFavLitIntent);
    }

    /**
     * This method takes the user to personality quiz activity where they can take
     * the personality quiz.
     */
    private void startQuiz() {
        Intent startQuizIntent = new Intent(this, PersonalityQuestion.class);
        startActivity(startQuizIntent);
    }

    /**
     * This method takes the user the PersonalityQuizResults activity to view quiz results.
     */
    private void viewPersonalityQuizResults() {
        Intent viewResultsIntent = new Intent(this, PersonalityQuizResults.class);
        startActivity(viewResultsIntent);
    }




}