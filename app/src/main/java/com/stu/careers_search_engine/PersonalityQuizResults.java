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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class PersonalityQuizResults extends AppCompatActivity {
    TextView TV_con, TV_agreeableness, TV_neuroticism, TV_openness, TV_extroversion;
    PieChart pieChart;
    ArrayList<Integer> extroversionResList, agreeablenessResList, conscientiousnessResList,
            neuroticismResList, opennessResList;

    //Need to change to ImageButton
    ImageView IMG_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personality_quiz_results);
        Objects.requireNonNull(getSupportActionBar()).hide();

        Intent intent = getIntent();
        extroversionResList = intent.getIntegerArrayListExtra("E score");
        agreeablenessResList = intent.getIntegerArrayListExtra("A score");
        conscientiousnessResList = intent.getIntegerArrayListExtra("C score");
        neuroticismResList = intent.getIntegerArrayListExtra("N score");
        opennessResList = intent.getIntegerArrayListExtra("O score");

        for(int score : extroversionResList){
            Log.d("Score:", String.valueOf(score));
        }


        // id's that we have given in .XML file
        TV_con = findViewById(R.id.TV_conscientiousness);
        TV_agreeableness = findViewById(R.id.TV_agreeableness);
        TV_neuroticism = findViewById(R.id.TV_neuroticism);
        TV_openness = findViewById(R.id.TV_openness);
        TV_extroversion = findViewById(R.id.TV_extroversion);
        pieChart = findViewById(R.id.piechart);

        // Creating a method setData()
        // to set the text in text view and pie chart
        setData(calcExtroversionScore(),
                calcAgreeablenessScore(),
                calcConScore(),
                calcNueScore(),
                calcOpenScore());


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

    private int calcExtroversionScore(){
        int eScore = 20;
        eScore += extroversionResList.get(0);
        eScore -= extroversionResList.get(1);
        eScore += extroversionResList.get(2);
        return eScore;
    }

    private int calcAgreeablenessScore(){
        int aScore = 14;
        aScore -= agreeablenessResList.get(0);
        aScore += agreeablenessResList.get(1);
        aScore -= agreeablenessResList.get(2);
        return aScore;
    }

    private int calcConScore(){
        int cScore = 14;
        cScore += conscientiousnessResList.get(0);
        cScore -= conscientiousnessResList.get(1);
        cScore += conscientiousnessResList.get(2);
        return cScore;
    }

    private int calcNueScore(){
        int nScore = 38;
        nScore += neuroticismResList.get(0);
        nScore -= neuroticismResList.get(1);
        nScore += neuroticismResList.get(2);
        return nScore;
    }

    private int calcOpenScore(){
        int oScore = 20;
        oScore += opennessResList.get(0);
        oScore -= opennessResList.get(1);
        oScore += opennessResList.get(2);
        return oScore;
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
