package com.stu.careers_search_engine;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CareerMatchesDisplay extends AppCompatActivity {

    // variable for our bar chart
    BarChart barChart;
    // variable for our bar data.
    BarData barData;
    // variable for our bar data set.
    BarDataSet barDataSet;
    // array list for storing entries.
    ArrayList barEntriesArrayList;

    //Wil need to read jobs from DB but to get functionality just use arrayList
    ArrayList<String> careerAreasList;

    ListView LV_jobMatchesList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_matches_display);
        Objects.requireNonNull(getSupportActionBar()).hide();

        LV_jobMatchesList = findViewById(R.id.LV_career_match_list);

        // initializing variable for bar chart.
        barChart = findViewById(R.id.BG_careers_match);

        // calling method to get bar entries.
        getBarEntries();

        // creating a new bar data set.
        barDataSet = new BarDataSet(barEntriesArrayList, "Career areas");

        // creating a new bar data and
        // passing our bar data set.
        barData = new BarData(barDataSet);

        // below line is to set data
        // to our bar chart.
        barChart.setData(barData);

        // adding color to our bar data set.
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        // setting text color.
        barDataSet.setValueTextColor(Color.BLACK);

        // setting text size
        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(false);


        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        List<Career> careerMatches = dataBaseHelper.getAllJobs();
        List<String> jobTitlesMatches = new ArrayList<>();
        for(Career career : careerMatches){
            jobTitlesMatches.add(career.getJobTitle());
        }
        //Toast.makeText(CareerMatchesDisplay.this, allJobs.toString(), Toast.LENGTH_LONG).show();
        ArrayAdapter jobsArrayAdapter = new ArrayAdapter<>(CareerMatchesDisplay.this, android.R.layout.simple_list_item_1, jobTitlesMatches);
        LV_jobMatchesList.setAdapter(jobsArrayAdapter);

    }

    private void getBarEntries() {
        //Once have DB running the bar lists will be the top 5 career areas and their count
        // creating a new array list
        barEntriesArrayList = new ArrayList<>();

        // adding new entry to our array list with bar
        // entry and passing x and y axis value to it.
        barEntriesArrayList.add(new BarEntry(1f, 4));
        barEntriesArrayList.add(new BarEntry(2f, 6));
        barEntriesArrayList.add(new BarEntry(3f, 8));
        barEntriesArrayList.add(new BarEntry(4f, 2));
        barEntriesArrayList.add(new BarEntry(5f, 4));
        barEntriesArrayList.add(new BarEntry(6f, 1));
    }




}