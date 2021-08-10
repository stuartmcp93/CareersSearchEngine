package com.stu.careers_search_engine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

/**
 * This class allows new users to create an account to use the careers search engine.
 *
 * @Author Stuart McPherson
 */
public class SignUp extends AppCompatActivity {
    EditText ET_password, ET_retype_password, ET_username, ET_email;
    Button BTN_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Objects.requireNonNull(getSupportActionBar()).hide();

        //Assign UI components
        ET_password = findViewById(R.id.ET_password);
        ET_retype_password = findViewById(R.id.ET_retype_password);
        ET_email = findViewById(R.id.ET_email_add);
        ET_username = findViewById(R.id.ET_username);
        BTN_signup = findViewById(R.id.BTN_sign_up);

        //Set sign up button on click listener
        BTN_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get user entered values from UI
                String username = ET_username.getText().toString();
                String password = ET_password.getText().toString();
                String password2 = ET_retype_password.getText().toString();

                //Pass user values to signUp method
                signUp(username, password, password2);

            }
        });
    }


    /**
     * This method allows users to sign up to the careers search engine. It checks the credentials
     * entered and creates the users account.
     *
     * @param username the username entered
     * @param password the password entered
     * @param password2 the second password entered to make sure passwords match
     */
    public void signUp(String username, String password, String password2) {

        //Create new database helper
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

        //Get users email address
        String email = ET_email.getText().toString();

        //Check passwords match
        if(!password.equals(password2)) {
            Toast.makeText(this, "Passwords don't match!", Toast.LENGTH_SHORT).show();

        }

        //Check username is unique
        if(dataBaseHelper.checkUsername(username)){
            Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show();
            }

        //Open database
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        //Insert new user into the database
        dataBaseHelper.insertUser(db, username, email, password);

        //Alert user account is being created
        Toast.makeText(this, "Creating account", Toast.LENGTH_SHORT).show();

        //Close the database
        db.close();

        //Load the login activity so user can login
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        }


}