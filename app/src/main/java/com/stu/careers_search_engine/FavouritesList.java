package com.stu.careers_search_engine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class displays the users favourite list of jobs on the UI. Users can click on jobs in the
 * list which loads the DisplayJobInfo activity to learn about the career, search for jobs and
 * add or remove the job from their favourite list.
 *
 * @Author Stuart McPherson
 */
public class FavouritesList extends AppCompatActivity {

    ImageView IMG_home_btn, IMG_quiz_res, IMG_career_matches;
    ListView favouritesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites_list);
        Objects.requireNonNull(getSupportActionBar()).hide();

        //Assign UI variables
        IMG_home_btn = findViewById(R.id.IMG_home_logo_fav_list);
        IMG_quiz_res = findViewById(R.id.IMG_quiz_res_fav_list);
        IMG_career_matches = findViewById(R.id.IMG_career_match_fav_list);
        favouritesList = findViewById(R.id.LV_favourites_list);


        //Create new database helper to query the database
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

        //Create list of Career objects by querying the database. The list will hold
        // career objects the user has previously favoured to be displayed in the UI
        List<Career> careerFavouritesList = dataBaseHelper.getUserFavouriteList(
                ((User) this.getApplication()).getUsername());

        //Create toast if list is empty to update user on system status
        if(careerFavouritesList.size() == 0){
            Toast.makeText(FavouritesList.this, "Favourites list is empty!",
                    Toast.LENGTH_SHORT).show();
        }

        //Create list of job titles to be displayed in the list
        List<String> jobTitlesMatches = new ArrayList<>();

        //Get job titles from the list of the users favoured jobs
        for (Career career : careerFavouritesList) {
            jobTitlesMatches.add(career.getJobTitle());

        }

        //Create array adapter to display job titles in the list view
        ArrayAdapter jobsArrayAdapter = new ArrayAdapter<>(FavouritesList.this,
                android.R.layout.simple_list_item_1, jobTitlesMatches);
        favouritesList.setAdapter(jobsArrayAdapter);

        //Set on click listener on items in the list view. This allows users to click on the
        // job title and go to DisplayJobInfo for this career.
       favouritesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Career careerToDisplay = careerFavouritesList.get(position);

                displayJobInfo(careerToDisplay);


            }
        });


        //Set on click listeners for navigation buttons
        IMG_home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnHome();
            }
        });


        IMG_career_matches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCareerMatches();
            }
        });

        IMG_quiz_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadQuizRes();
            }
        });


    }

    /**
     * This method allows the user to return to their quiz results page.
     */
    private void loadQuizRes() {
        Intent intent = new Intent(this, PersonalityQuizResults.class);
        startActivity(intent);
    }

    /**
     * This method allows the user to return to their career matches.
     */
    private void loadCareerMatches() {
        Intent intent = new Intent(this, CareerMatchesDisplay.class);
        startActivity(intent);
    }

    /**
     *This method loads the DisplayJobInfo activity. It passes the career object
     * the user has clicked on to the activity.
     *
     * @param careerToDisplay the career object to display in the DisplayJobInfo activity
     */
    private void displayJobInfo(Career careerToDisplay) {
        Intent displayJobIntent = new Intent(this, DisplayJobInfo.class);
        displayJobIntent.putExtra("career obj", careerToDisplay);
        startActivity(displayJobIntent);
    }

    /**
     * This method allows the user to return to the home screen.
     */
    private void returnHome() {
        Intent returnHome = new Intent(this, Home.class);
        startActivity(returnHome);
    }


}