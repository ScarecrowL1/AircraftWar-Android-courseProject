package aircraft;

import static com.example.aircraftwar.Image_Manage.HEROAIRCRAFT_IMAGE;
import static com.example.aircraftwar.MainActivity.StateHeight;

import android.graphics.Bitmap;

import com.example.aircraftwar.MainActivity;

import java.util.List;

import ShootStrategy.ShootContext;
import ShootStrategy.Strategy;
import ShootStrategy.straightShoot;
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


}
