package com.stu.careers_search_engine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;

public class DisplayJobInfo extends AppCompatActivity {
    TextView jobTitle, descriptionDisplay, salaryDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_data);
        Objects.requireNonNull(getSupportActionBar()).hide();

        /*jobTitle.findViewById(R.id.TV_job_title_display_info);
        descriptionDisplay.findViewById(R.id.TV_description_display);
        salaryDisplay.findViewById(R.id.TV_avg_salary_display);*/

        Intent intent = getIntent();
        Career careerToDisplay = (Career) intent.getSerializableExtra("career obj");

        /*jobTitle.setText(careerToDisplay.getJobTitle());
        descriptionDisplay.setText(careerToDisplay.getDescription());
        salaryDisplay.setText(careerToDisplay.getSalary());*/
    }
}