package com.stu.careers_search_engine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "CAREERS_SEARCH_ENGINE_DB"; // the name of our database
    private static final int DB_VERSION = 1;

    DataBaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE JOBS_TABLE (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "CAREER_AREA TEXT, " +
                "JOB_TITLE TEXT," +
                "MATCHING_TRAIT TEXT);");




        //Agreeableness jobs
        insertCareer(db,"Education", "Primary Teacher", "A");
        insertCareer(db,"Education", "Nursery Practitioner", "A");
        insertCareer(db,"Education", "High School Teacher", "A");
        insertCareer(db,"Education", "Primary Teacher", "A");
        insertCareer(db,"Education", "Special Education Teacher", "A");
        insertCareer(db,"Education", "Teaching Assistant", "A");
        insertCareer(db,"Healthcare", "Acupuncturist", "A");
        insertCareer(db,"Healthcare", "Child Psychologist", "A");
        insertCareer(db,"Healthcare", "Clinical Psychologist", "A");
        insertCareer(db,"Healthcare", "Dietitian", "A");
        insertCareer(db,"Healthcare", "Nurse", "A");
        insertCareer(db,"Healthcare", "Massage Therapist", "A");
        insertCareer(db,"Healthcare", "Nutritionist", "A");
        insertCareer(db,"Healthcare", "Personal Trainer", "A");
        insertCareer(db,"Healthcare", "Pharmacist", "A");
        insertCareer(db,"Healthcare", "Psychologist", "A");
        insertCareer(db,"Healthcare", "Occupational Therapist", "A");
        insertCareer(db,"Marketing & Sales", "Fundraiser", "A");
        insertCareer(db,"Science & Tech", "Veterinarian", "A");
        insertCareer(db,"Science & Tech", "Veterinarian Technician", "A");

        //Conscientiousness jobs
        insertCareer(db,"Arts & Humanities", "Writer", "C");
        insertCareer(db,"Business", "Management Consultant", "C");
        insertCareer(db,"IT", "Technology Consultant", "C");
        insertCareer(db,"IT", "IT Consultant", "C");
        insertCareer(db,"Construction", "Surveyor", "C");
        insertCareer(db,"Finance", "Accountant", "C");
        insertCareer(db,"Finance", "Actuary", "C");
        insertCareer(db,"Finance", "Accountant", "C");
        insertCareer(db,"Finance", "Bookkeeper", "C");
        insertCareer(db,"Finance", "Claims Adjuster", "C");
        insertCareer(db,"Finance", "Controller", "C");
        insertCareer(db,"Finance", "Financial Analyst", "C");
        insertCareer(db,"Finance", "Insurance Underwriter", "C");
        insertCareer(db,"Healthcare", "Anesthesiologist", "C");
        insertCareer(db,"Healthcare", "Physician (General)", "C");
        insertCareer(db,"Healthcare", "Physician (Specialist)", "C");
        insertCareer(db,"Healthcare", "Podiatrist", "C");
        insertCareer(db,"Marketing & Sales", "Copywriter", "C");
        insertCareer(db,"Public Services", "Detective", "C");
        insertCareer(db,"Public Services", "Armed Forces", "C");
        insertCareer(db,"Public Services", "Private Investigator", "C");
        insertCareer(db,"Science & Tech", "Research Technician", "C");
        insertCareer(db,"Transport & Logistics", "Pilot", "C");

        //Extroversion jobs
        insertCareer(db,"Business", "Human Resources", "E");
        insertCareer(db,"Business", "Industrial - Organizational Psychologist", "E");
        insertCareer(db,"Business", "Operations Manager", "E");
        insertCareer(db,"Business", "Public Relations Manager", "E");
        insertCareer(db,"Business", "Purchasing Manager", "E");
        insertCareer(db,"Business", "Retail Manager", "E");
        insertCareer(db,"Construction", "Construction Manager", "E");
        insertCareer(db,"Healthcare", "Counselor", "E");
        insertCareer(db,"Healthcare", "Care Assistant", "E");
        insertCareer(db,"Healthcare", "Therapist", "E");
        insertCareer(db,"Hospitality & Tourism", "Server (Waiter/Waitress)", "E");
        insertCareer(db,"Marketing & Sales", "Account Executive", "E");
        insertCareer(db,"Marketing & Sales", "Account Manager", "E");
        insertCareer(db,"Marketing & Sales", "Event Planner", "E");
        insertCareer(db,"Marketing & Sales", "Real Estate Agent", "E");
        insertCareer(db,"Marketing & Sales", "Sales Manager", "E");
        insertCareer(db,"Marketing & Sales", "Sales Representative", "E");
        insertCareer(db,"Media & Film", "Journalist", "E");
        insertCareer(db,"Transport & Logistics", "Warehouse Manager", "E");

        //Neuroticism jobs
        insertCareer(db,"Finance", "Stockbroker", "N");
        insertCareer(db,"Finance", "Asset Manager", "N");
        insertCareer(db,"Healthcare", "Dentist", "N");
        insertCareer(db,"Healthcare", "Forensic Psychologist", "N");
        insertCareer(db,"Healthcare", "Optometrist", "N");
        insertCareer(db,"Healthcare", "Surgeon", "N");
        insertCareer(db,"Public Services", "Social Worker", "N");
        insertCareer(db,"Hospitality & tourism", "Chef", "N");
        insertCareer(db,"Hospitality & tourism", "Cook", "N");
        insertCareer(db,"Public Service", "Correctional Officer", "N");
        insertCareer(db,"Public Service", "Firefighter", "N");
        insertCareer(db,"Public Service", "Paramedic", "N");
        insertCareer(db,"Public Service", "Security Officer", "N");
        insertCareer(db,"Public Service", "Civil Servant", "N");

        //Openness jobs
        insertCareer(db,"Arts & Humanities", "Artist", "O");
        insertCareer(db,"Arts & Humanities", "Fashion Designer", "O");
        insertCareer(db,"Arts & Humanities", "Interior Designer", "O");
        insertCareer(db,"Business", "Executive", "O");
        insertCareer(db,"Hospitality & Tourism", "Travel Agent", "O");
        insertCareer(db,"Public Services", "Hairdresser", "O");
        insertCareer(db,"Public Services", "Lawyer", "O");
        insertCareer(db,"Public Services", "Solicitor", "O");
        insertCareer(db,"Marketing & Sales", "Art Director", "O");
        insertCareer(db,"Marketing & Sales", "Graphic Designer", "O");
        insertCareer(db,"Marketing & Sales", "Marketing Manager", "O");
        insertCareer(db,"Media & Film", "Director", "O");
        insertCareer(db,"Media & Film", "Editor", "O");
        insertCareer(db,"Media & Film", "Filmmaker", "O");
        insertCareer(db,"Media & Film", "Producer", "O");
        insertCareer(db,"Transport & Logistics", "Flight Attendant", "O");
        insertCareer(db,"IT", "Web Developer", "O");
        insertCareer(db,"IT", "Software Engineer", "O");


        //Creating users table
        db.execSQL("CREATE TABLE USER_TABLE( USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "USERNAME TEXT NOT NULL UNIQUE, " +
                "EMAIL_ADDRESS TEXT NOT NULL, " +
                "PASSWORD TEXT NOT NULL);");
        insertUSer(db, "stuartM", "stuart@gmail.com", "password");


        db.execSQL("CREATE TABLE USER_PERSONALITY_TEST_SCORE(USERNAME TEXT NOT NULL UNIQUE, " +
                "E_SCORE INTEGER, " +
                "A_SCORE INTEGER, " +
                "C_SCORE INTEGER, " +
                "N_SCORE INTEGER, " +
                "O_SCORE INTEGER, " +
                "FOREIGN KEY(USERNAME) REFERENCES USER_TABLE(USERNAME))");

        Log.d("M:","===================================================================");
        Log.d("M:", "Success DB!");




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS JOBS_TABLE");
        db.execSQL("DROP TABLE IF EXISTS USER_TABLE");
        db.execSQL("DROP TABLE IF EXISTS USER_PERSONALITY_TEST_SCORE");
        Log.d("######### databse", "UPDATED!!!!");
        onCreate(db);

    }

    private static void insertCareer(SQLiteDatabase db, String careerArea, String jobTitle,
                                    String matchingTrait) {
        ContentValues jobValues = new ContentValues();
        jobValues.put("CAREER_AREA", careerArea);
        jobValues.put("JOB_TITLE", jobTitle);
        jobValues.put("MATCHING_TRAIT", matchingTrait);
        db.insert("JOBS_TABLE", null, jobValues);
    }

    private static void insertUSer(SQLiteDatabase db, String username, String emailAdd,
                                     String pWord) {
        ContentValues userValues = new ContentValues();
        userValues.put("USERNAME", username);
        userValues.put("EMAIL_ADDRESS", emailAdd);
        userValues.put("PASSWORD", pWord);
        db.insert("USER_TABLE", null, userValues);
    }



    public HashMap<String, Integer> getUserPTScores(String username){

        HashMap<String, Integer> resultMap = new HashMap<>();
        String query = "SELECT E_SCORE, A_SCORE, C_SCORE, N_SCORE, O_SCORE " +
                "FROM USER_PERSONALITY_TEST_SCORE WHERE USERNAME = ?;";
        String[] args = new String[1];
        args[0] = username;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, args);
        if(cursor.moveToFirst()) {
            do {
                resultMap.put("eScore", cursor.getInt(0));
                resultMap.put("aScore", cursor.getInt(1));
                resultMap.put("cScore", cursor.getInt(2));
                resultMap.put("nScore", cursor.getInt(3));
                resultMap.put("oScore", cursor.getInt(4));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return resultMap;
    }

    public void checkUserScoreTable(){
        String query = "SELECT * FROM USER_PERSONALITY_TEST_SCORE;";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                Log.d("############# USER PT SCORE TABLE", "#########");
                Log.d("USERNAME", cursor.getString(0));
                Log.d("eScore", Integer.toString(cursor.getInt(1)));
                Log.d("aScore", Integer.toString(cursor.getInt(2)));
                Log.d("cScore", Integer.toString(cursor.getInt(3)));
                Log.d("nScore", Integer.toString(cursor.getInt(4)));
                Log.d("oScore", Integer.toString(cursor.getInt(5)));
            } while(cursor.moveToNext());

        }

    }



    public List<Career> getHighMatchingJobs(String highestTrait){

        List<Career> returnList = new ArrayList<>();

        String query = "SELECT * FROM JOBS_TABLE WHERE MATCHING_TRAIT LIKE ?;";
        String[] args = new String[1];
        Log.d("############################ highest trait param:", highestTrait);
        args[0] = highestTrait;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, args);

        if(cursor.moveToFirst()){
            //Loop through the results
            do {
                int id = cursor.getInt(0);
                String careerArea = cursor.getString(1);
                String jobTitle = cursor.getString(2);
                String matchingTrait = cursor.getString(3);
                Career career = new Career(id, careerArea, jobTitle, matchingTrait);
                returnList.add(career);

            } while(cursor.moveToNext());

        }

        cursor.close();
        db.close();
        return returnList;
    }
}
