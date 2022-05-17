package DrawAction;

import static com.example.aircraftwar.Image_Manage.HEROAIRCRAFT_IMAGE;
import static com.example.aircraftwar.Image_Manage.HERO_BULLET_IMAGE;

import android.graphics.Canvas;

import java.util.List;

import aircraft.HeroAircraft;
import bullet.BaseBullet;

public class Draw_Bullet {
    private static HeroAircraft heroAircraft;
    public static void Draw_Hero_Bullet(List<BaseBullet> Bullet_List, Canvas canvas){
        heroAircraft = HeroAircraft.getInstance();
        for(BaseBullet baseBullet:Bullet_List){
            canvas.drawBitmap(HERO_BULLET_IMAGE,baseBullet.getLocationX()-HERO_BULLET_IMAGE.getWidth()/2,baseBullet.getLocationY()-HERO_BULLET_IMAGE.getHeight(),null);
        }
    }
}
