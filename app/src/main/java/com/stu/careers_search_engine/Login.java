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

public class Login extends AppCompatActivity {


    Button BTN_login;
    EditText ET_username, ET_password;
    TextView TV_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Objects.requireNonNull(getSupportActionBar()).hide();

        BTN_login = findViewById(R.id.BTN_login);
        ET_username = findViewById(R.id.ET_username);
        ET_password = findViewById(R.id.ET_password);
        TV_signup = findViewById(R.id.TV_sign_up);

        BTN_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login();
            }
        });

        TV_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpActivity();
            }
        });
    }

    @Override
    public void onBackPressed() {
        //do nothing
    }

    public void login(){
        String username =  ET_username.getText().toString();
        String password = ET_password.getText().toString();

        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        if(dataBaseHelper.checkPassword(password) && dataBaseHelper.checkUsername(username)){
        //ListHolder.getInstance().username.add(username);
        User user = ((User) this.getApplication()).setUsername(username);
        Intent loginIntent = new Intent(this, Home.class);
        startActivity(loginIntent);
    } else {
            Toast.makeText(this, "Username or password not found!", Toast.LENGTH_SHORT).show();
        }
    }

    public void signUpActivity(){
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
}