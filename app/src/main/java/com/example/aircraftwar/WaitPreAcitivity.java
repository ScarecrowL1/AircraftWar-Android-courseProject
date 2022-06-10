package com.example.aircraftwar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class WaitPreAcitivity extends AppCompatActivity {
    boolean preFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_pre_acitivity);

        Button startButton = findViewById(R.id.startButton);
        Switch preSwitch = findViewById(R.id.preSwitch);

        startButton.setEnabled(false);

        //点击准备按钮
        preSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //如果之前已经准备
                if(preFlag){

                }
                //如果之前没有准备
                else {

                }
                preFlag = isChecked;
            }
        });

        //点击开始游戏按钮
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WaitPreAcitivity.this, "准备成功", Toast.LENGTH_SHORT).show();
            }
        });
    }
}