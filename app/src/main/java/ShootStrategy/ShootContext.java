package ShootStrategy;

import bullet.BaseBullet;

import java.util.List;

public class ShootContext {
    private Strategy strategy;

    public ShootContext(Strategy strategy){
        this.strategy = strategy;
    }
    public void setStrategy(Strategy strategy){
        this.strategy = strategy;
    }
    public List<BaseBullet> executeStrategy(int x, int y, int speedY, int shootnum, int Direction){
        return strategy.shoot(x,y,speedY,shootnum,Direction);
    }
}
