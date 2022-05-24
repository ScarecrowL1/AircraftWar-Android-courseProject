package com.example.aircraftwar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import game_difficulty_method.*;

/**
 * 菜单界面
 *
 * @author ScarecrowLi
 * @date 2022/05/17
 */
public class MenuActivity extends AppCompatActivity {
    public static difficulty_method level = null;

    /**
     * 音效开关
     */
    public static boolean soundOn = true ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_menu);
        Button easyButton = findViewById(R.id.easyButton);
        Button casualButton = findViewById(R.id.casualButton);
        Button hardButton = findViewById(R.id.hardButton);

        Switch soundSwitch = findViewById(R.id.switch1);

        //简单按钮
        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MenuActivity.this, MainActivity.class);
                startActivity(intent);
                level = new easy_difficulty();
                finish();
            }
        });

        //普通按钮
        casualButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MenuActivity.this, MainActivity.class);
                startActivity(intent);
                level = new normal_difficulty();
                finish();
            }
        });

        //困难按钮
        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MenuActivity.this, MainActivity.class);
                startActivity(intent);
                level = new hard_difficulty();
                finish();
            }
        });

        soundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                soundOn = isChecked;
            }
        });


    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    public void finishReback(View v){
        startActivity(new Intent().setClass(MenuActivity.this, singgleOrMuti.class));
        finish();
    }
}