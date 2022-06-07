package com.example.aircraftwar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class LoginActivity extends AppCompatActivity {

    private static String userID;

    public static String textedName;
    public static String textedPasswd;


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

                if (isTyped()) {
                    /**
                     * 比对用户名、密码
                     * 若符合，返回true,否则返回flase
                     * @return boolean
                     */
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                Socket socket = new Socket("120.77.59.99", 20000);
                                socket.setSoTimeout(10000);
                                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
                                String user_name = usernameText.getText().toString();
                                String user_password = passwordText.getText().toString();
                                pw.println(user_name + "\n" + user_password);
                                InputStreamReader isr = new InputStreamReader(socket.getInputStream());
                                BufferedReader br = new BufferedReader(isr);
                                String response = br.readLine();
                                if (response.equals("登陆成功")) {
                                    Looper.prepare();
                                    Toast toast= Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT);
                                    toast.show();
                                    startActivity(new Intent().setClass(LoginActivity.this, singgleOrMuti.class));
                                    finish();
                                    Looper.loop();
                                }
                                else{
                                    Looper.prepare();
                                    Toast toast= Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT);
                                    toast.show();
                                    Looper.loop();
                                }
                                pw.close();
                                socket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                } else {
                    Toast toast= Toast.makeText(LoginActivity.this, "请输入完整数据", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTyped()) {
                    final EditText confirmPassword = new EditText(LoginActivity.this);
                    confirmPassword.setInputType(129); //设置为密码不可见
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setTitle("确认密码").setView(confirmPassword)
                            .setNegativeButton("取消", null);
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            //比对确认密码的字符串和之前输入的密码是否相符
                            if (passwordText.getText().toString().equals(confirmPassword.getText().toString())) {
                                /**
                                 * 验证密码通过，进行注册
                                 */
                                new Thread(){
                                    @Override
                                    public void run() {
                                        try {
                                            String user_name = usernameText.getText().toString();
                                            String user_password = passwordText.getText().toString();
                                            Socket socket = new Socket("120.77.59.99", 15000);
                                            socket.setSoTimeout(10000);
                                            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
                                            pw.println(user_name + "\n" + user_password);
                                            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
                                            BufferedReader br = new BufferedReader(isr);
                                            String response = br.readLine();
                                            if (response.equals("用户名已存在")) {
                                                Looper.prepare();
                                                Toast toast= Toast.makeText(LoginActivity.this, "用户名已存在！", Toast.LENGTH_SHORT);
                                                toast.show();
                                                Looper.loop();
                                            } else {
                                                Looper.prepare();
                                                Toast toast= Toast.makeText(LoginActivity.this, "注册成功！", Toast.LENGTH_SHORT);
                                                toast.show();
                                                Looper.loop();
                                            }
                                            pw.close();
                                            socket.close();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }.start();
                            }
                            else {
                                Toast toast= Toast.makeText(LoginActivity.this, "确认密码不一致，未注册", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }
                    });
                    builder.show();
                } else {
                    Toast toast= Toast.makeText(LoginActivity.this, "请输入完整数据", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }


    public static String getUserID() {
        return userID;
    }

    /**
     * 判断输入框是否都输入了数据
     * 如果是，返回ture，如果不是，返回false
     * @return boolean
     */
    public boolean isTyped(){
        EditText usernameText = findViewById(R.id.usernameText);
        EditText passwordText = findViewById(R.id.passwordText);
        if(usernameText.getText().toString().equals("") || passwordText.getText().toString().equals("")){
            return false;
        }
        else{
            return true;
        }
    }
}