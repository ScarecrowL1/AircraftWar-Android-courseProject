package com.example.aircraftwar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MutiChooseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muti_choose);

        Button lordButton = findViewById(R.id.MainButton);
        Button mutiButton = findViewById(R.id.mutiButton);

        lordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent().setClass(MutiChooseActivity.this, WaitPreAcitivity.class);
               startActivity(intent);
            }
        });

    }
}