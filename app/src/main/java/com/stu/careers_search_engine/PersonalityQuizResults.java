package com.stu.careers_search_engine;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.Objects;

public class PersonalityQuizResults extends AppCompatActivity {
    TextView TV_con, TV_agreeableness, TV_neuroticism, TV_openness, TV_extroversion;
    PieChart pieChart;

    //Need to change to ImageButton
    ImageView IMG_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personality_quiz_results);
        Objects.requireNonNull(getSupportActionBar()).hide();

        Intent intent = getIntent();
        int extroversionScore = intent.getIntExtra("E score", 0);
        int agreeablenessScore = intent.getIntExtra("A score", 0);
        int conscientiousnessScore = intent.getIntExtra("C score", 0);
        int neuroticismScore = intent.getIntExtra("N score", 0);
        int opennessScore = intent.getIntExtra("O score", 0);

        Log.d("E score", String.valueOf(extroversionScore));
        Log.d("A score", String.valueOf(agreeablenessScore));
        Log.d("C score", String.valueOf(conscientiousnessScore));
        Log.d("N score", String.valueOf(neuroticismScore));
        Log.d("O score", String.valueOf(opennessScore));


        // Link those objects with their respective
        // id's that we have given in .XML file
        TV_con = findViewById(R.id.TV_conscientiousness);
        TV_agreeableness = findViewById(R.id.TV_agreeableness);
        TV_neuroticism = findViewById(R.id.TV_neuroticism);
        TV_openness = findViewById(R.id.TV_openness_to_exp);
        TV_extroversion = findViewById(R.id.TV_extraversion);
        pieChart = findViewById(R.id.piechart);

        // Creating a method setData()
        // to set the text in text view and pie chart
        setData(extroversionScore, agreeablenessScore, conscientiousnessScore, neuroticismScore, opennessScore);

        IMG_home = findViewById(R.id.IMG_home_logo_quiz_res);

        IMG_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IMG_home.setColorFilter(0x800080, PorterDuff.Mode.MULTIPLY);
                returnHome();
            }
        });
    }

    private void returnHome() {
        Intent homeIntent = new Intent(this, Home.class);
        startActivity(homeIntent);
    }

    private void setData(int eScore, int aScore, int cScore, int nScore, int oScore)
    {

        // Set the score
        TV_extroversion.setText(Integer.toString(eScore));
        TV_agreeableness.setText(Integer.toString(aScore));
        TV_con.setText(Integer.toString(cScore));
        TV_neuroticism.setText(Integer.toString(nScore));
        TV_openness.setText(Integer.toString(oScore));


        // Set the data and color to the pie chart
        pieChart.addPieSlice(
                new PieModel(
                        "Extroversion",
                        Float.valueOf(eScore),
                        Color.parseColor("#29B6F6")));

        pieChart.addPieSlice(
            new PieModel(
                    "Agreeableness",
                    Float.valueOf(aScore),
                    Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "Conscientiousness",
                        Float.valueOf(cScore),
                        Color.parseColor("#FFA726")));

        pieChart.addPieSlice(
                new PieModel(
                        "Neuroticism",
                        Float.valueOf(nScore),
                        Color.parseColor("#FBFE09")));
        pieChart.addPieSlice(
                new PieModel(
                        "Openness",
                        Float.valueOf(oScore),
                        Color.parseColor("#EF5350")));



        // To animate the pie chart
        pieChart.startAnimation();

    }
}
