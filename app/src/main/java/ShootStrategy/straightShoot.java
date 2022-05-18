package ShootStrategy;


import java.util.LinkedList;
import java.util.List;

import bullet.BaseBullet;
import bullet.HeroBullet;

public class straightShoot implements Strategy{
    @Override
    public List<BaseBullet> shoot(int x, int y, int speedY, int shootnum, int Direction) {

        // 获取飞机x,y坐标
        // 获取飞机子弹伤害，射击数量，射击方向
        int power = 30;
        int direction = Direction;

        List<BaseBullet> res = new LinkedList<>();

        int speedx = 0;
        int speedy = speedY+direction*10;
        BaseBullet baseBullet;
        for(int i=0; i<shootnum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            baseBullet = new BaseBullet(x, y, speedx, speedy, power);
            res.add(baseBullet);
        }
        return res;
    }
    }