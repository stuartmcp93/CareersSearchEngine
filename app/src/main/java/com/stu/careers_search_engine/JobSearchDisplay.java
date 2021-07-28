package com.stu.careers_search_engine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class JobSearchDisplay extends AppCompatActivity {
    TextView TV_job_title;
    Button BTN_career_matches;
    ImageView IMG_home_btn;
    ListView LV_search_display;
    JobSearchResult jobSearchResultList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_search_display);
        Objects.requireNonNull(getSupportActionBar()).hide();

        TV_job_title = findViewById(R.id.TV_title_search);
        BTN_career_matches = findViewById(R.id.BTN_back_to_careers);
        IMG_home_btn = findViewById(R.id.IMG_home_logo_search);
        LV_search_display = findViewById(R.id.LV_search_results);
        Intent intent = getIntent();
        Career careerToDisplay = (Career) intent.getSerializableExtra("career obj");
        TV_job_title.setText(careerToDisplay.getJobTitle());

        Toast.makeText(JobSearchDisplay.this, "Loading results...",
                Toast.LENGTH_LONG).show();

        //getting API
        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/
        String authHeader = "Basic YTU4Nzc0ZTMtZWM5YS00NDU4LThlMDItNDVlODI1ZjAyMjRkOg==";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.reed.co.uk/api/1.0/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        String keyword = careerToDisplay.getJobTitle().replace(" ", "-");
        PlaceholderAPI placeholderAPI = retrofit.create(PlaceholderAPI.class);
        //placeholderAPI.getJobs(authKey);
        //Call<List<JobSearchResult>> call = placeholderAPI.getJobs(authHeader);
        Call<JobSearchResult> call = placeholderAPI.getJobs(authHeader,
                keyword,
                intent.getStringExtra("location"),
                intent.getStringExtra("salary"),
                30, 15);

        call.enqueue(new Callback<JobSearchResult>() {
            @Override
            public void onResponse(Call<JobSearchResult> call, Response<JobSearchResult> response) {
                if(!response.isSuccessful()){
                    TV_job_title.setText("Code: " + response.code());
                    return;
                }

                Log.d("############### success", "RESPONSE!!!!!");
                jobSearchResultList =  response.body();
                List<Result> results = jobSearchResultList.getResults();
                ArrayList<String> resultDisplayList = new ArrayList<>();
                for(Result result : results){
                    resultDisplayList.add("Title: " + result.getJobTitle() +
                            "\n Employer: " + result.getEmployerName() +
                            "\n Location: " + result.getLocationName() +
                            "\n Min salary: " + result.getMinimumSalary());
                }






                if(resultDisplayList.isEmpty()){
                    Toast.makeText(JobSearchDisplay.this, "No results!", Toast.LENGTH_SHORT).show();
                }



                ArrayAdapter jobsArrayAdapter = new ArrayAdapter<>(JobSearchDisplay.this,
                        android.R.layout.simple_list_item_1, resultDisplayList);
                LV_search_display.setAdapter(jobsArrayAdapter);

            }



            @Override
            public void onFailure(Call<JobSearchResult> call, Throwable t) {
                Log.d("############## Error", t.getMessage());
            }
        });









        IMG_home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnHome();
            }
        });

        BTN_career_matches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToCareerMatches();
            }
        });


    }

    private void returnToCareerMatches() {
        Intent returnCareer = new Intent(this, CareerMatchesDisplay.class);
        startActivity(returnCareer);
    }

    private void returnHome() {
        Intent returnHome = new Intent(this, Home.class);
        startActivity(returnHome);
    }


}