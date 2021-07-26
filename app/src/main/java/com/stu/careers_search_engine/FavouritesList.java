package com.stu.careers_search_engine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FavouritesList extends AppCompatActivity {

    ImageView BTN_home_btn;
    ListView favouritesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites_list);
        Objects.requireNonNull(getSupportActionBar()).hide();

        BTN_home_btn = findViewById(R.id.IMG_home_logo);
        favouritesList = findViewById(R.id.LV_favourites_list);


        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        dataBaseHelper.checkUserScoreTable();
        List<Career> careerFavouritesList = dataBaseHelper.getUserFavouriteList(ListHolder.getInstance().username.get(0));

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





        BTN_home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BTN_home_btn.setColorFilter(0x800080, PorterDuff.Mode.MULTIPLY);
                returnHome();
            }
        });
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