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

public class FavouritesList extends AppCompatActivity {

    ImageView IMG_home_btn, IMG_quiz_res, IMG_career_matches;
    ListView favouritesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites_list);
        Objects.requireNonNull(getSupportActionBar()).hide();

        IMG_home_btn = findViewById(R.id.IMG_home_logo_fav_list);
        IMG_quiz_res = findViewById(R.id.IMG_quiz_res_fav_list);
        IMG_career_matches = findViewById(R.id.IMG_career_match_fav_list);
        favouritesList = findViewById(R.id.LV_favourites_list);



        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

        List<Career> careerFavouritesList = dataBaseHelper.getUserFavouriteList(
                ((User) this.getApplication()).getUsername());

        if(careerFavouritesList.size() == 0){
            Toast.makeText(FavouritesList.this, "Favourites list is empty!",
                    Toast.LENGTH_SHORT).show();
        }

        List<String> jobTitlesMatches = new ArrayList<>();
        for (Career career : careerFavouritesList) {
            jobTitlesMatches.add(career.getJobTitle());

        }
        //Toast.makeText(CareerMatchesDisplay.this, allJobs.toString(), Toast.LENGTH_LONG).show();
        ArrayAdapter jobsArrayAdapter = new ArrayAdapter<>(FavouritesList.this, android.R.layout.simple_list_item_1, jobTitlesMatches);
        favouritesList.setAdapter(jobsArrayAdapter);

       favouritesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Career careerToDisplay = careerFavouritesList.get(position);

                displayJobInfo(careerToDisplay);


            }
        });





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

    private void loadQuizRes() {
        Intent intent = new Intent(this, PersonalityQuizResults.class);
        startActivity(intent);
    }

    private void loadCareerMatches() {
        Intent intent = new Intent(this, CareerMatchesDisplay.class);
        startActivity(intent);
    }


    private void displayJobInfo(Career careerToDisplay) {
        Intent displayJobIntent = new Intent(this, DisplayJobInfo.class);
        displayJobIntent.putExtra("career obj", careerToDisplay);
        startActivity(displayJobIntent);
    }

    private void returnHome() {
        Intent returnHome = new Intent(this, Home.class);
        startActivity(returnHome);
    }


}