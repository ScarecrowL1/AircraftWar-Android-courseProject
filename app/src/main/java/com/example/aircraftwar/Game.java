package com.example.aircraftwar;

import static com.example.aircraftwar.Image_Manage.BACKGROUND_IMAGE;
import static com.example.aircraftwar.Image_Manage.BOSS_ENEMY_IMAGE;
import static com.example.aircraftwar.Image_Manage.ELITE_ENEMY_IMAGE;
import static com.example.aircraftwar.Image_Manage.HEROAIRCRAFT_IMAGE;
import static com.example.aircraftwar.MainActivity.StateHeight;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.RequiresApi;

import java.util.LinkedList;
import java.util.List;

import DrawAction.Draw_Bullet;
import DrawAction.Draw_Prop;
import DrawAction.Draw_enemy;
import aircraft.AbstractAircraft;
import aircraft.BossEnemy;
import aircraft.EliteEnemy;
import aircraft.Enemy;
import aircraft.HeroAircraft;
import aircraft.MobEnemy;
import aircraft.Prop;
import basic.AbstractFlyingObject;
import bullet.BaseBullet;
import bullet.HeroBullet;
import factory.BombSupplyFactory;
import factory.BossFactory;
import factory.EliteEnemyFactory;
import factory.EnemyFactory;
import factory.FireSupplyFactory;
import factory.HpupFactory;
import factory.MobEnemyFactory;
import factory.PropFactory;

/**
 * Created by Administrator on 2017/10/19.
 */

public class Game extends View{

    private static int y1;
    private static int y2;

    private static int count_shoot = 0;
    private static int Mobenemy_come_out_count = 0;
    private static int mobenemyMaxNumber = 5;
    private static int EliteEnemy_come_out_count = 0;
    private static int Boss_Come_out_Limit = 20;
    private static int Score;
    private static int Boss_Come_out_Count = 0;
    private static int eliteenemyMaxNumber = 1;
    private static int bossenemyMaxNumber = 1;

    private List<MobEnemy> Mob_Enemy_List;
    private List<BaseBullet> Hero_bullet_List;
    private List<EliteEnemy> Elite_Enemy_List;
    private List<BaseBullet> Enemy_bullet_List;
    private HeroAircraft heroAircraft;
    private List<Prop> Prop_List;
    private List<BossEnemy> Boss_Enemy_List;


    public Game(Context context, AttributeSet attrs) {

        super(context, attrs);
        Score = 0;
        Boss_Enemy_List = new LinkedList<>();
        Prop_List = new LinkedList<>();
        Enemy_bullet_List = new LinkedList<>();
        Elite_Enemy_List = new LinkedList<>();
        Mob_Enemy_List = new LinkedList<>();
        Hero_bullet_List = new LinkedList<>();
        Image_Manage image_manage = new Image_Manage(context,attrs);
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
                    count_shoot += 1;
                    Mobenemy_come_out_count += 1;
                    EliteEnemy_come_out_count += 1;
                    if(count_shoot == 30){
                        shoot();
                        count_shoot = 0;
                    }
                    if(Mobenemy_come_out_count == 20){
                        if(Mob_Enemy_List.size()<=mobenemyMaxNumber) {
                            EnemyFactory enemyFactory = new MobEnemyFactory();
                            Mob_Enemy_List.add((MobEnemy) enemyFactory.createEnemy());
                        }
                        Mobenemy_come_out_count = 0;
                    }
                    if(EliteEnemy_come_out_count == 50){
                        if(Elite_Enemy_List.size()<1){
                            EnemyFactory enemyFactory = new EliteEnemyFactory();
                            Elite_Enemy_List.add((EliteEnemy) enemyFactory.createEnemy());
                        }
                        EliteEnemy_come_out_count = 0;
                    }
                    if(Boss_Come_out_Count >= Boss_Come_out_Limit){
                        Boss_Come_out_Count = 0;
                        EnemyFactory enemyFactory = new BossFactory();
                        Boss_Enemy_List.add((BossEnemy) enemyFactory.createEnemy());
                    }
                    check_crash();
                    ForWard();
                    logic();
                    invalidate();
                    postProcessAction();
                }
            }
        }.start();
    }

    private void ForWard(){
        Bullet_Forward();
        Enemy_Forward();
        Prop_Forward();
    }
    private void shoot(){
        Hero_bullet_List.addAll(heroAircraft.shoot());
        for(EliteEnemy eliteEnemy:Elite_Enemy_List){
            Enemy_bullet_List.addAll(eliteEnemy.shoot());
        }
        for(BossEnemy bossEnemy:Boss_Enemy_List){
            Enemy_bullet_List.addAll(bossEnemy.shoot());
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
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setTextSize(50);
        /*
        绘制背景图
         */
        canvas.drawBitmap(BACKGROUND_IMAGE,0,y1,null);
        canvas.drawBitmap(BACKGROUND_IMAGE,0,y2,null);
        /*
        子弹绘制
         */
        Draw_Bullet.Draw_Hero_Bullet(Hero_bullet_List,canvas);
        Draw_Bullet.Draw_Enemy_Bullet(Enemy_bullet_List,canvas);
        /*
        英雄机绘制
         */
        canvas.drawBitmap(HEROAIRCRAFT_IMAGE,heroAircraft.getLocationX()-HEROAIRCRAFT_IMAGE.getWidth()/2,heroAircraft.getLocationY()-HEROAIRCRAFT_IMAGE.getHeight()/2,null);
        /*
        敌机绘制
         */
        Draw_enemy.Draw_Mobenemy(Mob_Enemy_List,canvas);
        Draw_enemy.Draw_Eliteenemy(Elite_Enemy_List,canvas);
        Draw_enemy.Draw_Bossenemy(Boss_Enemy_List,canvas);
        /*
        道具绘制
         */
        Draw_Prop.draw(Prop_List,canvas);
        /*
        绘制生命值和分数
         */
        canvas.drawText("分数:"+Score,0,50,paint);
        canvas.drawText("生命值:"+heroAircraft.getHp(),0,100,paint);
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
        Prop_List.removeIf(AbstractFlyingObject::notValid);
    }

    private void check_crash(){
        /*
        英雄机子弹击中敌人
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
                    //受击音效
                    MainActivity.myBinder.playBullet();

                    mobEnemy.decreaseHp(heroBullet.getPower());
                    heroBullet.vanish();
                    if(mobEnemy.notValid()){
                        //得分
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
                    //受击音效
                    MainActivity.myBinder.playBullet();

                    eliteEnemy.decreaseHp(heroBullet.getPower());
                    heroBullet.vanish();
                    if(eliteEnemy.notValid()){
                        //得分
                        Score += 30;
                        if(Boss_Enemy_List.size()<1){
                            Boss_Come_out_Count += 30;
                        }
                        //生成道具
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
                    //受击音效
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
        敌机子弹击中英雄机
         */
        //TODO


        /*
        飞行物体碰撞，包括敌机和英雄机，英雄机和道具等。
         */

        /*
        道具碰撞检测
         */
        for(Prop prop:Prop_List){
            if(prop.notValid()){
                continue;
            }
            if(prop.crash(heroAircraft)){
                System.out.println("Get Prop!");
                prop.vanish();
            }
        }

    }

}
