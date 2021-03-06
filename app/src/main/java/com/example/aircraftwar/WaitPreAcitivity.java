package com.example.aircraftwar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.Socket;

import SocketServe.GetThread;
import SocketServe.PostThread;
import game_difficulty_method.easy_difficulty;

public class WaitPreAcitivity extends AppCompatActivity {
    public static boolean preFlag = false;
    public static boolean is_ready = false;
    public static boolean ismuti = false;
    public static boolean wait_is_running = true;
    public static boolean other_ready = false;
    public static boolean game_is_running = false;
    static boolean both_ready = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_pre_acitivity);
        Switch preSwitch = findViewById(R.id.preSwitch);
        Button startButton = findViewById(R.id.startButton);

        startButton.setEnabled(false);

        @SuppressLint("HandlerLeak") final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0x11) {
                    startButton.setEnabled(true);
                }
                if (msg.what == 0x12) {
                    startButton.setEnabled(false);
                }
            }
        };



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
        new Thread(){
            boolean did = false;
            boolean preIsReady;
            boolean preOtherReady;
            boolean isChanged;
            @Override
            public void run() {
                while (wait_is_running) {
                    isChanged = ((preIsReady != is_ready) || (preOtherReady != other_ready));
                    if(!isChanged){
                        continue;
                    }
                    if (is_ready && other_ready) {
                        Message message=Message.obtain();
                        message.what = 0x11;
                        handler.sendMessage(message);
                        preIsReady = is_ready;
                        preOtherReady = other_ready;
                        System.out.println("??????");
                        both_ready = true;
                    } else {
                        preIsReady = is_ready;
                        preOtherReady = other_ready;
                        Message message=Message.obtain();
                        message.what = 0x12;
                        handler.sendMessage(message);
                        both_ready = false;
                    }
                }
            }
        }.start();
        //??????????????????
        preSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //????????????????????????
                if(preFlag){
                    is_ready = false;
                }
                //????????????????????????
                else {
                    is_ready = true;
                }
                preFlag = isChecked;
            }
        });

        //????????????????????????
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(both_ready) {
                    ismuti = true;
                    wait_is_running = false;
                    game_is_running = true;
                    Intent intent = new Intent().setClass(WaitPreAcitivity.this, MenuActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(WaitPreAcitivity.this,"???????????????",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}