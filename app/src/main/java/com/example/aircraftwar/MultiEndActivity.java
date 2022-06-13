package com.example.aircraftwar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class MultiEndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_end);

        TextView myScore = findViewById(R.id.myscore);
        TextView oppoScore = findViewById(R.id.opposcore);
/*        AlertDialog dialog = new AlertDialog.Builder(this).setTitle("等待中").setMessage("正在等待对方游戏结束").create();

        if(!Game.othergameOverFlag) {
            dialog.show();
        }*/

/*        @SuppressLint("HandlerLeak") final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0x11) {
                    oppoScore.setText(Game.finally_score);
                    dialog.dismiss();
                }
            }
        };*/

/*        new Thread(new Runnable() {
            @Override
            public void run() {
                //FIXME 这里直接更新ui是不行的
                //还有其他更新ui方式,runOnUiThread()等
                boolean did = false;
                while (!did) {
                    if(!Game.gameOverFlag){
                        continue;
                    }
                    if (Game.othergameOverFlag) {
                        Message message=Message.obtain();
                        message.what = 0x11;
                        handler.sendMessage(message);
                        System.out.println("对面死了");
                        did = true;
                    }
                }
            }
        }).start();*/



        String score = String.valueOf(Game.Score);
        myScore.setText(score);
        oppoScore.setText(Game.finally_score);

    }

}