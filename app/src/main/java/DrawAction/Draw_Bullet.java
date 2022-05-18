package DrawAction;

import static com.example.aircraftwar.Image_Manage.ENEMY_BULLET_IMAGE;
import static com.example.aircraftwar.Image_Manage.HEROAIRCRAFT_IMAGE;
import static com.example.aircraftwar.Image_Manage.HERO_BULLET_IMAGE;

import android.graphics.Canvas;

import java.util.List;

import aircraft.HeroAircraft;
import bullet.BaseBullet;

public class Draw_Bullet {
    public static void Draw_Hero_Bullet(List<BaseBullet> Bullet_List, Canvas canvas){
        for(BaseBullet baseBullet:Bullet_List){
            canvas.drawBitmap(HERO_BULLET_IMAGE,baseBullet.getLocationX()-HERO_BULLET_IMAGE.getWidth()/2,baseBullet.getLocationY()-HERO_BULLET_IMAGE.getHeight()/2,null);
        }
    }
    public static void Draw_Enemy_Bullet(List<BaseBullet> Bullet_List,Canvas canvas){
        for(BaseBullet baseBullet:Bullet_List){
            canvas.drawBitmap(ENEMY_BULLET_IMAGE,baseBullet.getLocationX()-HERO_BULLET_IMAGE.getWidth()/2,baseBullet.getLocationY()-ENEMY_BULLET_IMAGE.getHeight()/2,null);
        }
    }
}
