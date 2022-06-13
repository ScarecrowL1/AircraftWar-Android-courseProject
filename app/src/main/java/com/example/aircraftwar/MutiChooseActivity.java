package com.example.aircraftwar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MutiChooseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muti_choose);

        Button lordButton = findViewById(R.id.MainButton);
        Button mutiButton = findViewById(R.id.pipeiButton);

        lordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent().setClass(MutiChooseActivity.this, WaitPreAcitivity.class);
               startActivity(intent);
            }
        });

        mutiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText confirm = new EditText(MutiChooseActivity.this);
                android.app.AlertDialog.Builder builder = new AlertDialog.Builder(MutiChooseActivity.this);
                builder.setTitle("请输入房间号").setView(confirm)
                        .setNegativeButton("取消", null);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(confirm.getText().toString().equals("56231")){
                            Intent intent = new Intent().setClass(MutiChooseActivity.this, WaitPreAcitivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast toast= Toast.makeText(MutiChooseActivity.this, "该房间不存在", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                });
                builder.show();
            }
        });

    }
}