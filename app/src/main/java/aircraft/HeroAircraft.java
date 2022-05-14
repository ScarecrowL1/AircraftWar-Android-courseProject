package aircraft;

import static com.example.aircraftwar.Image_Manage.HEROAIRCRAFT_IMAGE;
import static com.example.aircraftwar.MainActivity.StateHeight;

import android.graphics.Bitmap;

import com.example.aircraftwar.MainActivity;

import java.util.List;

import basic.AbstractFlyingObject;
import bullet.BaseBullet;

public class HeroAircraft extends AbstractAircraft {
    private HeroAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }
    /** 单例生成英雄机 */
    private static HeroAircraft instance = new HeroAircraft(
            MainActivity.width / 2,
            MainActivity.height-StateHeight,
            0, 0, 1000);
    public static HeroAircraft getInstance(){
        return instance;
    }

    @Override
    public List<BaseBullet> shoot() {
        return null;
    }


    @Override
    public Bitmap Get_Image() {
        return HEROAIRCRAFT_IMAGE;
    }
}
