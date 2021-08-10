package com.stu.careers_search_engine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

/**
 * This class allows users to log in to the careers search application by providing their username
 * and password.
 *
 * @Autohor Stuart McPherson
 */

public class Login extends AppCompatActivity {


    Button BTN_login;
    EditText ET_username, ET_password;
    TextView TV_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Objects.requireNonNull(getSupportActionBar()).hide();

        //Assign UI components to variables
        BTN_login = findViewById(R.id.BTN_login);
        ET_username = findViewById(R.id.ET_username);
        ET_password = findViewById(R.id.ET_password);
        TV_signup = findViewById(R.id.TV_sign_up);

        //Set on click listener for login button
        BTN_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login();
            }
        });

        //Set onclick listener for sign up text
        TV_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpActivity();
            }
        });
    }

    //Prevents the user from returning to teh previous screen
    @Override
    public void onBackPressed() {
        //do nothing
    }

    /**
     * This method allows users to login to the application. It reads the values that were input
     * in the username and password edit texts in the UI and queries the database. If the credentials
     * are correct it takes the user to the home screen.
     */
    public void login(){

        //Get username and password String values
        String username =  ET_username.getText().toString();
        String password = ET_password.getText().toString();

        //Create new database helper
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

        //Check the username and password in the database
        if(dataBaseHelper.checkUsernameAndPassword(username, password)){

        //Set the user global application variable to the current user
        User user = ((User) this.getApplication()).setUsername(username);

        //Start the home activity
        Intent loginIntent = new Intent(this, Home.class);
        startActivity(loginIntent);

        //Alert user of login failure
    } else {
            Toast.makeText(this, "Username or password not found!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This methods starts the sign up activity.
     */
    public void signUpActivity(){
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
}