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

public class LoginActivity extends AppCompatActivity {
    private static String userID;
    private static String userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = findViewById(R.id.loginButton);
        Button regButton = findViewById(R.id.regButton);

        EditText usernameText = findViewById(R.id.usernameText);
        EditText passwordText = findViewById(R.id.passwordText);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //登陆成功跳转到单人与多人的选择
                //显示登陆成功信息
                Toast toast= Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT);
                toast.show();
                startActivity(new Intent().setClass(LoginActivity.this, singgleOrMuti.class));
                finish();

            }
        });

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText confirmPassword = new EditText(LoginActivity.this);
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("确认密码").setView(confirmPassword)
                        .setNegativeButton("取消", null);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        confirmPassword.getText().toString();
                    }
                });
                builder.show();
            }
        });
    }

    public static String getUserID() {
        return userID;
    }
}