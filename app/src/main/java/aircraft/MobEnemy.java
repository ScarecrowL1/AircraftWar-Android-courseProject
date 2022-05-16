package aircraft;

import com.example.aircraftwar.MainActivity;

import bullet.BaseBullet;

import java.util.LinkedList;
import java.util.List;

/**
 * 普通敌机
 * 不可射击
 *
 * @author hitsz
 */
public class MobEnemy extends Enemy {

    public MobEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public void forwards(){
        super.forward();
        if(locationY>= MainActivity.height){
            vanish();
        }
    }


    @Override
    public List<BaseBullet> shoot() {
        return new LinkedList<>();
    }

}
