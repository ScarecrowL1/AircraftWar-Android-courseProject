package factory;

import aircraft.Prop;

public interface PropFactory {
    public Prop CreateProp(int locationX, int locationY, int speedX, int speedY);
}
