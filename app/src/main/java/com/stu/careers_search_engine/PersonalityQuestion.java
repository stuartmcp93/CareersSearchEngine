package com.stu.careers_search_engine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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
    TextView TV_questionDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personality_question);
        Objects.requireNonNull(getSupportActionBar()).hide();

        //Just hard coding question Num but need to (de)increment, probably will read the value in
        //the display to do this

        IMG_home_btn = findViewById(R.id.IMG_home_logo_quiz);
        BTN_submit_answer = findViewById(R.id.BTN_submit_answer);
        BTN_previous_question = findViewById(R.id.BTN_previous_question);
        TV_questionDisplay = findViewById(R.id.TV_question_display);


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


        // Instantiate the RequestQueue.
        //RequestQueue queue = Volley.newRequestQueue(this);
        //String url ="http://127.0.0.1:5000/item/1";
        //String url ="http://10.0.2.2:5000/item/1";
        //String url = "http://192.168.1.9:5000/item/1";




    }
    /*
        So need to include functions that either load the next question if there is still some left
        or returns to the previous question. Also functionality to submit final answer and view the
        results. It should also hide the previous button if on the first question.
         */
    private void nextQuestion() {
        //if (questionNum < 25){ go to next question; pull question from DB and display it
        // questionNum++;
        //if (questionNum == 25){ start activity(showResults);
        //Intent submitQuizAnswers = new Intent(this, PersonalityResultsDisplay.class);
        //startActivity(submitQuizAnswers);

        //##############################THIS WORKS
        //Need to get it to connect to local host obvs!!!
        /*Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/
        //String url ="http://127.0.0.1:5000/item/1";
        //String url ="http://10.0.2.2:5000/item/1"; didn't work
        //String url = "http://192.168.1.9:5000/item/1"; 192.168.1.9
        //
        //http://10.0.2.2:8080/
        //https://stackoverflow.com/questions/9887621/accessing-localhost-of-pc-from-usb-connected-android-mobile-device
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://127.0.0.1:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        PlaceholderAPI placeholderAPI = retrofit.create(PlaceholderAPI.class);

        Call<List> call = placeholderAPI.getItem();

        call.enqueue(new Callback<List>() {
            @Override
            public void onResponse(Call<List> call, Response<List> response) {

                if (response.isSuccessful()) {
                    List posts = response.body();
                    assert posts != null;
                    Log.d("Success", posts.get(0).toString());
                    TV_questionDisplay.setText(posts.get(0).toString());
                    //TV_questionDisplay.setText("It worked!");

                } else {
                    Log.d("Yo", "Boo!");
                    return;
                }
            }

            @Override
            public void onFailure(Call<List> call, Throwable t) {
                Log.d("Yo", "Errror!");
                TV_questionDisplay.setText("oh no :-(");
            }

        });

    }

    private void returnHome() {
        Intent returnHome = new Intent(this, Home.class);
        startActivity(returnHome);
    }




}