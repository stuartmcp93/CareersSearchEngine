package com.stu.careers_search_engine;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CareerMatchesDisplay extends AppCompatActivity {

    ImageView IMG_home_btn;



    //Wil need to read jobs from DB but to get functionality just use arrayList
    ArrayList<String> careerAreasList;

    ListView LV_jobMatchesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_matches_display);
        Objects.requireNonNull(getSupportActionBar()).hide();

        LV_jobMatchesList = findViewById(R.id.LV_career_match_list);



        IMG_home_btn = findViewById(R.id.IMG_home_logo_quiz);

        String highestPTScore = getHighestPTScore(ListHolder.getInstance().username.get(0));
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        dataBaseHelper.checkUserScoreTable();
        List<Career> careerMatches = dataBaseHelper.getHighMatchingJobs(highestPTScore);
        List<String> jobTitlesMatches = new ArrayList<>();
        for (Career career : careerMatches) {
            jobTitlesMatches.add(career.getJobTitle());

        }
        if(careerMatches.size() == 0){
            Toast.makeText(CareerMatchesDisplay.this, "Take quiz to see jobs!",
                    Toast.LENGTH_SHORT).show();
        }

        ArrayAdapter jobsArrayAdapter = new ArrayAdapter<>(CareerMatchesDisplay.this,
                android.R.layout.simple_list_item_1, jobTitlesMatches);
        LV_jobMatchesList.setAdapter(jobsArrayAdapter);

        LV_jobMatchesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Career careerToDisplay = careerMatches.get(position);

                displayJobInfo(careerToDisplay);


            }
        });

        IMG_home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IMG_home_btn.setColorFilter(0x800080, PorterDuff.Mode.MULTIPLY);
                returnHome();
            }
        });


    }


    private String getHighestPTScore(String username) {
        String highestPT = "";
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        HashMap<String, Integer> userResultMap = dataBaseHelper.getUserPTScores(username);

        String res = "";
        int max = 0;
        for (Map.Entry<String, Integer> entry : userResultMap.entrySet())
        {
            Log.d(entry.getKey(), Integer.toString(entry.getValue()));
            if(entry.getValue() > max){
                max = entry.getValue();
                res = entry.getKey();
            }
        }

        Log.d("########################## Res String:", res);
        switch (res) {
            case "eScore":
                highestPT = "E";
                Log.d("######################### HighestPT STR:", highestPT);
                return highestPT;
            case "aScore":
               highestPT = "A";
                Log.d("######################### HighestPT STR:", highestPT);
                return highestPT;
            case "cScore":
                highestPT = "C";
                Log.d("######################### HighestPT STR:", highestPT);
                return highestPT;
            case "nScore":
                highestPT = "N";
                Log.d("######################### HighestPT STR:", highestPT);
                return highestPT;
            case "oScore":
                highestPT = "O";
                Log.d("######################### HighestPT STR:", highestPT);
                return highestPT;
        }

        Log.d("######################### HighestPT STR:", highestPT);
        return highestPT;
    }

    public void displayJobInfo(Career careerToDisplay){
        Intent displayJobIntent = new Intent(this, DisplayJobInfo.class);
        displayJobIntent.putExtra("career obj", careerToDisplay);
        startActivity(displayJobIntent);

    }

    private void returnHome() {
        Intent returnHome = new Intent(this, Home.class);
        startActivity(returnHome);
    }
}




