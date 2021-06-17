package com.stu.careers_search_engine;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.Objects;

public class PersonalityResultsDisplay extends AppCompatActivity {
    TextView TV_con, TV_agreeableness, TV_neuroticism, TV_openness, TV_extraversion;
    PieChart pieChart;
    ImageView IMG_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personality_quiz_results);
        //Objects.requireNonNull(getSupportActionBar()).hide();


        // Link those objects with their respective
        // id's that we have given in .XML file
        TV_con = findViewById(R.id.TV_conscientiousness);
        TV_agreeableness = findViewById(R.id.TV_agreeableness);
        TV_neuroticism = findViewById(R.id.TV_neuroticism);
        TV_openness = findViewById(R.id.TV_openness_to_exp);
        TV_extraversion = findViewById(R.id.TV_extraversion);
        pieChart = findViewById(R.id.piechart);

        // Creating a method setData()
        // to set the text in text view and pie chart
        setData();

        IMG_home = findViewById(R.id.IMG_home_logo_quiz_res);

        IMG_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnHome();
            }
        });
    }

    private void returnHome() {
        Intent homeIntent = new Intent(this, Home.class);
        startActivity(homeIntent);
    }

    private void setData()
    {

        // Set the percentage of language used
        TV_con.setText(Integer.toString(40));
        TV_agreeableness.setText(Integer.toString(30));
        TV_neuroticism.setText(Integer.toString(5));
        TV_openness.setText(Integer.toString(25));
        TV_extraversion.setText(Integer.toString(35));

        // Set the data and color to the pie chart
        pieChart.addPieSlice(
                new PieModel(
                        "Conscientiousness",
                        Integer.parseInt(TV_con.getText().toString()),
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        "Agreeableness",
                        Integer.parseInt(TV_agreeableness.getText().toString()),
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "Neuroticism",
                        Integer.parseInt(TV_neuroticism.getText().toString()),
                        Color.parseColor("#EF5350")));
        pieChart.addPieSlice(
                new PieModel(
                        "Openness to experience",
                        Integer.parseInt(TV_openness.getText().toString()),
                        Color.parseColor("#EF5350")));
        pieChart.addPieSlice(
                new PieModel(
                        "Extraversion",
                        Integer.parseInt(TV_extraversion.getText().toString()),
                        Color.parseColor("#29B6F6")));

        // To animate the pie chart
        pieChart.startAnimation();
    }
}
