package factory;

import aircraft.Hpup;
import aircraft.Prop;

public class HpupFactory implements PropFactory {
    @Override
    public Prop CreateProp(int locationX, int locationY, int speedX, int speedY) {
        return new Hpup(locationX,locationY,speedX,speedY);
    }
}
