package com.stu.careers_search_engine;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * This class displays the career matches for the user after they have completed the personality
 * quiz. A list of job matches are displayed and the top three career areas that match their result.
 * The user can filter the results to view careers further from their personality type.
 *
 * @Author Stuart McPherson 29/07/2021
 */

public class CareerMatchesDisplay extends AppCompatActivity {

    ImageView IMG_home_btn;
    TextView TV_topMatch, TV_topPercent, TV_secondMatch,
            TV_secondPercent, TV_thirdMatch, TV_thirdPercent, TV_filterBy;
    List<Career> careerMatches;
    ListView LV_jobMatchesList;
    Spinner SP_filterSpinner;
    String[] spinnerOptions = { "", "High personality match", "Low personality match" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_matches_display);
        Objects.requireNonNull(getSupportActionBar()).hide();

        LV_jobMatchesList = findViewById(R.id.LV_career_match_list);
        TV_topMatch = findViewById(R.id.TV_top_area);
        TV_topPercent = findViewById(R.id.TV_top_percent);
        TV_secondMatch = findViewById(R.id.TV_second_top);
        TV_secondPercent = findViewById(R.id.TV_second_percent);
        TV_thirdMatch = findViewById(R.id.TV_third_area);
        TV_filterBy = findViewById(R.id.TV_filter_by);
        TV_thirdPercent = findViewById(R.id.TV_third_percent);
        IMG_home_btn = findViewById(R.id.IMG_home_logo_quiz);
        SP_filterSpinner = findViewById(R.id.SP_career_match_filter_spinner);

        //Get a list of high matching careers to display when activity starts
         careerMatches = getHighMatchingJobs();

         //Set the top three career areas for the careers on display
         setMatchesDisplayAndPercent(careerMatches);

         //Get list of job titles to display in the ListView
         List<String> highMatchingTitles = getCareerTitlesList(careerMatches);

         //Set array adapter to display list of job titles in the list view
         setArrayAdapter(highMatchingTitles);

        //On click listener for job titles displayed in the ListView
        LV_jobMatchesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Get the career object to be displayed in the DisplayJobInfo activity
                Career careerToDisplay = careerMatches.get(position);

                //Pass career object to method to start DisplayJobInfo activity
                displayJobInfo(careerToDisplay);


            }
        });

        //Setting ArrayAdapter for the filter spinner
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item, spinnerOptions);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        SP_filterSpinner.setAdapter(aa);

        //Set on click listener for home button
        IMG_home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IMG_home_btn.setColorFilter(0x800080, PorterDuff.Mode.MULTIPLY);
                returnHome();
            }
        });

        //Call filter results function to set spinner and current list of jobs
        filterResults();


    }

    /**
     * This method searches the database for the current user and returns careers that are a low
     * match to their personality type. This method is called when the user filters results to
     * display careers that have a low match to their personality.
     *
     * @return List of Career objects
     */
    public List<Career> getLowMatchingJobs() {

        //Get string value of the lowest scoring personality trait for the current user
        String lowestPTScore = getLowestPTScore(((User) this.getApplication()).getUsername());

        //Create DatabaseHelper
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

        //return list of careers that match the low scoring personality trait
        return dataBaseHelper.getLowMatchingJobs(lowestPTScore);
    }


    /**
     *  This method searches the database for the current user and returns careers that are a high
     *  match to their personality type. This method is called when the user filters results to
     *  display careers that have a high match to their personality.
     *
     * @return List of Career objects
     */
    public List<Career> getHighMatchingJobs() {

        String highestPTScore = getHighestPTScore(ListHolder.getInstance().username.get(0));
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        //dataBaseHelper.checkUserScoreTable();

        return dataBaseHelper.getHighMatchingJobs(highestPTScore);
    }

    public List<String> getCareerTitlesList(List<Career> careerList){
        List<String> jobTitlesMatches = new ArrayList<>();
        for (Career career : careerList) {
            jobTitlesMatches.add(career.getJobTitle());

        }
        if(careerList.size() == 0) {
            Toast.makeText(CareerMatchesDisplay.this, "Take quiz to see jobs!",
                    Toast.LENGTH_SHORT).show();
        }
        return jobTitlesMatches;
    }

    public void setArrayAdapter(List<String> careerTitles){
        ArrayAdapter jobsArrayAdapter = new ArrayAdapter<>(CareerMatchesDisplay.this,
                android.R.layout.simple_list_item_1, careerTitles);
        LV_jobMatchesList.setAdapter(jobsArrayAdapter);
    }


    private String getLowestPTScore(String username) {
        String lowestPT = "";
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        HashMap<String, Integer> userResultMap = dataBaseHelper.getUserPTScores(username);
        String res = "";
        int max = 100;
        for (Map.Entry<String, Integer> entry : userResultMap.entrySet())
        {
            Log.d(entry.getKey(), Integer.toString(entry.getValue()));
            if(entry.getValue() <= max){
                max = entry.getValue();
                res = entry.getKey();
            }
        }

        Log.d("########################## Res String:", res);
        switch (res) {
            case "eScore":
                lowestPT = "E";
                Log.d("######################### HighestPT STR:", lowestPT);
                return lowestPT;
            case "aScore":
                lowestPT = "A";
                Log.d("######################### HighestPT STR:", lowestPT);
                return lowestPT;
            case "cScore":
                lowestPT = "C";
                Log.d("######################### HighestPT STR:", lowestPT);
                return lowestPT;
            case "nScore":
                lowestPT = "N";
                Log.d("######################### HighestPT STR:", lowestPT);
                return lowestPT;
            case "oScore":
                lowestPT = "O";
                Log.d("######################### HighestPT STR:", lowestPT);
                return lowestPT;
        }

        Log.d("######################### HighestPT STR:", lowestPT);
        return lowestPT;
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


    public void filterResults(){
        SP_filterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TV_filterBy.setVisibility(View.GONE);
                if(position == 1){
                    careerMatches = getHighMatchingJobs();
                    setMatchesDisplayAndPercent(careerMatches);

                } else if(position == 2) {
                    careerMatches = getLowMatchingJobs();
                    setMatchesDisplayAndPercent(careerMatches);

                }
                List<String> titles = getCareerTitlesList(careerMatches);
                setArrayAdapter(titles);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                careerMatches = getHighMatchingJobs();
            }
        });
    }

    public void setMatchesDisplayAndPercent(List<Career> careerMatches){
            double topPercent, secondPercent, thirdPercent;

        HashMap<String, Integer> careerAreaAndCounts = new HashMap<>();

        for(Career career : careerMatches){
            if(!careerAreaAndCounts.containsKey(career.getCareerArea())){
                careerAreaAndCounts.put(career.getCareerArea(), 0);
            }
            careerAreaAndCounts.put(career.getCareerArea(),
                    careerAreaAndCounts.get(career.getCareerArea()) + 1);
        }

        //create ArrayList to hold six words
        ArrayList<String> topThreeListAreas = new ArrayList<>();
        ArrayList<Integer> topThreeListCounts = new ArrayList<>();

        while(topThreeListAreas.size() < 3){
            int highestCount = 0;

            String careerAreaToAdd = "";

            for(Map.Entry<String, Integer> entry : careerAreaAndCounts.entrySet()){
                if(entry.getValue() > highestCount){
                    highestCount = entry.getValue();
                    careerAreaToAdd = entry.getKey();
                }
            }
            topThreeListAreas.add(careerAreaToAdd);
            topThreeListCounts.add(highestCount);
            Log.d("######## highest count", Integer.toString(highestCount));
            careerAreaAndCounts.remove(careerAreaToAdd);
        }

        TV_topMatch.setText(topThreeListAreas.get(0));
        TV_secondMatch.setText(topThreeListAreas.get(1));
        TV_thirdMatch.setText(topThreeListAreas.get(2));

        Log.d("################## list count 0", Integer.toString(topThreeListCounts.get(0)));

        double size = careerMatches.size();
        Log.d("########### size", Integer.toString((int) size));

        topPercent =  (double) (topThreeListCounts.get(0) / size) * 100;
        secondPercent = (double) (topThreeListCounts.get(1) / size) * 100;
        thirdPercent =  (double) (topThreeListCounts.get(2) / size) * 100;

        Log.d("########## percentage top", Integer.toString((int) topPercent));

        TV_topPercent.setText(Integer.toString((int) topPercent));
        TV_secondPercent.setText(Integer.toString((int) secondPercent));
        TV_thirdPercent.setText(Integer.toString((int) thirdPercent));



    }


}




