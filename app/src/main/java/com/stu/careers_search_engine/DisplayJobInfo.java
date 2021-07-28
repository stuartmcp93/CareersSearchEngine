package com.stu.careers_search_engine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

public class DisplayJobInfo extends AppCompatActivity {
    TextView jobTitle, descriptionDisplay, salaryDisplay;
    SwitchCompat favSwitch;
    ImageView IMG_home_btn;
    Button BTN_favourites, BTN_search;
    EditText ET_location, ET_salary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_data);
        Objects.requireNonNull(getSupportActionBar()).hide();


        Intent intent = getIntent();
        Career careerToDisplay = (Career) intent.getSerializableExtra("career obj");

        //findViewById(R.id.IMG_home_logo_quiz);
        BTN_favourites = findViewById(R.id.BTN_return_fav_list);
        BTN_search = findViewById(R.id.BTN_search_jobs);
        jobTitle = findViewById(R.id.title_data);
        descriptionDisplay = findViewById(R.id.TV_description);
        salaryDisplay = findViewById(R.id.TV_salary);
        favSwitch = findViewById(R.id.SW_add_to_fav);
        IMG_home_btn = findViewById(R.id.IMG_home_logo_info);
        ET_location = findViewById(R.id.ET_location);
        ET_salary = findViewById(R.id.ET_min_salary);
        checkAlreadyAddedToFavs(careerToDisplay, ListHolder.getInstance().username.get(0));

        jobTitle.setText(careerToDisplay.getJobTitle());
        descriptionDisplay.setText(careerToDisplay.getDescription());
        salaryDisplay.setText(careerToDisplay.getSalary());

        favSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addorRemoveJobToFavourites(careerToDisplay.getId(), ListHolder.getInstance().username.get(0));
            }
        });

        IMG_home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IMG_home_btn.setColorFilter(0x800080, PorterDuff.Mode.MULTIPLY);
                returnHome();
            }
        });

        BTN_favourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToFavouritesList();
            }
        });

        BTN_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchJobs(careerToDisplay);
            }
        });

    }

    private void searchJobs(Career careerToDisplay) {
        String location = ET_location.getText().toString();
        String salary = ET_salary.getText().toString();
        boolean isNumeric = salary.chars().allMatch( Character::isDigit );
        if(location.isEmpty() || salary.isEmpty()){
            Toast.makeText(this, "Please enter a location and min. salary.",
                    Toast.LENGTH_SHORT).show();
        } else if(!isNumeric){
            Toast.makeText(this, "Please enter numbers for salary.",
                    Toast.LENGTH_SHORT).show();

        }else {
            Intent intent = new Intent(this, JobSearchDisplay.class);
            intent.putExtra("career obj", careerToDisplay);
            intent.putExtra("location", location);
            intent.putExtra("salary", salary);
            startActivity(intent);

        }

    }

    public void addorRemoveJobToFavourites(int jobId, String username) {

        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        // Gets the data repository in write mode
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        if (favSwitch.isChecked()) {

            ContentValues favContent = new ContentValues();
            favContent.put("USERNAME", username);
            favContent.put("JOB_ID", jobId);
            db.insert("USER_FAVOURITES_TABLE", null, favContent);
        } else {
            db.delete("USER_FAVOURITES_TABLE", "JOB_ID = ?", new String[]{Integer.toString(jobId)});

        }
    }

    private void returnToFavouritesList() {
        Intent intent = new Intent(this, FavouritesList.class);
        startActivity(intent);
    }

    private void returnHome() {
        Intent returnHome = new Intent(this, Home.class);
        startActivity(returnHome);
    }

    public void checkAlreadyAddedToFavs(Career career, String username) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        List<Career> favsList = dataBaseHelper.getUserFavouriteList(username);
        for (Career careers : favsList) {
            if (career.getJobTitle().equals(careers.getJobTitle())) {
                favSwitch.setChecked(true);
            }
        }
    }

    @Override
    public void onBackPressed() {
        //do nothing
        Toast.makeText(this, "Choose home or favourites list", Toast.LENGTH_SHORT).show();
    }


}