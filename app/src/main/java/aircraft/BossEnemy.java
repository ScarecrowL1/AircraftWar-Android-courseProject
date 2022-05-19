package aircraft;

import static com.example.aircraftwar.Image_Manage.BOSS_ENEMY_IMAGE;

import com.example.aircraftwar.MainActivity;

import ShootStrategy.ShootContext;
import ShootStrategy.divergentShoot;
import bullet.BaseBullet;

import java.util.List;

public class BossEnemy extends Enemy{
    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        loadImage();
    }
    private int shootNum = 3;     //子弹一次发射数量
    private int power = 30;       //子弹伤害
    private int direction = 1;  //子弹射击方向 (向上发射：-1，向下发射：1)
    private ShootContext shootcontext = new ShootContext(new divergentShoot());
    @Override
    public void forwards() {
        super.forward();
        if(locationY>= MainActivity.height){
            vanish();
        }
    }

    @Override
    public List<BaseBullet> shoot() {
        return shootcontext.executeStrategy(locationX,locationY,speedY,shootNum,direction);
    }

    @Override
    public void loadImage() {
        image = BOSS_ENEMY_IMAGE;
    }
}
