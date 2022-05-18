package aircraft;

import static com.example.aircraftwar.Image_Manage.ELITE_ENEMY_IMAGE;

import com.example.aircraftwar.MainActivity;

import java.util.List;

import ShootStrategy.ShootContext;
import ShootStrategy.straightShoot;
import bullet.BaseBullet;


public class EliteEnemy extends Enemy{
    /** 攻击方式 */
    private int shootNum = 1;     //子弹一次发射数量
    private int power = 30;       //子弹伤害
    private int direction = 1;  //子弹射击方向 (向上发射：-1，向下发射：1)
    private ShootContext shootcontext = new ShootContext(new straightShoot());

    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        loadImage();
    }

    @Override
    public void forwards() {
        super.forward();
        if(locationY >= MainActivity.height){
            vanish();
        }
    }

    @Override
    public List<BaseBullet> shoot() {
        return this.shootcontext.executeStrategy(locationX,locationY,speedY,shootNum,direction);
    }

    @Override
    public void loadImage() {
        image = ELITE_ENEMY_IMAGE;
    }
}
