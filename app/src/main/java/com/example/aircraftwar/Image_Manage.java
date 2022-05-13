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
    }
}
