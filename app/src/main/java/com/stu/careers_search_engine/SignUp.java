package com.stu.careers_search_engine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
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
                signUp();

            }
        });
    }

    public void signUp() {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        String password = ET_password.getText().toString();
        String password2 = ET_retype_password.getText().toString();
        String username = ET_username.getText().toString();
        String email = ET_email.getText().toString();

        if(!password.equals(password2)) {
            Toast.makeText(this, "Passwords don't match!", Toast.LENGTH_SHORT).show();
            return;
        }


        if(dataBaseHelper.checkUsername(username)){
            Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show();
            return;
            }


        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        ContentValues userValues = new ContentValues();
        userValues.put("USERNAME", username);
        userValues.put("PASSWORD", password);
        userValues.put("EMAIL_ADDRESS", email);

        db.insert("USER_TABLE", null, userValues);
        Toast.makeText(this, "Success:" + dataBaseHelper.checkUsername(username), Toast.LENGTH_SHORT).show();
        db.close();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);


        }





}