package ShootStrategy;

import static com.example.aircraftwar.Image_Manage.HEROAIRCRAFT_IMAGE;

import bullet.BaseBullet;
import bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class divergentShoot implements Strategy{
    @Override
    public List<BaseBullet> shoot(int x, int y, int speedY, int shootnum, int Direction) {

        // 获取飞机子弹伤害，射击数量，射击方向

        int power = 30;
        List<BaseBullet> res = new LinkedList<>();
        int mid = (shootnum-1)/2;
        int speedy = speedY+Direction*10;
        BaseBullet baseBullet;
        for(int i=0; i<shootnum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            int speedx = (mid-i)*Direction;
            baseBullet = new BaseBullet(x, y, speedx, speedy, power);
            res.add(baseBullet);
        }
        return res;
    }
}

