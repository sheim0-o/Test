package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        int userScore = getIntent().getIntExtra("userScore", 0);
        int maxScore = getIntent().getIntExtra("maxScore", 0);

        TextView userScoreView = findViewById(R.id.userScore);
        TextView maxScoreView = findViewById(R.id.maxScore);

        userScoreView.setText(Integer.toString(userScore));
        maxScoreView.setText(Integer.toString(maxScore));
    }
}