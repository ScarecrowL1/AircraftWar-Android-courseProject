package aircraft;

import static com.example.aircraftwar.Image_Manage.HEROAIRCRAFT_IMAGE;
import static com.example.aircraftwar.Image_Manage.MOBENEMY_IMAGE;
import static com.example.aircraftwar.MainActivity.StateHeight;

import android.graphics.Bitmap;
import android.view.MotionEvent;

import com.example.aircraftwar.MainActivity;

import java.util.List;

import ShootStrategy.ShootContext;
import ShootStrategy.Strategy;
import ShootStrategy.straightShoot;
import bullet.BaseBullet;
import bullet.HeroBullet;

public class HeroAircraft extends AbstractAircraft {
    private HeroAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        loadImage();
    }
    /** 单例生成英雄机 */
    private static HeroAircraft instance = new HeroAircraft(
            MainActivity.width / 2,
            MainActivity.height-StateHeight-HEROAIRCRAFT_IMAGE.getHeight()/2,
            0, 0, 1000);
    public static HeroAircraft getInstance(){
        return instance;
    }
    /** 攻击方式 */
    private int shootNum = 1;     //子弹一次发射数量
    private int power = 30;       //子弹伤害
    private int direction = -1;  //子弹射击方向 (向上发射：-1，向下发射：1)
    private ShootContext shootContext = new ShootContext(new straightShoot());

    public int getShootNum(){
        return shootNum;
    }
    public void setShootNum(int num){
        this.shootNum = num;
    }
    public void setShootStrategy(Strategy strategy){
        shootContext.setStrategy(strategy);
    }

    @Override
    public List<BaseBullet> shoot() {
        return this.shootContext.executeStrategy(instance.getLocationX(), instance.getLocationY(),instance.getSpeedY(),instance.shootNum,instance.direction);
    }


    @Override
    public void loadImage() {
        image = HEROAIRCRAFT_IMAGE;
    }

    public void touchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            int ex = (int) event.getX();
            int ey = (int) event.getY();
            if (ex > locationX-image.getWidth()/2 && ex < locationX + image.getWidth()/2 && ey > locationY-image.getHeight()/2 && ey < locationY + image.getHeight()/2) {
                locationX = ex;
                locationY = ey;
                if (locationY < 0) {
                    locationY = 0;
                }
                if (locationY + image.getHeight()/2 > MainActivity.height) {
                    locationY = MainActivity.height - image.getHeight()/2;
                }
            }
            if (locationX < 0) {
                locationX = 0;
            }
            if (locationX + image.getWidth()/2 > MainActivity.width) {
                locationX = MainActivity.width - image.getWidth()/2;
            }

        }

    }
}
