package com.stu.careers_search_engine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PersonalityQuestion extends AppCompatActivity {
    ImageView IMG_home_btn;
    Button BTN_submit_answer, BTN_previous_question;
    TextView TV_questionDisplay, TV_questionNum;
    String[] dummyQuestions, dummyPT;
    RadioButton RB_sAgree, RB_agree, RB_neutral, RB_disagree, RB_sDisagree;

    //These will hold the users the score for each personality trait and
    //them to the database for the user.
    //When having working properly will have these int[] that will hold each score the question.
    // This will allow for calculation of final score
    int extroversionScore, agreeablenessScore, conscientiousnessScore,
            neuroticismScore, opennessScore;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personality_question);
        Objects.requireNonNull(getSupportActionBar()).hide();


        //Adding array with questions for pseudocode - this will be read from API database in real app
        dummyQuestions = new String[5];
        dummyQuestions[0] = "I am the life of the party.";
        dummyQuestions[1] = "I feel little concern for others.";
        dummyQuestions[2] = "I am always prepared.";
        dummyQuestions[3] = "I get stressed out easily.";
        dummyQuestions[4] = "I have a rich vocabulary.";
        dummyPT = new String[5];
        dummyPT[0] = "E";
        dummyPT[1] = "A";
        dummyPT[2] = "C";
        dummyPT[3] = "N";
        dummyPT[4] = "O";



        IMG_home_btn = findViewById(R.id.IMG_home_logo_quiz);
        BTN_submit_answer = findViewById(R.id.BTN_submit_answer);
        BTN_previous_question = findViewById(R.id.BTN_previous_question);
        TV_questionDisplay = findViewById(R.id.TV_question_display);
        TV_questionNum = findViewById(R.id.TV_question_number);
        TV_questionNum.setText(getString(R.string.question_num));
        TV_questionDisplay.setText(dummyQuestions[0]);
        RB_sAgree = findViewById(R.id.RBTN_strongly_agree);
        RB_agree = findViewById(R.id.RBTN_agree);
        RB_neutral = findViewById(R.id.RBTN_neutral);
        RB_disagree = findViewById(R.id.RBTN_disagree);
        RB_sDisagree = findViewById(R.id.RBTN_strongly_disagree);






        //Set on click listeners
        IMG_home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IMG_home_btn.setColorFilter(0x800080, PorterDuff.Mode.MULTIPLY);
                returnHome();
            }
        });

        BTN_submit_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //nextQuestion();
                try {
                    getQuestionAndPT();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });





    }

    private void returnHome() {
        Intent returnHome = new Intent(this, Home.class);
        startActivity(returnHome);
    }

    /**
     *
     * This method reads which radio button was clicked and returns the score
     * depending on the button that was clicked.
     *
     * @return the user answer as an integer to be added to array for that personality trait
     */

    public int radioButtonClicked(){
        int score = 0;
        if(RB_sAgree.isChecked()){
            score = 5;
            RB_sAgree.setChecked(false);
        }
        if(RB_agree.isChecked()){
            score = 4;
            RB_agree.setChecked(false);
        }
        if(RB_neutral.isChecked()){
            score = 3;
            RB_neutral.setChecked(false);
        }
        if(RB_disagree.isChecked()){
            score = 2;
            RB_disagree.setChecked(false);
        }
        if(RB_sDisagree.isChecked()){
            score = 1;
            RB_sDisagree.setChecked(false);
        }
        return score;
    }

    /**
     * This method adds the score to the correct personality trait.
     *
     * @param trait the personality trait associated with the current question
     * @param userAnswer the response of the user as an integer
     */
    public void addScoreToPersonalityTrait(String trait, int userAnswer){
        switch(trait) {
            case "E":
                extroversionScore = userAnswer;
                break;
            case "A":
                agreeablenessScore = userAnswer;
                break;
            case "C":
                conscientiousnessScore = userAnswer;
                break;
            case "N":
                neuroticismScore = userAnswer;
                break;
            case "O":
                opennessScore = userAnswer;
                break;
        }
    }

    /**
     * This method will record the users answer and add that score to the correct personality trait
     * the score will be updated in the database for that user.
     * The method then loads the next question in the personality quiz.
     *
     * When the quiz is complete the app will load the results.
     */
    private void nextQuestion(){

        //get the users answer for the question
        int userAnswer = radioButtonClicked();

        /*
            Default value for the user answer is 0.
            A user answer of 0 means the user has not checked a radio button.
            The user cannot loads the next question without submitting an answer
         */
        if(userAnswer == 0){

            //Display toast to ask user to choose an answer
            Toast.makeText(PersonalityQuestion.this, "Please check an answer!",
                    Toast.LENGTH_LONG).show();

        //If user has selected an answer continue with the quiz
        } else {

            //Printing answer for testing
            Log.d("User answer", String.valueOf(userAnswer));

            //Get the number of the current question
            String currentQuestionStr = (String) TV_questionNum.getText();

            /*
             * Convert current question number to int. When API is working this will be incremented
             * and added to the API request string to get the next question from the DB
             */
            int currentQuestionNum = Integer.parseInt(currentQuestionStr);

            //Have function to grab question from DB here
            //getQuestion();

            //Passing the trait and user answer to the trait scores
            addScoreToPersonalityTrait(dummyPT[currentQuestionNum - 1], userAnswer);


            /*
                Checking the question number against number of questions in the quiz. if quiz
                is finished calculate scores for each personality trait and display them in the
                results activity
             */
            if(currentQuestionNum <= dummyQuestions.length - 1){
                //Set question number display to next question
                TV_questionNum.setText(String.valueOf(currentQuestionNum + 1));

                /*
                    Set question text to next question - when API is working this will get grabbed
                    from there.
                 */
                //getQuestionAndPT();
                TV_questionDisplay.setText(dummyQuestions[currentQuestionNum]);

                //Toast tell user answer was submitted
                Toast.makeText(PersonalityQuestion.this, "Answer submitted", Toast.LENGTH_SHORT).show();
            } else {

                //Printing out scores for testing purposes
                Log.d("E score", String.valueOf(extroversionScore));
                Log.d("A score", String.valueOf(agreeablenessScore));
                Log.d("C score", String.valueOf(conscientiousnessScore));
                Log.d("N score", String.valueOf(neuroticismScore));
                Log.d("O score", String.valueOf(opennessScore));

                Toast.makeText(PersonalityQuestion.this, "Questions finished!", Toast.LENGTH_LONG).show();
                loadResultsDisplay();
            }
        }


    }

    public void loadResultsDisplay(){
        Intent resultsIntent = new Intent(PersonalityQuestion.this, PersonalityQuizResults.class);
        resultsIntent.putExtra("E score", extroversionScore);
        resultsIntent.putExtra("A score", agreeablenessScore);
        resultsIntent.putExtra("C score", conscientiousnessScore);
        resultsIntent.putExtra("N score", neuroticismScore);
        resultsIntent.putExtra("O score", opennessScore);
        startActivity(resultsIntent);
    }





    //COME BACK TO THIS WHEN CAN GET API TO CONNECT - DO LOGIC OF FUNCTIONALITY FIRST*/
    //Will get it to return the question and PT as string[] it will take number as parameter
    public void getQuestionAndPT() throws IOException {

        //Create base holder in retrofit
        //This worked for tutorial grabbing example API but need to connect to local server
        //Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                //.addConverterFactory(GsonConverterFactory.create())
                //.build();

        /*Attempted urls:
        1 - https://192.168.1.9:5000/ & https://192.168.1.9:5000 - failed to connect
        && http://192.168.1.9:5000 & http://192.168.1.9:5000/ (Tried all these on mobile device)
        2- https://192.168.1.4:49650/
        3 - https://192.168.1.9:3000
        4 - http://192.168.1.9:8090 - gives cleartext error
        5 - http://127.0.0.1:5000 - failed to connect error (mobile device) & https://127.0.0.1:5000
        http://127.0.0.1:5000/
        6 - http://10.0.2.2:8080/ (emulator)

        * */

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:5000/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        //Implement interface methods
        PlaceholderAPI placeholderAPI = retrofit.create(PlaceholderAPI.class);

        //Hold the response in the list
        Call<List<Question>> call = placeholderAPI.getQuestions();

        //Apparently can use this if not on main thread
        //call.execute();

        Log.d("Before response:", "code is working up till here");
        //Tutorial guy did this>>>
        call.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {

                Log.d("Response:", "IT'S ALIIIIIIIIIIIIVE!!!!!!!!!!!!!!!!!");
                //If no response display HTTP code
                if(!response.isSuccessful()){
                    TV_questionDisplay.setText("Code:" + response.code());
                    return;
                }

                //Hold data from response in a list
                List<Question> questions = response.body();


                assert questions != null;
                String questionToDisplay = questions.get(1).getQuestion();
                TV_questionDisplay.setText(questionToDisplay);
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                Log.d("Error:", t.getMessage());
                //Display error message if no response from the server
                TV_questionDisplay.setText(t.getMessage());
            }
        });



    }

}