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
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.RequiresApi;

import java.util.LinkedList;
import java.util.List;

import DrawAction.Draw_Bullet;
import aircraft.AbstractAircraft;
import aircraft.HeroAircraft;
import basic.AbstractFlyingObject;
import bullet.BaseBullet;
import bullet.HeroBullet;

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


    private List<AbstractAircraft> Mob_Enemy_List;
    private List<BaseBullet> Hero_bullet_List;
    private HeroAircraft heroAircraft;

    public Game(Context context, AttributeSet attrs) {
        super(context, attrs);
        Mob_Enemy_List = new LinkedList<>();
        Hero_bullet_List = new LinkedList<>();
        Image_Manage image_manage = new Image_Manage(context,attrs);
        y1 = 0;
        y2 = y1-MainActivity.height;
        heroAircraft = HeroAircraft.getInstance();
        new Thread(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run(){
                while(true){
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count += 1;
                    if(count == 20){
                        shoot();
                        count = 0;
                    }
                    ForWard();
                    logic();
                    invalidate();
                    postProcessAction();
                }
            }
        }.start();
        /*
        new Thread(){
            @Override
            public void run(){
                while(true){
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    shoot();

                }
            }
        }.start();*/
    }

    private void ForWard(){
        Hero_Bullet_Forward();
        Enemy_Forward();
    }
    private void shoot(){
        Hero_bullet_List.addAll(heroAircraft.shoot());
        System.out.println(Hero_bullet_List);
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
        子弹绘制
         */
        Draw_Bullet.Draw_Hero_Bullet(Hero_bullet_List,canvas);
        /*
        英雄机绘制
         */
        canvas.drawBitmap(HEROAIRCRAFT_IMAGE,heroAircraft.getLocationX()-HEROAIRCRAFT_IMAGE.getWidth()/2,heroAircraft.getLocationY()-HEROAIRCRAFT_IMAGE.getHeight(),null);
        /*
        敌机绘制
         */
        //TODO
    }
    private void Hero_Bullet_Forward(){
        for(BaseBullet HeroBullet:Hero_bullet_List){
            HeroBullet.forward();
        }
    }
    private void Enemy_Forward(){

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void postProcessAction() {
        Hero_bullet_List.removeIf(AbstractFlyingObject::notValid);
    }

}
