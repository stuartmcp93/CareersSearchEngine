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

public class SignUp extends AppCompatActivity {
    EditText ET_password, ET_retype_password, ET_username, ET_email;
    Button BTN_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Objects.requireNonNull(getSupportActionBar()).hide();

        ET_password = findViewById(R.id.ET_password);
        ET_retype_password = findViewById(R.id.ET_retype_password);
        ET_email = findViewById(R.id.ET_email_add);
        ET_username = findViewById(R.id.ET_username);
        BTN_signup = findViewById(R.id.BTN_sign_up);

        BTN_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ET_username.getText().toString();
                String password = ET_password.getText().toString();
                String password2 = ET_retype_password.getText().toString();

                signUp(username, password, password2);

            }
        });
    }



    public boolean signUp(String username, String password, String password2) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

        String email = ET_email.getText().toString();

        if(!password.equals(password2)) {
            Toast.makeText(this, "Passwords don't match!", Toast.LENGTH_SHORT).show();
            return false;
        }


        if(dataBaseHelper.checkUsername(username)){
            Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show();
            return false;
            }


        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        dataBaseHelper.insertUser(db, username, email, password);
        Toast.makeText(this, "Creating account", Toast.LENGTH_SHORT).show();
        db.close();



        Intent intent = new Intent(this, Login.class);
        startActivity(intent);

        return true;
        }





}