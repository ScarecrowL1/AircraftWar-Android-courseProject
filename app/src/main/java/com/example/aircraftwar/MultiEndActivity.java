package com.example.aircraftwar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MultiEndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_end);

        TextView myScore = findViewById(R.id.myscore);
        TextView oppoScore = findViewById(R.id.opposcore);
        Button backButton = findViewById(R.id.backbutton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent().setClass(MultiEndActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });

        String score = String.valueOf(Game.Score);
        myScore.setText(score);
        oppoScore.setText(Game.finally_score);

    }

}