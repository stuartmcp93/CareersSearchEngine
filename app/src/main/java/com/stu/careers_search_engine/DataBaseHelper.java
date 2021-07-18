package com.stu.careers_search_engine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
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
        insertCareer(db,"Education", "Primary Teacher", "A");
        insertCareer(db,"Arts & Humanities", "Writer", "C");
        Log.d("M:","===================================================================");
        Log.d("M:", "Success DB!");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS JOBS_TABLE");
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

    public List<Career> getAllJobs(){

        List<Career> returnList = new ArrayList<>();

        String query = "SELECT * FROM JOBS_TABLE;";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

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

        } else {
            //Not add anything
        }

        cursor.close();
        db.close();
        return returnList;
    }
}
 /*public static final String JOBS_TABLE = "JOBS_TABLE";
    public static final String COLUMN_CAREER_AREA = "CAREER_AREA";
    public static final String COLUMN_JOB_TITLE = "JOB_TITLE";
    public static final String COLUMN_MATCHING_TRAIT = "MATCHING_TRAIT";
    public static final String COLUMN_ID = "ID";


    public DataBaseHelper(@Nullable Context context) {
        super(context, "careersSearchEngine.db", null, 1);
    }

    //this is called the first time a database is accessed.
    //There should be code to generate a new table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createJobTableStatement = "CREATE TABLE " + JOBS_TABLE + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CAREER_AREA + " TEXT, " + COLUMN_JOB_TITLE + " TEXT, " + COLUMN_MATCHING_TRAIT + " TEXT)";
        db.execSQL(createJobTableStatement);


        Log.d("HERE!:", "###########################################################################################################");
        Log.d("DB:", "DATABASE CREATED!!!!");

    }

    //Called when version of DB number changes.
    //It prevents previous users app from breaking and updates schema
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    public boolean insertJob(Career career){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CAREER_AREA, career.getCareerArea());
        cv.put(COLUMN_JOB_TITLE, career.getJobTitle());
        cv.put(COLUMN_MATCHING_TRAIT, career.getMatchingTrait());

        long insert = db.insert(JOBS_TABLE, null, cv);

        return insert == -1;

    }*/