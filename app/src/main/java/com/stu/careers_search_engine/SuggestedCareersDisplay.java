package com.stu.careers_search_engine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

public class SuggestedCareersDisplay extends AppCompatActivity {

    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested_careers_display);

        barChart = findViewById(R.id.BG_careers_match);

        ArrayList<BarEntry> barEntries = new ArrayList<>();

        //add manually for display but this will be added dynamically from SQL reader
        barEntries.add(new BarEntry(44f, 0));
        barEntries.add(new BarEntry(66f, 1));
        barEntries.add(new BarEntry(88f, 2));
        barEntries.add(new BarEntry(12f, 2));
        barEntries.add(new BarEntry(2f, 4));
        barEntries.add(new BarEntry(94f, 5));
        BarDataSet barDataSet = new BarDataSet(barEntries, "Match (%)");

        ArrayList<String> xJobMatches = new ArrayList<>();
        xJobMatches.add("Technology");
        xJobMatches.add("Education");
        xJobMatches.add("Finance");
        xJobMatches.add("Health care");
        xJobMatches.add("Management");
        xJobMatches.add("Hospitality");


        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);

        /*barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);*/

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(10f);
        xAxis.setTextColor(Color.RED);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        //xAxis.setValueFormatter(new MyCustomFormatter());

        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xJobMatches));







    }
}