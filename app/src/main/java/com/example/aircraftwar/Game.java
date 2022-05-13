package com.example.aircraftwar;

import static com.example.aircraftwar.Image_Manage.BACKGROUND_IMAGE;
import static com.example.aircraftwar.Image_Manage.HEROAIRCRAFT_IMAGE;
import static com.example.aircraftwar.MainActivity.StateHeight;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/10/19.
 */

public class Game extends View{
    // 定义画笔
    private Paint mPaint;
    // 用于获取文字的宽和高
    private Rect mRect;
    // 计数值，每点击一次本控件，其值增加1

    public static int y1;
    public static int y2;

    private static int count = 0;

    public Game(Context context, AttributeSet attrs) {
        super(context, attrs);
        Image_Manage image_manage = new Image_Manage(context,attrs);
        y1 = 0;
        y2 = y1-MainActivity.height;
        // 初始化画笔、Rect
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRect = new Rect();
        new Thread(){
            @Override
            public void run(){
                while(true){
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    logic();
                    count++;
                    if(count == 20){
                        count = 0;
                    }
                    invalidate();
                }
            }
        }.start();
    }
    private void logic(){
        y1 += 10;
        y2 += 10;
        if(y1>=MainActivity.height){
            y1 = y2-MainActivity.height;
        }
        if(y2>=MainActivity.height){
            y2 = y1-MainActivity.height;
        }
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /*
        绘制背景图
         */
        canvas.drawBitmap(BACKGROUND_IMAGE,0,y1,null);
        canvas.drawBitmap(BACKGROUND_IMAGE,0,y2,null);
        /*
        英雄机绘制
         */
        canvas.drawBitmap(HEROAIRCRAFT_IMAGE,(MainActivity.width-HEROAIRCRAFT_IMAGE.getHeight())/2,MainActivity.height-HEROAIRCRAFT_IMAGE.getHeight()-StateHeight,null);
        /*
        敌机绘制
         */




        // 绘制一个填充色为蓝色的矩形
        /*canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(50);
        String text = String.valueOf(mCount);*/
        // 获取文字的宽和高
        /*mPaint.getTextBounds(text, 0, text.length(), mRect);
        float textWidth = mRect.width();
        float textHeight = mRect.height();*/
        // 绘制字符串
        /*canvas.drawText("点了我"+text+"次", getWidth() / 2 - textWidth / 2, getHeight() / 2
                + textHeight / 2, mPaint);*/
    }

}
