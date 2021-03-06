package com.example.aircraftwar;

import static com.example.aircraftwar.Image_Manage.BACKGROUND_IMAGE;
import static com.example.aircraftwar.Image_Manage.HEROAIRCRAFT_IMAGE;
import static com.example.aircraftwar.MenuActivity.gameModeInt;
import static com.example.aircraftwar.MenuActivity.level;
import static com.example.aircraftwar.WaitPreAcitivity.ismuti;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.example.aircraftwar.DataDAO.DAO;
import com.example.aircraftwar.DataDAO.DAOImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

import SocketServe.GetThread;
import SocketServe.PostThread;
import bullet.EnemyBullet;
import game_difficulty_method.easy_difficulty;
import game_difficulty_method.hard_difficulty;
import DrawAction.Draw_Bullet;
import DrawAction.Draw_Prop;
import DrawAction.Draw_enemy;
import aircraft.BombSupply;
import aircraft.BossEnemy;
import aircraft.EliteEnemy;
import aircraft.Enemy;
import aircraft.FireSupply;
import aircraft.HeroAircraft;
import aircraft.Hpup;
import aircraft.MobEnemy;
import aircraft.Prop;
import basic.AbstractFlyingObject;
import bullet.BaseBullet;
import factory.BombSupplyFactory;
import factory.BossFactory;
import factory.EliteEnemyFactory;
import factory.EnemyFactory;
import factory.FireSupplyFactory;
import factory.HpupFactory;
import factory.MobEnemyFactory;
import factory.PropFactory;
import game_difficulty_method.normal_difficulty;

/**
 * Created by Administrator on 2017/10/19.
 */

public class Game extends View{

    private static int y1;
    private static int y2;
    public static double ratio_of_ability = 1;
    private static MediaPlayer bgm;
    private static MediaPlayer boss_bgm;
    private static int temp = 0;
    private static int Enemy_come_out_limit = level.enemy_come_out_time();
    private static volatile int increase_enemy_ability_count = 0;
    private static int count_shoot = 0;
    private static int Mobenemy_come_out_count = 0;
    private static int mobenemyMaxNumber = level.size_of_total_mobenemy();
    private static int EliteEnemy_come_out_count = 0;
    private static int Boss_Come_out_Limit = level.limit_of_boss_come_out();
    public static int Score = 0;
    private static int Boss_Come_out_Count = 0;
    private static int eliteenemyMaxNumber = level.size_of_total_exenemy();
    private static int bossenemyMaxNumber = level.size_of_total_boss();
    public static volatile boolean gameOverFlag = false;
    public static String Other_player_score = "";
    public static String finally_score = "";

    private List<MobEnemy> Mob_Enemy_List;
    private List<BaseBullet> Hero_bullet_List;
    private List<EliteEnemy> Elite_Enemy_List;
    private List<BaseBullet> Enemy_bullet_List;
    private HeroAircraft heroAircraft;
    private List<Prop> Prop_List;
    private List<BossEnemy> Boss_Enemy_List;
    private List<AbstractFlyingObject> abstractFlyingObjects;
    private static Fire_Supply_Thread fire_supply_thread = null;
    public static volatile boolean othergameOverFlag = false;


    public Game(Context context, AttributeSet attrs) {
        super(context, attrs);
        abstractFlyingObjects = new LinkedList<>();
        Boss_Enemy_List = new LinkedList<>();
        Prop_List = new LinkedList<>();
        Enemy_bullet_List = new LinkedList<>();
        Elite_Enemy_List = new LinkedList<>();
        Mob_Enemy_List = new LinkedList<>();
        Hero_bullet_List = new LinkedList<>();
        Image_Manage image_manage = new Image_Manage(context,attrs);
        if(level instanceof easy_difficulty){
            BACKGROUND_IMAGE = BitmapFactory.decodeResource(getResources(),R.mipmap.easybg);
            BACKGROUND_IMAGE = Bitmap.createScaledBitmap(BACKGROUND_IMAGE, MainActivity.width, MainActivity.height, true);
        }
        else if(level instanceof normal_difficulty){
            BACKGROUND_IMAGE = BitmapFactory.decodeResource(getResources(),R.mipmap.normalbg);
            BACKGROUND_IMAGE = Bitmap.createScaledBitmap(BACKGROUND_IMAGE, MainActivity.width, MainActivity.height, true);
        }
        else if(level instanceof hard_difficulty){
            BACKGROUND_IMAGE = BitmapFactory.decodeResource(getResources(),R.mipmap.hardbg);
            BACKGROUND_IMAGE = Bitmap.createScaledBitmap(BACKGROUND_IMAGE, MainActivity.width, MainActivity.height, true);
        }
        y1 = 0;
        y2 = y1-MainActivity.height;
        heroAircraft = HeroAircraft.getInstance();

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent e) {
                heroAircraft.touchEvent(e);
                return true;
            }
        });
        /*if (ismuti) {
            new Thread(){
                @Override
                public void run() {
                    try {
                        Socket socket = new Socket("120.77.59.99", 10000);
                        new GetThread(socket).start();
                        new PostThread(socket).start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }*/
        Thread thread = new Thread(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run(){
                boolean Alive = true;
                while(Alive){
                    if(heroAircraft.getHp() <= 0){
                        if(BackGroundMusicService.getBgm() != null){
                            BackGroundMusicService.stop_bgm();
                        }
                        if(BackGroundMusicService.getBoss_bgm() != null){
                            BackGroundMusicService.stop_boss_bgm();
                        }
                        MainActivity.myBinder.playGameOver();
                        Alive = false;
                        gameOverFlag = true;
                        //??????????????????????????????
                        saveData();
                        //????????????
                        break;
                    }
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count_shoot += 1;
                    Mobenemy_come_out_count += 1;
                    EliteEnemy_come_out_count += 1;
                    increase_enemy_ability_count += 1;
                    if(count_shoot == 30){
                        shoot();
                        count_shoot = 0;
                    }
                    if(Mobenemy_come_out_count >= Enemy_come_out_limit*1000/30){
                        if(Mob_Enemy_List.size() < mobenemyMaxNumber) {
                            EnemyFactory enemyFactory = new MobEnemyFactory();
                            Enemy enemy = enemyFactory.createEnemy();
                            Mob_Enemy_List.add((MobEnemy) enemy);
                            abstractFlyingObjects.add((AbstractFlyingObject) enemy);
                        }
                        Mobenemy_come_out_count = 0;
                    }
                    if(EliteEnemy_come_out_count >= Enemy_come_out_limit*3*1000/30){
                        if(Elite_Enemy_List.size()< eliteenemyMaxNumber){
                            EnemyFactory enemyFactory = new EliteEnemyFactory();
                            Enemy enemy = enemyFactory.createEnemy();
                            abstractFlyingObjects.add((AbstractFlyingObject) enemy);
                            Elite_Enemy_List.add((EliteEnemy) enemy);
                        }
                        EliteEnemy_come_out_count = 0;
                    }
                    if(Boss_Come_out_Count >= Boss_Come_out_Limit){
                        Boss_Come_out_Count = 0;
                        EnemyFactory enemyFactory = new BossFactory();
                        Boss_Enemy_List.add((BossEnemy) enemyFactory.createEnemy());
                    }
                    if(ratio_of_ability <= 1.5 && increase_enemy_ability_count >= 1000){
                        if(level instanceof hard_difficulty){
                            System.out.println("?????????????????????");
                            ratio_of_ability += level.ratio_of_ability_increase();
                        }
                        increase_enemy_ability_count = 0;
                    }
                    check_crash();
                    ForWard();
                    logic();
                    invalidate();
                    postProcessAction();
                    if (MenuActivity.soundOn) {
                        play_bgm(context);
                    }
                }
            }
        };
        thread.start();
    }

    private void play_bgm(Context context) {
        bgm = BackGroundMusicService.getBgm();
        boss_bgm = BackGroundMusicService.getBoss_bgm();
        if(Boss_Enemy_List.size() == 0){
            if(boss_bgm != null){
                BackGroundMusicService.stop_boss_bgm();
                BackGroundMusicService.play_bgm(context);
            }
            else{
                if(bgm == null){
                    BackGroundMusicService.play_bgm(context);
                }
            }
        }
        else{
            if(bgm != null){
                BackGroundMusicService.stop_bgm();
                BackGroundMusicService.play_boss_bgm(context);
            }
            else{
                if(boss_bgm == null){
                    BackGroundMusicService.play_boss_bgm(context);
                }
            }
        }

    }

    private void ForWard(){
        Bullet_Forward();
        Enemy_Forward();
        Prop_Forward();
    }

    private void shoot(){
        Hero_bullet_List.addAll(heroAircraft.shoot());
        for(EliteEnemy eliteEnemy:Elite_Enemy_List){
            List<BaseBullet> shoot_list = eliteEnemy.shoot();
            Enemy_bullet_List.addAll(shoot_list);
            abstractFlyingObjects.addAll(shoot_list);
        }
        for(BossEnemy bossEnemy:Boss_Enemy_List){
            List<BaseBullet> shoot_list = bossEnemy.shoot();
            Enemy_bullet_List.addAll(shoot_list);
            abstractFlyingObjects.addAll(shoot_list);
        }
    }

    private void logic(){
        y1 += 5;
        y2 += 5;
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
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        paint.setTextSize(50);
        /*
        ???????????????
         */
        canvas.drawBitmap(BACKGROUND_IMAGE,0,y1,null);
        canvas.drawBitmap(BACKGROUND_IMAGE,0,y2,null);
        /*
        ????????????
         */
        Draw_Bullet.Draw_Hero_Bullet(Hero_bullet_List,canvas);
        Draw_Bullet.Draw_Enemy_Bullet(Enemy_bullet_List,canvas);
        /*
        ???????????????
         */
        canvas.drawBitmap(HEROAIRCRAFT_IMAGE,heroAircraft.getLocationX()-HEROAIRCRAFT_IMAGE.getWidth()/2,heroAircraft.getLocationY()-HEROAIRCRAFT_IMAGE.getHeight()/2,null);
        /*
        ????????????
         */
        Draw_enemy.Draw_Mobenemy(Mob_Enemy_List,canvas);
        Draw_enemy.Draw_Eliteenemy(Elite_Enemy_List,canvas);
        Draw_enemy.Draw_Bossenemy(Boss_Enemy_List,canvas);
        /*
        ????????????
         */
        Draw_Prop.draw(Prop_List,canvas);
        /*
        ????????????????????????
         */
        canvas.drawText("????????????:"+Score,0,50,paint);
        if (ismuti) {
            canvas.drawText("????????????:"+Other_player_score,0,100,paint);
        }
        canvas.drawText("?????????:"+heroAircraft.getHp(),0,150,paint);
    }

    private void Bullet_Forward(){
        for(BaseBullet HeroBullet:Hero_bullet_List){
            HeroBullet.forward();
        }
        for(BaseBullet EnemyBullet:Enemy_bullet_List){
            EnemyBullet.forward();
        }
    }

    private void Enemy_Forward(){
        for(MobEnemy mobenemy:Mob_Enemy_List){
            mobenemy.forwards();
        }
        for(EliteEnemy eliteEnemy:Elite_Enemy_List){
            eliteEnemy.forwards();
        }
        for(BossEnemy bossEnemy:Boss_Enemy_List){
            bossEnemy.forwards();
        }
    }

    private void Prop_Forward(){
        for(Prop prop:Prop_List){
            prop.forwards();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void postProcessAction() {
        Hero_bullet_List.removeIf(AbstractFlyingObject::notValid);
        Enemy_bullet_List.removeIf(AbstractFlyingObject::notValid);
        Mob_Enemy_List.removeIf(AbstractFlyingObject::notValid);
        Elite_Enemy_List.removeIf(AbstractFlyingObject::notValid);
        Boss_Enemy_List.removeIf(AbstractFlyingObject::notValid);
        Prop_List.removeIf(AbstractFlyingObject::notValid);
        abstractFlyingObjects.removeIf(AbstractFlyingObject::notValid);
    }

    private void check_crash(){
        /*
        ???????????????????????????
         */
        for(BaseBullet heroBullet:Hero_bullet_List){
            for(MobEnemy mobEnemy:Mob_Enemy_List){
                if(heroBullet.notValid()){
                    continue;
                }
                if(mobEnemy.notValid()){
                    continue;
                }
                if(mobEnemy.crash(heroBullet)){
                    //????????????
                    MainActivity.myBinder.playBullet();

                    mobEnemy.decreaseHp(heroBullet.getPower());
                    heroBullet.vanish();
                    if(mobEnemy.notValid()){
                        //??????
                        Score += 10;
                        if(Boss_Enemy_List.size()<1){
                            Boss_Come_out_Count += 10;
                        }
                    }
                }
            }
            for(EliteEnemy eliteEnemy:Elite_Enemy_List){
                if(heroBullet.notValid()){
                    continue;
                }
                if(eliteEnemy.notValid()){
                    continue;
                }
                if(eliteEnemy.crash(heroBullet)){
                    //????????????
                    MainActivity.myBinder.playBullet();

                    eliteEnemy.decreaseHp(heroBullet.getPower());
                    heroBullet.vanish();
                    if(eliteEnemy.notValid()){
                        //??????
                        Score += 30;
                        if(Boss_Enemy_List.size()<1){
                            Boss_Come_out_Count += 120;
                        }
                        //????????????
                        int locationx = eliteEnemy.getLocationX();
                        int locationy = eliteEnemy.getLocationY();
                        PropFactory propFactory;
                        if (Math.random() >= 0.2) {
                            int chosen = (int)((Math.random()*10)%3);
                            switch (chosen){
                                case 0:
                                    propFactory = new HpupFactory();
                                    Prop_List.add(propFactory.CreateProp(locationx,locationy,0,5));
                                    break;
                                case 1:
                                    propFactory = new FireSupplyFactory();
                                    Prop_List.add(propFactory.CreateProp(locationx,locationy,0,5));
                                    break;
                                case 2:
                                    propFactory = new BombSupplyFactory();
                                    Prop_List.add(propFactory.CreateProp(locationx,locationy,0,5));
                                    break;
                                default:
                            }
                        }
                    }
                }
            }
            for(BossEnemy bossEnemy:Boss_Enemy_List){
                if(bossEnemy.notValid()){
                    continue;
                }
                if(heroBullet.notValid()){
                    continue;
                }
                if(bossEnemy.crash(heroBullet)){
                    //????????????
                    MainActivity.myBinder.playBullet();
                    bossEnemy.decreaseHp(heroBullet.getPower());
                    heroBullet.vanish();
                    if(bossEnemy.notValid()){
                        Score += 1000;
                    }
                }
            }
        }
        /*
        ???????????????????????????
         */
        //
        for(BaseBullet enemyBullet:Enemy_bullet_List){
            if(enemyBullet.notValid()){
                continue;
            }
            else{
                if(enemyBullet.crash(heroAircraft)){
                    heroAircraft.decreaseHp(enemyBullet.getPower());
                    enemyBullet.vanish();
                }
            }
        }


        /*
        ????????????????????????????????????????????????????????????????????????
         */
        for(MobEnemy mobEnemy:Mob_Enemy_List){
            if(heroAircraft.crash(mobEnemy)){
                heroAircraft.decreaseHp(heroAircraft.getHp());
            }
        }
        for(EliteEnemy eliteEnemy:Elite_Enemy_List){
            if(heroAircraft.crash(eliteEnemy)){
                heroAircraft.decreaseHp(heroAircraft.getHp());
            }
        }
        for(BossEnemy bossEnemy:Boss_Enemy_List){
            if(heroAircraft.crash(bossEnemy)){
                heroAircraft.decreaseHp(heroAircraft.getHp());
            }
        }

        /*
        ??????????????????
         */
        for(Prop prop:Prop_List){
            if(prop.notValid()){
                continue;
            }
            if(prop.crash(heroAircraft)){
                //??????????????????
                if(prop instanceof Hpup){
                    MainActivity.myBinder.playPropActive();
                    heroAircraft.decreaseHp(-20);
                }
                if(prop instanceof FireSupply){
                    //??????????????????10S
                    MainActivity.myBinder.playPropActive();
                    if(fire_supply_thread == null || !fire_supply_thread.isAlive()){
                        fire_supply_thread = new Fire_Supply_Thread();
                        fire_supply_thread.start();
                    }
                }
                if(prop instanceof BombSupply){
                    MainActivity.myBinder.playBomb();
                    new Explosion().explosion_happend(abstractFlyingObjects);
                }
                prop.vanish();
            }
        }
    }

    public void saveData(){
        DAO dao = new DAOImpl();
        dao.doRead(gameModeInt);
        dao.doAdd(Score, 1000, LoginActivity.getUserID());
        dao.doRank();
        dao.doSave(gameModeInt);
    }

}
