package com.stu.careers_search_engine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.wifi.hotspot2.pps.Credential;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *This class displays the search results from the search the user created in the DisplayJobInfo
 * activity. The class displays a list of vacancies based on the user's search from the Reed.co.uk
 * job seeker API.
 *
 * @Author Stuart McPherson
 */
public class JobSearchDisplay extends AppCompatActivity {
    TextView TV_job_title;
    ImageView IMG_home_btn, IMG_favourites, IMG_career_matches;
    ListView LV_search_display;
    JobSearchResult jobSearchResultList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_search_display);
        Objects.requireNonNull(getSupportActionBar()).hide();

        //Assign UI components
        TV_job_title = findViewById(R.id.TV_title_search);
        IMG_career_matches = findViewById(R.id.IMG_career_match_search_res);
        IMG_favourites = findViewById(R.id.IMG_favs_search_res);
        IMG_home_btn = findViewById(R.id.IMG_home_logo_search_res);
        LV_search_display = findViewById(R.id.LV_search_results);

        //Get the career object from the DisplayJobInfo activity
        Intent intent = getIntent();
        Career careerToDisplay = (Career) intent.getSerializableExtra("career obj");

        //Display the job title that is being searched at the top of the screen
        TV_job_title.setText(careerToDisplay.getJobTitle());

        //Toast to inform the user that the search is being performed
        Toast.makeText(JobSearchDisplay.this, "Loading results...",
                Toast.LENGTH_LONG).show();

        //Authorization API key provided by reed.co.uk for Developers - Jobseeker APIs
        String authHeader = "Basic YTU4Nzc0ZTMtZWM5YS00NDU4LThlMDItNDVlODI1ZjAyMjRkOg==";

        //Create new retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.reed.co.uk/api/1.0/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Create interface and use retrofit object to send GET request
        PlaceholderAPI placeholderAPI = retrofit.create(PlaceholderAPI.class);

        //Convert job title to keyword search parameter
        String keyword = careerToDisplay.getJobTitle().replace(" ", "-");

        //Make call request with search parameters and return JSON object
        Call<JobSearchResult> call = placeholderAPI.getJobs(authHeader,
                keyword,
                intent.getStringExtra("location"),
                intent.getStringExtra("salary"),
                50, 25);

        //Make call to get list of JobSearchResults
        call.enqueue(new Callback<JobSearchResult>() {
            @Override
            public void onResponse(Call<JobSearchResult> call, Response<JobSearchResult> response) {

                //If response fails display error code
                if(!response.isSuccessful()){
                    TV_job_title.setText("Code: " + response.code());
                    return;
                }

                //Store successful response in a list
                jobSearchResultList =  response.body();

                //create list of individual jobs form JSON Array
                List<Result> results = jobSearchResultList.getResults();

                //Create array list to display results on the UI
                ArrayList<String> resultDisplayList = new ArrayList<>();

                //Display result info in the UI
                for(Result result : results){
                    resultDisplayList.add("Title: " + result.getJobTitle() +
                            "\n Employer: " + result.getEmployerName() +
                            "\n Location: " + result.getLocationName() +
                            "\n Min salary: " + result.getMinimumSalary());
                }

                //Alert user if the search returned no results
                if(resultDisplayList.isEmpty()){
                    Toast.makeText(JobSearchDisplay.this, "No results!",
                            Toast.LENGTH_SHORT).show();
                }

                //Set array adapter to display results in the list view
                ArrayAdapter jobsArrayAdapter = new ArrayAdapter<>(JobSearchDisplay.this,
                        android.R.layout.simple_list_item_1, resultDisplayList);
                LV_search_display.setAdapter(jobsArrayAdapter);

            }


            //Log error message if call fails
            @Override
            public void onFailure(Call<JobSearchResult> call, Throwable t) {
                Log.d("############## Error", t.getMessage());
            }
        });

        //OnClickListeners for navigation buttons
        IMG_home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //on click animation
                IMG_home_btn.setColorFilter(0x800080, PorterDuff.Mode.MULTIPLY);

                returnHome();
            }
        });

       IMG_favourites.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               //on click animation
               IMG_favourites.setColorFilter(0x800080, PorterDuff.Mode.MULTIPLY);

               favouritesList();
           }
       });

       IMG_career_matches.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               //on click animation
               IMG_career_matches.setColorFilter(0x800080, PorterDuff.Mode.MULTIPLY);

               returnToCareerMatches();
           }
       });

    }

    /**
     * This method allows the user to return to their favourites list.
     */
    private void favouritesList() {
        Intent intent = new Intent(this, FavouritesList.class);
        startActivity(intent);
    }

    /**
     * This method allows the user to return to their career matches.
     */
    private void returnToCareerMatches() {
        Intent returnCareer = new Intent(this, CareerMatchesDisplay.class);
        startActivity(returnCareer);
    }

    /**
     * This method allows the user to return to the home menu.
     */
    private void returnHome() {
        Intent returnHome = new Intent(this, Home.class);
        startActivity(returnHome);
    }


}