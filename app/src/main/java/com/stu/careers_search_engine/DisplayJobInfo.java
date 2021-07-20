package com.stu.careers_search_engine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

public class DisplayJobInfo extends AppCompatActivity {
    TextView jobTitle, descriptionDisplay, salaryDisplay;
    SwitchCompat favSwitch;
    ImageView IMG_home_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_data);
        Objects.requireNonNull(getSupportActionBar()).hide();



        Intent intent = getIntent();
        Career careerToDisplay = (Career) intent.getSerializableExtra("career obj");

        findViewById(R.id.IMG_home_logo_quiz);
        jobTitle = findViewById(R.id.title_data);
        descriptionDisplay = findViewById(R.id.TV_description);
        salaryDisplay = findViewById(R.id.TV_salary);
        favSwitch = findViewById(R.id.SW_add_to_fav);
        IMG_home_btn = findViewById(R.id.IMG_home_logo_info);
        checkAlreadyAddedToFavs(careerToDisplay, "stuartM");

        jobTitle.setText(careerToDisplay.getJobTitle());
        descriptionDisplay.setText(careerToDisplay.getDescription());
        salaryDisplay.setText(careerToDisplay.getSalary());

        favSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addorRemoveJobToFavourites(careerToDisplay.getId(), "stuartM");
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

    public void addorRemoveJobToFavourites(int jobId, String username){

        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        // Gets the data repository in write mode
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        if(favSwitch.isChecked()){

            ContentValues favContent = new ContentValues();
            favContent.put("USERNAME", username);
            favContent.put("JOB_ID", jobId);
            db.insert("USER_FAVOURITES_TABLE", null, favContent);
        } else {
            db.delete("USER_FAVOURITES_TABLE", "JOB_ID = ?", new String[] {Integer.toString(jobId)});

        }
    }

    private void returnHome() {
        Intent returnHome = new Intent(this, Home.class);
        startActivity(returnHome);
    }

    public void checkAlreadyAddedToFavs(Career career, String username){
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        List<Career> favsList = dataBaseHelper.getUserFavouriteList(username);
        for(Career careers : favsList){
            if(career.getJobTitle().equals(careers.getJobTitle())){
                favSwitch.setChecked(true);
            }
        }


    }


}