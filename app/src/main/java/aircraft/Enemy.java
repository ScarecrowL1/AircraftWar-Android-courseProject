package aircraft;

import bullet.BaseBullet;
import java.util.List;

public abstract class Enemy extends AbstractAircraft{
    public Enemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }
    @Override
    public abstract List<BaseBullet> shoot();
    public abstract void forwards();
}
