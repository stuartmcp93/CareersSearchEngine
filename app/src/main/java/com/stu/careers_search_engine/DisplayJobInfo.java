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

/**
 * This class displays information about a job that the user has clicked on in the
 * careersMatchesDisplay class or the FavouritesList class. The user can search for vacancies for
 * this job by entering a location and minimum salary.
 *
 * @Author Stuart McPherson
 */
public class DisplayJobInfo extends AppCompatActivity {
    TextView jobTitle, descriptionDisplay, salaryDisplay;
    SwitchCompat favSwitch;
    ImageView IMG_home_btn, IMG_career_matches, IMG_favourites_list;
    Button BTN_search;
    EditText ET_location, ET_salary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_data);
        Objects.requireNonNull(getSupportActionBar()).hide();


        //Get the career object to display information about from other activity
        Intent intent = getIntent();
        Career careerToDisplay = (Career) intent.getSerializableExtra("career obj");

        //Assign UI components
        BTN_search = findViewById(R.id.BTN_search_jobs);
        jobTitle = findViewById(R.id.title_data);
        descriptionDisplay = findViewById(R.id.TV_description);
        salaryDisplay = findViewById(R.id.TV_salary);
        favSwitch = findViewById(R.id.SW_add_to_fav);
        IMG_home_btn = findViewById(R.id.IMG_home_logo_job_data);
        IMG_career_matches = findViewById(R.id.IMG_career_match_job_data);
        IMG_favourites_list = findViewById(R.id.IMG_favs_job_data);
        ET_location = findViewById(R.id.ET_location);
        ET_salary = findViewById(R.id.ET_min_salary);

        //Check if career is added to user favourites list
        checkAlreadyAddedToFavs(careerToDisplay, ((User) this.getApplication()).getUsername());

        //Set displays to show job information
        jobTitle.setText(careerToDisplay.getJobTitle());
        descriptionDisplay.setText(careerToDisplay.getDescription());
        salaryDisplay.setText(careerToDisplay.getSalary());

        String username = ((User) this.getApplication()).getUsername();

        //Add or remove job from the user's favourite list
        favSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOrRemoveJobToFavourites(careerToDisplay.getId(), username);
            }
        });

        //On click listeners for navigation buttons
        IMG_home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IMG_home_btn.setColorFilter(0x800080, PorterDuff.Mode.MULTIPLY);
                returnHome();
            }
        });

        IMG_favourites_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToFavouritesList();
            }
        });

        IMG_career_matches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCareerMatches();
            }
        });

        //On click listener to search for jobs.
        BTN_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchJobs(careerToDisplay);
            }
        });

    }

    /**
     * This method loads the careerMatchesDisplay activity.
     */
    private void loadCareerMatches() {
        Intent intent = new Intent(this, CareerMatchesDisplay.class);
        startActivity(intent);
    }



    /**
     * This method reads parameters entered by the user and searches for job vacancies for the
     * current job title displayed. The method passes the parameters to the JobSearchDisplay
     * activity which displays the search results.
     *
     * @param careerToDisplay the career object currently displayed
     */

    private void searchJobs(Career careerToDisplay) {
        //The location entered
        String location = ET_location.getText().toString();

        //The minimum salary
        String salary = ET_salary.getText().toString();

        //Check salary is a numeric value for searching
        boolean isNumeric = salary.chars().allMatch( Character::isDigit );

        //Check parameters are entered correctly and display toasts for user
        if(location.isEmpty() || salary.isEmpty()){
            Toast.makeText(this, "Please enter a location and min. salary.",
                    Toast.LENGTH_SHORT).show();
        } else if(!isNumeric){
            Toast.makeText(this, "Please enter numbers for salary.",
                    Toast.LENGTH_SHORT).show();

        //Pass search parameters if entered correctly to search display activity
        }else {
            Intent intent = new Intent(this, JobSearchDisplay.class);
            intent.putExtra("career obj", careerToDisplay);
            intent.putExtra("location", location);
            intent.putExtra("salary", salary);
            startActivity(intent);

        }

    }


    /**
     * This method queries the SQLite database and adds or remove a job to the users favourite list
     * of jobs. The method is called when the user clicks the 'add to favourites' button.
     *
     * @param jobId the id of the current career object on display
     * @param username the username of the current user
     */
    public void addOrRemoveJobToFavourites(int jobId, String username) {

        //Create new database helper
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

        // Gets the data repository in write mode
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        //Check status of add to favorites button on the UI
        if (favSwitch.isChecked()) {

            //If the button is switched on add the job to the users favoruites list
            ContentValues favContent = new ContentValues();
            favContent.put("USERNAME", username);
            favContent.put("JOB_ID", jobId);
            db.insert("USER_FAVOURITES_TABLE", null, favContent);
        } else {
            //Else delete the job from the users favorites
            db.delete("USER_FAVOURITES_TABLE", "JOB_ID = ?", new String[]{Integer.toString(jobId)});

        }
    }

    /**
     * This method allows the user to navigate to their favourites list.
     */
    private void returnToFavouritesList() {
        Intent intent = new Intent(this, FavouritesList.class);
        startActivity(intent);
    }

    /**
     * This method allows the use to return to the home menu.
     */
    private void returnHome() {
        Intent returnHome = new Intent(this, Home.class);
        startActivity(returnHome);
    }

    /**
     * This method checks if the current job on display has already been added to the user's
     * favourite list. If the job has been added the 'add to favourites' switch button will appear
     * as checked when the UI display is loaded. This allows the user to remove the job from
     * their list if they wish to do so.
     *
     * @param career the current career object on display.
     * @param username the username of the current user.
     */
    public void checkAlreadyAddedToFavs(Career career, String username) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        List<Career> favsList = dataBaseHelper.getUserFavouriteList(username);
        for (Career careers : favsList) {
            if (career.getJobTitle().equals(careers.getJobTitle())) {
                favSwitch.setChecked(true);
            }
        }
    }




}