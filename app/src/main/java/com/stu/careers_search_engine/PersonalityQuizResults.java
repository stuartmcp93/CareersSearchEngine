package com.stu.careers_search_engine;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PersonalityQuizResults extends AppCompatActivity {

    TextView TV_con, TV_agreeableness, TV_neuroticism, TV_openness, TV_extroversion;
    PieChart pieChart;
    Button BTN_close;
    RelativeLayout explanationView;



    //Need to change to ImageButton
    ImageView IMG_home, IMG_career_matches_display, IMG_favs_display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personality_quiz_results);
        Objects.requireNonNull(getSupportActionBar()).hide();

        // id's that we have given in .XML file
        TV_con = findViewById(R.id.TV_conscientiousness);
        TV_agreeableness = findViewById(R.id.TV_agreeableness);
        TV_neuroticism = findViewById(R.id.TV_neuroticism);
        TV_openness = findViewById(R.id.TV_openness);
        TV_extroversion = findViewById(R.id.TV_extroversion);
        pieChart = findViewById(R.id.piechart);
        IMG_career_matches_display = findViewById(R.id.BTN_career_area_matches);
        IMG_favs_display = findViewById(R.id.BTN_favs_list);
        IMG_home = findViewById(R.id.IMG_home_logo_quiz_res);
        BTN_close = findViewById(R.id.BTN_close_explanation);
        explanationView = findViewById(R.id.Explanation_pop_up);


        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        HashMap<String, Integer> userResultMap = dataBaseHelper.getUserPTScores(((User) this.getApplication()).getUsername());

        if(userResultMap.isEmpty()){
            Toast.makeText(this, "Take quiz to see results", Toast.LENGTH_SHORT).show();
        }

        for (Map.Entry<String, Integer> entry : userResultMap.entrySet())
        {

            Log.d("########## Quiz results from DB:", "######");
            Log.d(entry.getKey(), Integer.toString(entry.getValue()));

        }
        setData(userResultMap.getOrDefault("eScore", 0),
                userResultMap.getOrDefault("aScore", 0),
                userResultMap.getOrDefault("cScore", 0),
                userResultMap.getOrDefault("nScore", 0),
                userResultMap.getOrDefault("oScore", 0));

        BTN_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                explanationView.setVisibility(View.GONE);

            }
        });

        IMG_career_matches_display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCareerMatches();
            }
        });

        IMG_favs_display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFavs();
            }
        });

        TV_extroversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTraitDescription("Extroversion");
            }
        });

        TV_agreeableness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTraitDescription("Agreeableness");
            }
        });

        TV_con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTraitDescription("Conscientiousness");
            }
        });

        TV_neuroticism.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trait = TV_neuroticism.getText().toString();
                showTraitDescription("Neuroticism");
            }
        });

        TV_openness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTraitDescription("Openness");
            }
        });





        IMG_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IMG_home.setColorFilter(0x800080, PorterDuff.Mode.MULTIPLY);
                returnHome();
            }
        });
    }

    private void loadFavs() {
        Intent intent = new Intent(this, FavouritesList.class);
        startActivity(intent);
    }

    private void loadCareerMatches() {
        Intent matchesIntent = new Intent(this, CareerMatchesDisplay.class);
        startActivity(matchesIntent);

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

    public void showTraitDescription(String traitClicked){
        TextView explanationText = findViewById(R.id.TV_explanation);
        explanationView.setVisibility(View.VISIBLE);
        Log.d("########## trait clicked", traitClicked);

        switch (traitClicked) {
            case "Extroversion":
                explanationText.setText(R.string.E_explain);
                break;
            case "Agreeableness":
                explanationText.setText(R.string.A_explain);
                break;
            case "Conscientiousness":
                explanationText.setText(R.string.C_explain);
                break;
            case "Neuroticism":
                explanationText.setText(R.string.N_explain);
                break;
            case "Openness":
                explanationText.setText(R.string.O_explain);
                break;
        }


    }
}
