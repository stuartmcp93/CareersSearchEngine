package com.stu.careers_search_engine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Objects;

public class FavouritesList extends AppCompatActivity {

    ImageView BTN_home_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites_list);
        Objects.requireNonNull(getSupportActionBar()).hide();

        BTN_home_btn = findViewById(R.id.IMG_home_logo);

        BTN_home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BTN_home_btn.setColorFilter(0x800080, PorterDuff.Mode.MULTIPLY);
                returnHome();
            }
        });
    }

    private void returnHome() {
        Intent returnHome = new Intent(this, Home.class);
        startActivity(returnHome);
    }
}