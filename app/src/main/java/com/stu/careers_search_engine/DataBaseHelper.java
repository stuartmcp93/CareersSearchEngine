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
                "MATCHING_TRAIT TEXT, " +
                "DESCRIPTION TEXT," +
                " AVG_SALARY TEXT);");




        //Agreeableness jobs
        insertCareer(db,"Education", "Nursery Practitioner", "A",
                "A nursery practitioner is someone who cares, guides and supports young" +
                        " children during their early years education. Often referred to as early " +
                        "years practitioners or nursery nurses, nursery practitioners act as role models" +
                        " for children throughout the first five years of their lives.", "19,000");

        insertCareer(db,"Education", "Primary Teacher", "A",
                "Primary school teachers are responsible for teaching approved national" +
                        " curriculum subjects to pupils aged 5-11, guiding them through what is" +
                        " arguably the most important stage of their education", "27,066");

        insertCareer(db,"Education", "Secondary School Teacher", "A",
                "As a secondary school teacher, you'll need to: prepare and deliver" +
                        " lessons to classes of different ages and abilities. mark work, give" +
                        " appropriate feedback and maintain records of pupils' progress and development.",
                "29,618");

        insertCareer(db,"Education", "Special Education Teacher", "A",
                "Special educational needs teachers work with children who need additional" +
                        " support. This includes children with physical disabilities, learning, " +
                        "emotional, behavioural or communication difficulties, conditions such as " +
                        "autism, dyspraxia, dyslexia and ADHD, sensory impairments (for " +
                        "example, visual impairment), sensory processing disorder, and mental" +
                        " health issues. Special educational needs (SEN) teachers may also work" +
                        " with gifted children.", "25,314");

        insertCareer(db,"Education", "Teaching Assistant", "A",
                "Teaching or classroom assistants support teachers by helping to supervise" +
                        " activities in the classroom, working with children on an individual," +
                        " small group or whole class basis. They may also manage other learning " +
                        "support staff and report on children's progress.", "13,964");

        insertCareer(db,"Healthcare", "Acupuncturist", "A",
                "As an acupuncturist, you'll take a holistic approach to the maintenance" +
                        " of health and the management of disease. The correct manipulation of qi is" +
                        " used to treat a range of emotional and physical disorders, including" +
                        " those of a musculoskeletal, respiratory, circulatory and gynaecological nature.",
                "22,957");

        insertCareer(db,"Healthcare", "Child Psychologist", "A",
                "Carefully observe children and young people and respond to what they" +
                        " might be communicating through their behaviour and play." +
                        " provide assessment and treatment of children and adolescents as" +
                        " individuals or in a group. supervise trainee child psychotherapists" +
                        " and other therapists.", "35,645");

        insertCareer(db,"Healthcare", "Clinical Psychologist", "A",
                "As a clinical psychologist, your aim is to reduce the distress and " +
                        "improve the psychological well-being of your clients who may have a" +
                        " variety of mental or physical health conditions", "37,570");

        insertCareer(db,"Healthcare", "Dietitian", "A",
                "Dietitians translate the science of nutrition into everyday information" +
                        " about food and advise people on their food and nutrition choices.",
                "26,858");

        insertCareer(db,"Healthcare", "Nurse", "A",
                "Nurses plan and provide medical and nursing care to patients" +
                                " in hospital, at home or in other settings who are suffering from" +
                                " chronic or acute physical or mental ill health. A caring and" +
                                " compassionate nature, and the ability to deal with emotionally" +
                                " charged and pressured situations are important traits of a nurse.",
                "17,596");

        insertCareer(db,"Healthcare", "Massage Therapist", "A",
                "Massage therapists manipulate clients' muscles and soft tissues, " +
                        "treat sports injuries, and give relief to people with health issues.",
                "21,357");

        insertCareer(db,"Healthcare", "Nutritionist", "A",
                "As a nutritionist, you'll generate, assess and deliver scientific," +
                        " evidence-based nutritional advice in a variety of settings to improve" +
                        " health and well-being and to promote a healthy diet and lifestyle.",
                "24,027");

        insertCareer(db,"Healthcare", "Personal Trainer", "A",
                "A personal trainer creates one-on-one fitness programmes for their" +
                        " clients, motivating and guiding them to achieve their goals.",
                "21,739");

        insertCareer(db,"Healthcare", "Pharmacist", "A",
                "Pharmacists are healthcare professionals responsible for supplying" +
                        " medicines in the most economical and effective way possible. It is an" +
                        " applied medical science. Pharmacists constantly monitor the quality, " +
                        "safety and the use of medicines, requiring a high level of involvement" +
                        " and interaction with patients.", "37,419");

        insertCareer(db,"Healthcare", "Psychologist", "A",
                " Psychologists use psychological therapies and procedures to help " +
                        "clients overcome a range of problems including depression, addiction, " +
                        "anxiety, challenging behaviour, neurological disorders, " +
                        "mental ill health and learning disabilities.", "35,645");

        insertCareer(db,"Healthcare", "Occupational Therapist", "A",
                "As an occupational therapist, you'll empower people to carry out" +
                        " everyday tasks or occupations with more confidence and independence " +
                        "in order to improve their health, quality of life and well-being. " +
                        "Tasks often relate to self-care, work or leisure.", "28,602");

        insertCareer(db,"Marketing & Sales", "Fundraiser", "A",
                "Typical responsibilities include: approaching businesses, local " +
                        "authorities, trusts and individuals to secure funding. organising " +
                        "and helping with fundraising activities such as sponsored or promotional" +
                        " events, house-to-house and street collections. recruiting volunteers and" +
                        " coordinating their work.", "22,772");

        insertCareer(db,"Science & Tech", "Veterinarian", "A",
                "Diagnose and treat sick and injured animals. perform operations. carry" +
                        " out blood analyses, X-rays and scans. provide care for animals in " +
                        "veterinary hospitals. carry out regular health checks and give vaccinations.",
                "34,332");

        insertCareer(db,"Science & Tech", "Veterinarian Technician", "A",
                "As an animal technician, you'll need to: ensure a high level of animal " +
                        "welfare and work to improve the quality of life for laboratory animals." +
                        " Clean cages, pens, trays, equipment and fittings, to keep animals clean" +
                        " and comfortable. Monitor the condition of animals and recognise and" +
                        " resolve any behavioural problems.", "29,883");

        //Conscientiousness jobs
        insertCareer(db,"Arts & Humanities", "Writer", "C" , "", "");

        insertCareer(db,"Business", "Management Consultant", "C", "", "");

        insertCareer(db,"IT", "Technology Consultant", "C", "", "");

        insertCareer(db,"IT", "IT Consultant", "C", "", "");

        insertCareer(db,"Construction", "Surveyor", "C", "", "");

        insertCareer(db,"Finance", "Accountant", "C", "", "");

        insertCareer(db,"Finance", "Actuary", "C", "", "");

        insertCareer(db,"Finance", "Accountant", "C", "", "");

        insertCareer(db,"Finance", "Bookkeeper", "C", "", "");

        insertCareer(db,"Finance", "Claims Adjuster", "C", "", "");

        insertCareer(db,"Finance", "Controller", "C", "", "");

        insertCareer(db,"Finance", "Financial Analyst", "C", "", "");

        insertCareer(db,"Finance", "Insurance Underwriter", "C", "", "");

        insertCareer(db,"Healthcare", "Anesthesiologist", "C", "", "");

        insertCareer(db,"Healthcare", "Physician (General)", "C", "", "");

        insertCareer(db,"Healthcare", "Physician (Specialist)", "C", "", "");

        insertCareer(db,"Healthcare", "Podiatrist", "C", "", "");

        insertCareer(db,"Marketing & Sales", "Copywriter", "C", "", "");

        insertCareer(db,"Public Services", "Detective", "C", "", "");

        insertCareer(db,"Public Services", "Armed Forces", "C", "", "");

        insertCareer(db,"Public Services", "Private Investigator", "C", "", "");

        insertCareer(db,"Science & Tech", "Research Technician", "C", "", "");

        insertCareer(db,"Transport & Logistics", "Pilot", "C", "", "");

        //Extroversion jobs
        insertCareer(db,"Business", "Human Resources", "E", "", "");

        insertCareer(db,"Business", "Industrial - Organizational Psychologist", "E", "", "");

        insertCareer(db,"Business", "Operations Manager", "E", "", "");

        insertCareer(db,"Business", "Public Relations Manager", "E", "", "");

        insertCareer(db,"Business", "Purchasing Manager", "E", "", "");

        insertCareer(db,"Business", "Retail Manager", "E", "", "");

        insertCareer(db,"Construction", "Construction Manager", "E", "", "");

        insertCareer(db,"Healthcare", "Counselor", "E", "", "");

        insertCareer(db,"Healthcare", "Care Assistant", "E", "", "");

        insertCareer(db,"Healthcare", "Therapist", "E", "", "");

        insertCareer(db,"Hospitality & Tourism", "Server (Waiter/Waitress)", "E", "", "");

        insertCareer(db,"Marketing & Sales", "Account Executive", "E", "", "");

        insertCareer(db,"Marketing & Sales", "Account Manager", "E", "", "");

        insertCareer(db,"Marketing & Sales", "Event Planner", "E", "", "");

        insertCareer(db,"Marketing & Sales", "Real Estate Agent", "E", "", "");

        insertCareer(db,"Marketing & Sales", "Sales Manager", "E", "", "");

        insertCareer(db,"Marketing & Sales", "Sales Representative", "E", "", "");

        insertCareer(db,"Media & Film", "Journalist", "E", "", "");

        insertCareer(db,"Transport & Logistics", "Warehouse Manager", "E", "", "");

        //Neuroticism jobs
        insertCareer(db,"Finance", "Stockbroker", "N", "", "");

        insertCareer(db,"Finance", "Asset Manager", "N", "", "");

        insertCareer(db,"Healthcare", "Dentist", "N", "", "");

        insertCareer(db,"Healthcare", "Forensic Psychologist", "N", "", "");

        insertCareer(db,"Healthcare", "Optometrist", "N", "", "");

        insertCareer(db,"Healthcare", "Surgeon", "N", "", "");

        insertCareer(db,"Public Services", "Social Worker", "N", "", "");

        insertCareer(db,"Hospitality & tourism", "Chef", "N", "", "");

        insertCareer(db,"Hospitality & tourism", "Cook", "N", "", "");

        insertCareer(db,"Public Service", "Correctional Officer", "N", "", "");

        insertCareer(db,"Public Service", "Firefighter", "N", "", "");

        insertCareer(db,"Public Service", "Paramedic", "N", "", "");

        insertCareer(db,"Public Service", "Security Officer", "N", "", "");

        insertCareer(db,"Public Service", "Civil Servant", "N", "", "");

        //Openness jobs
        insertCareer(db,"Arts & Humanities", "Artist", "O", "", "");

        insertCareer(db,"Arts & Humanities", "Fashion Designer", "O", "", "");

        insertCareer(db,"Arts & Humanities", "Interior Designer", "O", "", "");

        insertCareer(db,"Business", "Executive", "O", "", "");

        insertCareer(db,"Hospitality & Tourism", "Travel Agent", "O", "", "");

        insertCareer(db,"Public Services", "Hairdresser", "O", "", "");

        insertCareer(db,"Public Services", "Lawyer", "O", "", "");

        insertCareer(db,"Public Services", "Solicitor", "O", "", "");

        insertCareer(db,"Marketing & Sales", "Art Director", "O", "", "");

        insertCareer(db,"Marketing & Sales", "Graphic Designer", "O", "", "");

        insertCareer(db,"Marketing & Sales", "Marketing Manager", "O", "", "");

        insertCareer(db,"Media & Film", "Director", "O", "", "");

        insertCareer(db,"Media & Film", "Editor", "O", "", "");

        insertCareer(db,"Media & Film", "Filmmaker", "O", "", "");

        insertCareer(db,"Media & Film", "Producer", "O", "", "");

        insertCareer(db,"Transport & Logistics", "Flight Attendant", "O", "", "");

        insertCareer(db,"IT", "Web Developer", "O", "", "");

        insertCareer(db,"IT", "Software Engineer", "O", "", "");


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
                                    String matchingTrait, String description, String salary) {
        ContentValues jobValues = new ContentValues();
        jobValues.put("CAREER_AREA", careerArea);
        jobValues.put("JOB_TITLE", jobTitle);
        jobValues.put("MATCHING_TRAIT", matchingTrait);
        jobValues.put("DESCRIPTION", description);
        jobValues.put("AVG_SALARY", salary);
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
                String description = cursor.getString(4);
                String salary = cursor.getString(5);
                Career career = new Career(id, careerArea, jobTitle, matchingTrait,
                        description, salary);
                returnList.add(career);

            } while(cursor.moveToNext());

        }

        cursor.close();
        db.close();
        return returnList;
    }
}
