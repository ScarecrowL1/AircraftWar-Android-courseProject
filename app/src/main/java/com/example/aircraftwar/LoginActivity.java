package com.example.aircraftwar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                    if (isPass()) {
                        //登陆成功跳转到单人与多人的选择
                        //显示登陆成功信息
                        Toast toast= Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT);
                        toast.show();
                        userID = textedName; //登陆成功，代表输入正确，把用户名保存到userID
                        startActivity(new Intent().setClass(LoginActivity.this, singgleOrMuti.class));
                        finish();
                    } else {
                        Toast toast= Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT);
                        toast.show();
                    }
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
                                //进行注册
                                doReg();
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

    /**
     * 比对用户名、密码
     * 若符合，返回true,否则返回flase
     * @return boolean
     */
    public boolean isPass(){
        boolean pass;
        EditText usernameText = findViewById(R.id.usernameText);
        EditText passwordText = findViewById(R.id.passwordText);

        //用户输入的用户名
        textedName = usernameText.getText().toString();
        //用户输入的密码
        textedPasswd = passwordText.getText().toString();

        //todo 比对数据库中的信息
        if (true) {
             pass = true;
        }
        else{
            pass =  false;
        }
        return pass;
    }

    /**
     * 验证密码通过，进行注册
     */
    public void doReg(){
        //todo 注册
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