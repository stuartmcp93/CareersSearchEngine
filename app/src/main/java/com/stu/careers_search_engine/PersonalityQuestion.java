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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PersonalityQuestion extends AppCompatActivity {
    ImageView IMG_home_btn;
    Button BTN_submit_answer, BTN_previous_question, BTN_view_results;
    TextView TV_questionDisplay, TV_questionNum;
    RadioButton RB_sAgree, RB_agree, RB_neutral, RB_disagree, RB_sDisagree;

    //These will hold the users the score for each personality trait and
    //them to the database for the user.
    //When having working properly will have these int[] that will hold each score the question.
    // This will allow for calculation of final score
    ArrayList<Integer> extroversionScore, agreeablenessScore, conscientiousnessScore,
            neuroticismScore, opennessScore;

    ArrayList<Question> questionsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personality_question);
        Objects.requireNonNull(getSupportActionBar()).hide();


        IMG_home_btn = findViewById(R.id.IMG_home_logo_quiz);
        BTN_submit_answer = findViewById(R.id.BTN_submit_answer);
        BTN_previous_question = findViewById(R.id.BTN_previous_question);
        //BTN_view_results = findViewById(R.id.BTN_view_results);
        TV_questionDisplay = findViewById(R.id.TV_question_display);
        TV_questionNum = findViewById(R.id.TV_question_number);
        TV_questionNum.setText(getString(R.string.question_num));
        RB_sAgree = findViewById(R.id.RBTN_strongly_agree);
        RB_agree = findViewById(R.id.RBTN_agree);
        RB_neutral = findViewById(R.id.RBTN_neutral);
        RB_disagree = findViewById(R.id.RBTN_disagree);
        RB_sDisagree = findViewById(R.id.RBTN_strongly_disagree);
        questionsList = getQuestions();
        extroversionScore = new ArrayList<>();
        agreeablenessScore = new ArrayList<>();
        conscientiousnessScore = new ArrayList<>();
        neuroticismScore = new ArrayList<>();
        opennessScore = new ArrayList<>();

        TV_questionDisplay.setText(questionsList.get(0).getQuestion());


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
                nextQuestion();
            }
        });


    }

    private void returnHome() {
        Intent returnHome = new Intent(this, Home.class);
        startActivity(returnHome);
    }

    /**
     * This method reads which radio button was clicked and returns the score
     * depending on the button that was clicked.
     *
     * @return the user answer as an integer to be added to array for that personality trait
     */

    public int radioButtonClicked() {
        int score = 0;
        if (RB_sAgree.isChecked()) {
            score = 5;
            RB_sAgree.setChecked(false);
        }
        if (RB_agree.isChecked()) {
            score = 4;
            RB_agree.setChecked(false);
        }
        if (RB_neutral.isChecked()) {
            score = 3;
            RB_neutral.setChecked(false);
        }
        if (RB_disagree.isChecked()) {
            score = 2;
            RB_disagree.setChecked(false);
        }
        if (RB_sDisagree.isChecked()) {
            score = 1;
            RB_sDisagree.setChecked(false);
        }
        return score;
    }

    /**
     * This method adds the score to the correct personality trait.
     *
     * @param trait      the personality trait associated with the current question
     * @param userAnswer the response of the user as an integer
     */
    public void addScoreToPersonalityTrait(String trait, int userAnswer) {
        switch (trait) {
            case "E":
                extroversionScore.add(userAnswer);
                break;
            case "A":
                agreeablenessScore.add(userAnswer);
                break;
            case "C":
                conscientiousnessScore.add(userAnswer);
                break;
            case "N":
                neuroticismScore.add(userAnswer);
                break;
            case "O":
                opennessScore.add(userAnswer);
                break;
        }
    }

    /**
     * This method will record the users answer and add that score to the correct personality trait
     * the score will be updated in the database for that user.
     * The method then loads the next question in the personality quiz.
     * <p>
     * When the quiz is complete the app will load the results.
     */
    private void nextQuestion() {

        /*
         * Convert current question number to int. When API is working this will be incremented
         * and added to the API request string to get the next question from the DB
         */
        int currentQuestionNum = Integer.parseInt((String) TV_questionNum.getText());
        /*if (currentQuestionNum == 15) {
            loadResultsDisplay();
        }*/
        Log.d("Question number:", String.valueOf(currentQuestionNum));

        //get the users answer for the question
        int userAnswer = radioButtonClicked();

        /*
            Default value for the user answer is 0.
            A user answer of 0 means the user has not checked a radio button.
            The user cannot loads the next question without submitting an answer
         */
        if (userAnswer == 0) {

            //Display toast to ask user to choose an answer
            Toast.makeText(PersonalityQuestion.this, "Please check an answer!",
                    Toast.LENGTH_LONG).show();

            //If user has selected an answer continue with the quiz
        } else {

            //Printing answer for testing
            Log.d("User answer", String.valueOf(userAnswer));

            //Get the number of the current question
            //String currentQuestionStr = (String) TV_questionNum.getText();


            //String personalityTrait = "";
            for (Question question : questionsList) {
                if (question.getQuestionNum() == currentQuestionNum) {
                    //Passing the trait and user answer to the trait scores
                    addScoreToPersonalityTrait(question.getPersonality_trait(), userAnswer);
                    Log.d("Personality Trait:", question.getPersonality_trait());
                }




            /*
                Checking the question number against number of questions in the quiz. if quiz
                is finished calculate scores for each personality trait and display them in the
                results activity
             */ //Toast tell user answer was submitted
                //Toast.makeText(PersonalityQuestion.this, "Answer submitted", Toast.LENGTH_SHORT).show();

                //if (currentQuestionNum <= questionsList.size()) {
                //Set question number and display next question


                for (Question questions : questionsList) {
                    if (questions.getQuestionNum() == currentQuestionNum + 1) {
                        TV_questionDisplay.setText(questions.getQuestion());
                        TV_questionNum.setText(String.valueOf(questions.getQuestionNum()));
                    }
                }


            }

            if(opennessScore.size() == 3){
                loadResultsDisplay();
            }

        }

    }


    public void loadResultsDisplay() {
        //Add results to arrayList before going to the activity
        Toast.makeText(PersonalityQuestion.this, "Questions finished! Calculating score",
                Toast.LENGTH_SHORT).show();
        calcScoreList();
        Intent resultsIntent = new Intent(PersonalityQuestion.this, PersonalityQuizResults.class);

        /*
        resultsIntent.putExtra("user scores", extroversionScore);
        resultsIntent.putExtra("A score", agreeablenessScore);
        resultsIntent.putExtra("C score", conscientiousnessScore);
        resultsIntent.putExtra("N score", neuroticismScore);
        resultsIntent.putExtra("O score", opennessScore);*/

        startActivity(resultsIntent);
    }

    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    //Temporary to get all other functionality working
    private void calcScoreList() {

        //Add the extroversion score
        int eScore = 20;
        eScore += extroversionScore.get(0);
        eScore -= extroversionScore.get(1);
        eScore += extroversionScore.get(2);
        ListHolder.getInstance().userPTscore.add(eScore);


        //add the agreeableness score
        int aScore = 20;
        aScore -= agreeablenessScore.get(0);
        aScore += agreeablenessScore.get(1);
        aScore -= agreeablenessScore.get(2);
        ListHolder.getInstance().userPTscore.add(aScore);

        //add conscientiousness score
        int cScore = 20;
        cScore += conscientiousnessScore.get(0);
        cScore -= conscientiousnessScore.get(1);
        cScore += conscientiousnessScore.get(2);
        ListHolder.getInstance().userPTscore.add(cScore);

        //add Neuroticism score
        int nScore = 20;
        nScore += neuroticismScore.get(0);
        nScore += neuroticismScore.get(1);
        nScore += neuroticismScore.get(2);
        ListHolder.getInstance().userPTscore.add(nScore);

        //add Openness score
        int oScore = 20;
        oScore += opennessScore.get(0);
        oScore += opennessScore.get(1);
        oScore += opennessScore.get(2);
        ListHolder.getInstance().userPTscore.add(oScore);

    }


    /*Going to create List this way then get results and career matches to work then come back and
    change this to a database
    */
    public ArrayList<Question> getQuestions() {

        ArrayList<Question> questionsList = new ArrayList<>();

        Question one = new Question(1, "I am the life of the party.", "E");
        questionsList.add(one);
        Question two = new Question(2, "I feel little concern for others.", "A");
        questionsList.add(two);
        Question three = new Question(3, "I am always prepared.", "C");
        questionsList.add(three);
        Question four = new Question(4, "I get stressed out easily.", "N");
        questionsList.add(four);
        Question five = new Question(5, "I have a rich vocabulary.", "O");
        questionsList.add(five);
        Question six = new Question(6, "I don''t talk a lot.", "E");
        questionsList.add(six);
        Question seven = new Question(7, "I am interested in people.", "A");
        questionsList.add(seven);
        Question eight = new Question(8, "I leave my belongings around.", "C");
        questionsList.add(eight);
        Question nine = new Question(9, "I am relaxed most of the time.", "N");
        questionsList.add(nine);
        Question ten = new Question(10, "I have difficulty understanding abstract ideas.", "O");
        questionsList.add(ten);
        Question eleven = new Question(11, "I feel comfortable around people.", "E");
        questionsList.add(eleven);
        Question twelve = new Question(12, "I insult people.", "A");
        questionsList.add(twelve);
        Question thirteen = new Question(13, "I pay attention to details.", "C");
        questionsList.add(thirteen);
        Question fourteen = new Question(14, "I worry about things.", "N");
        questionsList.add(fourteen);
        Question fifteen = new Question(15, "I have a vivid imagination.", "O");
        questionsList.add(fifteen);

        return questionsList;
    }


}

