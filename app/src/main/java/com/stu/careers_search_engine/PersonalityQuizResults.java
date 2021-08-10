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

/**
 * This class displays the users scores in a pie chart. It also shows numerical scores for each of
 * the five personality traits measured. Users can click on the trait and an explanation of the
 * trait is displayed.
 *
 * @Author Stuart McPherson
 */
public class PersonalityQuizResults extends AppCompatActivity {

    TextView TV_con, TV_agreeableness, TV_neuroticism, TV_openness, TV_extroversion;
    PieChart pieChart;
    Button BTN_close;
    RelativeLayout explanationView;
    ImageView IMG_home, IMG_career_matches_display, IMG_favs_display;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personality_quiz_results);
        Objects.requireNonNull(getSupportActionBar()).hide();

        //Assign UI components
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

        //Create new DatabaseHelper
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

        //Get the users personality scores from the database
        HashMap<String, Integer> userResultMap = dataBaseHelper.getUserPTScores(
                ((User) this.getApplication()).getUsername());

        //No results for the user means they haven not taken the quiz and results cannot be displayed
        if(userResultMap.isEmpty()){

            //Display message for user if no results
            Toast.makeText(this, "Take quiz to see results", Toast.LENGTH_SHORT).show();
        }

        //Set the data scores in the pie chart - if no score default is 0
        setData(userResultMap.getOrDefault("eScore", 0),
                userResultMap.getOrDefault("aScore", 0),
                userResultMap.getOrDefault("cScore", 0),
                userResultMap.getOrDefault("nScore", 0),
                userResultMap.getOrDefault("oScore", 0));


        //Set on click listener for close button - it hides the explanation text of the trait
        BTN_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                explanationView.setVisibility(View.GONE);

            }
        });

        //Navigation button to go to career matches display
        IMG_career_matches_display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCareerMatches();
            }
        });

        //Navigation button to go to the user favourite list
        IMG_favs_display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFavouritesList();
            }
        });

        //Navigation button to go to the home activity
        IMG_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IMG_home.setColorFilter(0x800080, PorterDuff.Mode.MULTIPLY);
                returnHome();
            }
        });

        //Set on click listener on extroversion text view to display explanation of the trait
        TV_extroversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTraitDescription("Extroversion");
            }
        });

        //Set on click listener on agreeableness text view to display explanation of the trait
        TV_agreeableness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTraitDescription("Agreeableness");
            }
        });

        //Set on click listener on Conscientiousness text view to display explanation of the trait
        TV_con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTraitDescription("Conscientiousness");
            }
        });

        //Set on click listener on Neuroticism text view to display explanation of the trait
        TV_neuroticism.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trait = TV_neuroticism.getText().toString();
                showTraitDescription("Neuroticism");
            }
        });

        //Set on click listener on openness text view to display explanation of the trait
        TV_openness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTraitDescription("Openness");
            }
        });
    }

    /**
     * This method loads the FavouriteList activity.
     */
    private void loadFavouritesList() {
        Intent intent = new Intent(this, FavouritesList.class);
        startActivity(intent);
    }

    /**
     * This method loads the career matches activity.
     */
    private void loadCareerMatches() {
        Intent matchesIntent = new Intent(this, CareerMatchesDisplay.class);
        startActivity(matchesIntent);
    }

    /**
     * This method loads the Home activity.
     */
    private void returnHome() {
        Intent homeIntent = new Intent(this, Home.class);
        startActivity(homeIntent);
    }


    /**
     *
     * This method sets the data in the pie chart and the numerical display for the user to see
     * their personality trait scores.
     *
     *
     * @param eScore the users extroversion score
     * @param aScore the users agreeableness score
     * @param cScore the users conscientiousness score
     * @param nScore the users neuroticism score
     * @param oScore the users openness score
     */
    private void setData(int eScore, int aScore, int cScore, int nScore, int oScore)
    {

        // Set the text view numerical display of the scores
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

    /**
     *
     * This method displays an explanation of the personality trait the user clicked on.
     *
     * @param traitClicked the personality trait the user clicked on.
     */
    public void showTraitDescription(String traitClicked){

        //Get text view component from UI
        TextView explanationText = findViewById(R.id.TV_explanation);

        //Change visibility status of text view
        explanationView.setVisibility(View.VISIBLE);

        //Set text in the text view
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
