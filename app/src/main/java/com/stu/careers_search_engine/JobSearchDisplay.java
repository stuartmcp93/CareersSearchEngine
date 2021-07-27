package com.stu.careers_search_engine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Objects;

public class JobSearchDisplay extends AppCompatActivity {
    TextView TV_job_title;
    Button BTN_career_matches;
    ImageView IMG_home_btn;
    ListView LV_search_display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_search_display);
        Objects.requireNonNull(getSupportActionBar()).hide();

        TV_job_title = findViewById(R.id.TV_title_search);
        BTN_career_matches = findViewById(R.id.BTN_back_to_careers);
        IMG_home_btn = findViewById(R.id.IMG_home_logo_search);
        LV_search_display = findViewById(R.id.LV_search_results);
        Intent intent = getIntent();
        Career careerToDisplay = (Career) intent.getSerializableExtra("career obj");
        TV_job_title.setText(careerToDisplay.getJobTitle());


        IMG_home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnHome();
            }
        });

        BTN_career_matches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToCareerMatches();
            }
        });


    }

    private void returnToCareerMatches() {
        Intent returnCareer = new Intent(this, CareerMatchesDisplay.class);
        startActivity(returnCareer);
    }

    private void returnHome() {
        Intent returnHome = new Intent(this, Home.class);
        startActivity(returnHome);
    }
}