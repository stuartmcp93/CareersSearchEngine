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

    ImageView IMG_home_btn, IMG_quiz_res, IMG_fav_list;
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

        //Setting variables to UI components
        LV_jobMatchesList = findViewById(R.id.LV_career_match_list);
        TV_topMatch = findViewById(R.id.TV_top_area);
        TV_topPercent = findViewById(R.id.TV_top_percent);
        TV_secondMatch = findViewById(R.id.TV_second_top);
        TV_secondPercent = findViewById(R.id.TV_second_percent);
        TV_thirdMatch = findViewById(R.id.TV_third_area);
        TV_filterBy = findViewById(R.id.TV_filter_by);
        TV_thirdPercent = findViewById(R.id.TV_third_percent);
        IMG_home_btn = findViewById(R.id.IMG_home_logo_career_res);
        IMG_fav_list = findViewById(R.id.IMG_favs_list_career_res);
        IMG_quiz_res = findViewById(R.id.IMG_quiz_res_career_res);
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

        IMG_fav_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFavsList();
            }
        });

        IMG_quiz_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadQuizRes();
            }
        });


    }

    private void loadQuizRes() {
        Intent intent = new Intent(this, PersonalityQuizResults.class);
        startActivity(intent);
    }

    private void loadFavsList() {
        Intent intent = new Intent(this, FavouritesList.class);
        startActivity(intent);
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

        //Get string value of the highest scoring personality trait for the current user
        String highestPTScore = getHighestPTScore(((User) this.getApplication()).getUsername());

        ////Create DatabaseHelper
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

        //return list of careers that match the high scoring personality trait
        return dataBaseHelper.getHighMatchingJobs(highestPTScore);
    }


    /**
     * This method returns a list of job titles from career objects to be displayed in a ListView.
     *
     * @param careerList list of careers that match current filter selection
     * @return List of job titles
     */
    public List<String> getCareerTitlesList(List<Career> careerList){

        //Create list to hold job titles
        List<String> jobTitlesMatches = new ArrayList<>();

        //Iterate through current Career objects list
        for (Career career : careerList) {

            //Call getJobTitle() on each object and add String title to list
            jobTitlesMatches.add(career.getJobTitle());

        }

        //Display toast if no titles in list
        if(careerList.size() == 0) {
            Toast.makeText(CareerMatchesDisplay.this, "Take quiz to see jobs!",
                    Toast.LENGTH_SHORT).show();
        }

        //Return the list of job titles
        return jobTitlesMatches;
    }

    /**
     * This method sets the array adapter with a list of job titles. The titles are then displayed
     * in the ListView.
     *
     * @param careerTitles list of job titles extracted from list of career objects.
     */
    public void setArrayAdapter(List<String> careerTitles){
        ArrayAdapter jobsArrayAdapter = new ArrayAdapter<>(CareerMatchesDisplay.this,
                android.R.layout.simple_list_item_1, careerTitles);
        LV_jobMatchesList.setAdapter(jobsArrayAdapter);
    }

    /**
     * This methods queries the database with the current users username and returns the lowest
     * scoring personality trait for the user.
     *
     * @param username the username of the current user
     * @return String value of lowest scoring personality trait
     */
    private String getLowestPTScore(String username) {

        //Create new databaseHelper
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

        //Get HashMap of the users score for each personality trait
        HashMap<String, Integer> userResultMap = dataBaseHelper.getUserPTScores(username);

        //Hold key value of lowest personality trait score from the HashMap
        String res = "";

        //Set a minimum value to a high integer to compare personality scores
        //Set lowest score to the minimum
        int min = 1000;

        //Iterate user score HashMap
        for (Map.Entry<String, Integer> entry : userResultMap.entrySet())
        {

            //Set minimum and result if lower than minimum integer
            if(entry.getValue() <= min){
                min = entry.getValue();
                res = entry.getKey();
            }
        }

        //Pass the key value of lowest personality trait to get string value of trait
        return personalityTraitFromScore(res);

    }

    /**
     * This methods queries the database with the current users username and returns the highest
     * scoring personality trait for the user.
     *
     * @param username the username of the current user
     * @return String value of highest scoring personality trait
     */
    private String getHighestPTScore(String username) {

        //Create new databaseHelper
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

        //Get HashMap of the users score for each personality trait
        HashMap<String, Integer> userResultMap = dataBaseHelper.getUserPTScores(username);

        //Hold key value of lowest personality trait score from the HashMap
        String res = "";

        //Set max value to compare scores of each trait
        int max = 0;

        //iterate through HashMap of user scores
        for (Map.Entry<String, Integer> entry : userResultMap.entrySet())
        {
            //Set res to key value of highest score
            if(entry.getValue() > max){
                max = entry.getValue();
                res = entry.getKey();
            }
        }

        //Pass res string to get personality trait string
        return personalityTraitFromScore(res);
    }

    /**
     * This method returns the string of the personality trait that matches the column in the
     * USER_PERSONALITY_TEST_SCORE table in the database.
     *
     * @param res result from database query
     * @return String value of personality trait that matches column header in the database
     */
    public String personalityTraitFromScore(String res){

        //Hold trait string
        String trait = "";

        //Compare res String that matches a column and return the trait letter
        switch (res) {
            case "eScore":
                trait = "E";
                return trait;

            case "aScore":
                trait = "A";
                return trait;

            case "cScore":
                trait = "C";
                return trait;

            case "nScore":
                trait = "N";
                return trait;

            case "oScore":
                trait = "O";
                return trait;
        }

        //return the personality trait string
        return trait;

    }


    /**
     * This method starts a new activity to display information about job when the user clicks
     * on a job title in the ListView.
     *
     * @param careerToDisplay career object to bedisplayed in the DisplayJobInfo activity
     */
    public void displayJobInfo(Career careerToDisplay){

        //Create intent for new activity
        Intent displayJobIntent = new Intent(this, DisplayJobInfo.class);

        //Add career object to intent
        displayJobIntent.putExtra("career obj", careerToDisplay);

        startActivity(displayJobIntent);
    }



    /**
     * This method returns the user to the Home activity when they click the home button icon
     */
    private void returnHome() {
        Intent returnHome = new Intent(this, Home.class);
        startActivity(returnHome);
    }



    /**
     * This method filters the job titles displayed in the ListView to display either jobs that
     * closely math the users personality score or jobs further from the users personality score.
     *
     */
    public void filterResults(){

        //Set item listener for options in the filter spinner
        SP_filterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //Hide the filter text for the user
                TV_filterBy.setVisibility(View.GONE);

                //if filter for high matching careers option is selected
                if(position == 1){

                    //Career matches list is set to get high matching jobs
                    careerMatches = getHighMatchingJobs();

                    //Set list view and top three matches display with high matching career areas
                    setMatchesDisplayAndPercent(careerMatches);

                    //If filter option for lower matching jobs is selected
                } else if(position == 2) {

                    //set careersMatches list to lower matching jobs
                    careerMatches = getLowMatchingJobs();

                    //Set list view and top three matches display with low matching career areas
                    setMatchesDisplayAndPercent(careerMatches);

                }

                //Set array adapter for new list of job titles
                List<String> titles = getCareerTitlesList(careerMatches);
                setArrayAdapter(titles);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                //Display high matching jobs if no option selected from filter spinner
                careerMatches = getHighMatchingJobs();
            }
        });
    }

    /**
     * This method sets the display at the top of the activity_career_matches_display. This sets
     * top three career areas and a percentage score.
     *
     * @param careerMatches list of career objects
     */
    public void setMatchesDisplayAndPercent(List<Career> careerMatches){

        //Hold values of percentage scores
        double topPercent, secondPercent, thirdPercent;

        //Create a HashMap of career areas of current careerMatches List and count occurrences
        HashMap<String, Integer> careerAreaAndCounts = new HashMap<>();

        //Iterate through the careerMatches list
        for(Career career : careerMatches){

            //If career area not in HashMap add it to the Map
            if(!careerAreaAndCounts.containsKey(career.getCareerArea())){
                careerAreaAndCounts.put(career.getCareerArea(), 0);
            }

            //Increment count for the career area in the HashMap
            careerAreaAndCounts.put(career.getCareerArea(),
                    careerAreaAndCounts.get(career.getCareerArea()) + 1);
        }

        //ArrayList to hold top three career areas names
        ArrayList<String> topThreeListAreas = new ArrayList<>();

        //ArrayList to hold top three career counts
        ArrayList<Integer> topThreeListCounts = new ArrayList<>();



        //Add careers and counts to list
        while(topThreeListAreas.size() < 3){

            //hold highest count value
            int highestCount = 0;

            //career area to add to the list
            String careerAreaToAdd = "";

            //Iterate through HashMap of career areas and counts
            for(Map.Entry<String, Integer> entry : careerAreaAndCounts.entrySet()){

                //Add highest value to careers areas list and count to list
                if(entry.getValue() > highestCount){
                    highestCount = entry.getValue();
                    careerAreaToAdd = entry.getKey();
                }
            }

            //Add results to Lists
            topThreeListAreas.add(careerAreaToAdd);
            topThreeListCounts.add(highestCount);

            //remove highest entry from HashMap as to avoid counting it twice
            careerAreaAndCounts.remove(careerAreaToAdd);
        }

        //Set displays to show results of career areas
        TV_topMatch.setText(topThreeListAreas.get(0));
        TV_secondMatch.setText(topThreeListAreas.get(1));
        TV_thirdMatch.setText(topThreeListAreas.get(2));

        //Get size of the list to compare counts as percentage
        double size = careerMatches.size();

        //Calculate percentage of counts for each career area
        topPercent =  (topThreeListCounts.get(0) / size) * 100;
        secondPercent = (topThreeListCounts.get(1) / size) * 100;
        thirdPercent =  (topThreeListCounts.get(2) / size) * 100;


        //Set percentage displays
        TV_topPercent.setText(Integer.toString((int) topPercent));
        TV_secondPercent.setText(Integer.toString((int) secondPercent));
        TV_thirdPercent.setText(Integer.toString((int) thirdPercent));



    }


}




