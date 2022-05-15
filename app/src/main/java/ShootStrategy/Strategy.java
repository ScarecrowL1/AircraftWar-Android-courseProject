package ShootStrategy;

import bullet.BaseBullet;

import java.util.List;

public interface Strategy {
    public List<BaseBullet> shoot(int x, int y, int SpeedY , int shootnum, int Direction);
}