package factory;

import aircraft.BombSupply;
import aircraft.Prop;

public class BombSupplyFactory implements PropFactory {

    @Override
    public Prop CreateProp(int locationX, int locationY, int speedX, int speedY) {
        return new BombSupply(locationX, locationY, speedX, speedY);
    }
}
