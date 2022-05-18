package factory;

import aircraft.FireSupply;
import aircraft.Prop;

public class FireSupplyFactory implements PropFactory {

    @Override
    public Prop CreateProp(int locationX, int locationY, int speedX, int speedY) {
        return new FireSupply(locationX, locationY, speedX, speedY);
    }
}
