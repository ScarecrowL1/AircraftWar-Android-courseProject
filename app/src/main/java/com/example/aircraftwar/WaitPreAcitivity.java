package com.example.aircraftwar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.io.IOException;
import java.net.Socket;

import SocketServe.GetThread;
import SocketServe.PostThread;

public class WaitPreAcitivity extends AppCompatActivity {
    public static boolean preFlag = false;
    public static boolean is_ready = false;
    public static boolean ismuti = false;
    public static boolean wait_is_running = true;
    public static boolean other_ready = false;
    public static boolean game_is_running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_pre_acitivity);
        Button startButton = findViewById(R.id.startButton);
        Switch preSwitch = findViewById(R.id.preSwitch);
        startButton.setEnabled(false);

        new Thread(){
            @Override
            public void run() {
                try {
                    Socket socket = new Socket("120.77.59.99", 10000);
                    new GetThread(socket).start();
                    new PostThread(socket).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (is_ready && other_ready) {
                        startButton.setEnabled(true);
                    } else {
                        startButton.setEnabled(false);
                    }
                }
            }
        });
        //点击准备按钮
        preSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //如果之前已经准备
                if(preFlag){
                    is_ready = false;
                }
                //如果之前没有准备
                else {
                    is_ready = true;
                }
                preFlag = isChecked;
            }
        });

        //点击开始游戏按钮
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ismuti = true;
                wait_is_running = false;
                game_is_running = true;
            }
        });
    }
}