package com.stu.careers_search_engine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {


    Button BTN_login;
    EditText ET_username, ET_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Objects.requireNonNull(getSupportActionBar()).hide();

        BTN_login = findViewById(R.id.BTN_login);
        ET_username = findViewById(R.id.ET_username);
        ET_password = findViewById(R.id.ET_password);

        BTN_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login();
            }
        });
    }

    public void login(){
        String username =  ET_username.getText().toString();
        String password = ET_password.getText().toString();

        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        if(dataBaseHelper.checkPassword(password) && dataBaseHelper.checkUsername(username)){
        ListHolder.getInstance().username.add(username);
        Intent loginIntent = new Intent(this, Home.class);
        startActivity(loginIntent);
    } else {
            Toast.makeText(this, "Username or password not found!", Toast.LENGTH_SHORT).show();
        }
    }
}