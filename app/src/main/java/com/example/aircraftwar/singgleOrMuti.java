package com.example.aircraftwar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class singgleOrMuti extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singgle_or_muti);

        //单人模式按钮
        findViewById(R.id.singleButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent().setClass(singgleOrMuti.this, MenuActivity.class));
            }
        });
    }
}