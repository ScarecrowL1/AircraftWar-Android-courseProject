package com.example.aircraftwar;

import static com.example.aircraftwar.Game.othergameOverFlag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class WaitDieActivity extends AppCompatActivity {
    public static boolean otherGameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_die);
        new Thread(){
            @Override
            public void run(){
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.e("123",""+othergameOverFlag);
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run(){
                while (true){
                    if(Game.gameOverFlag && othergameOverFlag){
                        Intent intent = new Intent().setClass(WaitDieActivity.this, MultiEndActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    }
                }
            }
        }.start();
    }
}