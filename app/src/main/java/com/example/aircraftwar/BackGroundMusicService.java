package com.example.aircraftwar;

import android.content.Context;
import android.media.MediaPlayer;

public class BackGroundMusicService {
    private static MediaPlayer bgm = null;
    private static MediaPlayer boss_bgm = null;
    public static void play_bgm(Context context){
        bgm = MediaPlayer.create(context,R.raw.bgm);
        bgm.setLooping(true);
        bgm.start();
    }
    public static void stop_bgm(){
        if(bgm != null){
            bgm.stop();
            bgm.release();
            bgm = null;
        }
    }
    public static void play_boss_bgm(Context context){
        boss_bgm = MediaPlayer.create(context,R.raw.bgm_boss);
        boss_bgm.setLooping(true);
        boss_bgm.start();
    }
    public static void stop_boss_bgm(){
        if(boss_bgm != null){
            boss_bgm.stop();
            boss_bgm.release();
            boss_bgm = null;
        }
    }
    public static MediaPlayer getBgm(){
        return bgm;
    }
    public static MediaPlayer getBoss_bgm(){
        return boss_bgm;
    }
}
