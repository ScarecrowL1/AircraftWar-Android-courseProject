package com.example.aircraftwar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                AlertDialog.Builder builder = new AlertDialog.Builder(MutiChooseActivity.this);
                builder.setTitle("123");
            }
        });
    }
}