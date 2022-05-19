package com.example.aircraftwar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.View;

public class Image_Manage extends View {
    public static Bitmap BACKGROUND_IMAGE;
    public static Bitmap HEROAIRCRAFT_IMAGE;
    public static Bitmap MOBENEMY_IMAGE;
    public static Bitmap HERO_BULLET_IMAGE;
    public static Bitmap ELITE_ENEMY_IMAGE;
    public static Bitmap ENEMY_BULLET_IMAGE;
    public static Bitmap PROP_BLOOD_IMAGE;
    public static Bitmap PROP_BULLET_IMAGE;
    public static Bitmap PROP_BOMB_IMAGE;
    public static Bitmap BOSS_ENEMY_IMAGE;
    public Image_Manage(Context context, AttributeSet attrs){
        super(context,attrs);
        init();
    }
    public void init(){
        BACKGROUND_IMAGE = BitmapFactory.decodeResource(getResources(),R.mipmap.background);
        HEROAIRCRAFT_IMAGE = BitmapFactory.decodeResource(getResources(),R.mipmap.hero);
        BACKGROUND_IMAGE = Bitmap.createScaledBitmap(BACKGROUND_IMAGE, MainActivity.width, MainActivity.height, true);
        HEROAIRCRAFT_IMAGE = Bitmap.createScaledBitmap(HEROAIRCRAFT_IMAGE,200,200,true);
        MOBENEMY_IMAGE = BitmapFactory.decodeResource(getResources(),R.mipmap.mob);
        MOBENEMY_IMAGE = Bitmap.createScaledBitmap(MOBENEMY_IMAGE,200,200,true);
        HERO_BULLET_IMAGE = BitmapFactory.decodeResource(getResources(),R.mipmap.bullet_hero);
        ELITE_ENEMY_IMAGE = BitmapFactory.decodeResource(getResources(),R.mipmap.elite);
        ELITE_ENEMY_IMAGE = Bitmap.createScaledBitmap(ELITE_ENEMY_IMAGE,200,200,true);
        ENEMY_BULLET_IMAGE = BitmapFactory.decodeResource(getResources(),R.mipmap.bullet_enemy);
        ENEMY_BULLET_IMAGE = Bitmap.createScaledBitmap(ENEMY_BULLET_IMAGE,20,20,true);
        PROP_BLOOD_IMAGE = BitmapFactory.decodeResource(getResources(),R.mipmap.prop_blood);
        PROP_BLOOD_IMAGE = Bitmap.createScaledBitmap(PROP_BLOOD_IMAGE,100,100,true);
        PROP_BULLET_IMAGE = BitmapFactory.decodeResource(getResources(),R.mipmap.prop_bullet);
        PROP_BULLET_IMAGE = Bitmap.createScaledBitmap(PROP_BULLET_IMAGE,100,100,true);
        PROP_BOMB_IMAGE = BitmapFactory.decodeResource(getResources(),R.mipmap.prop_bomb);
        PROP_BOMB_IMAGE = Bitmap.createScaledBitmap(PROP_BOMB_IMAGE,100,100,true);
        BOSS_ENEMY_IMAGE = BitmapFactory.decodeResource(getResources(),R.mipmap.boss);
        BOSS_ENEMY_IMAGE = Bitmap.createScaledBitmap(BOSS_ENEMY_IMAGE,500,500,true);
    }
}
