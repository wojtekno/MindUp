package com.example.myapplication_java_check;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ColorGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_game);

        getSupportFragmentManager().beginTransaction().add(R.id.parentContainer,new ColorGameIntroFragment()).commit();
    }
}
