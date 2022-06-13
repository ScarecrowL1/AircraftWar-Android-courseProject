package com.example.aircraftwar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MultiEndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_end);

        TextView myScore = findViewById(R.id.myscore);
        TextView oppoScore = findViewById(R.id.opposcore);

        String score = String.valueOf(Game.Score);
        myScore.setText(score);
        oppoScore.setText(Game.finally_score);

    }
}