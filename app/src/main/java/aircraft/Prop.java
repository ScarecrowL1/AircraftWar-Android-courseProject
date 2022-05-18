package aircraft;

import basic.AbstractFlyingObject;

public abstract class Prop extends AbstractFlyingObject {
        public Prop(int locationX, int locationY, int speedX, int speedY){
                super(locationX, locationY, speedX, speedY);
        }
        public abstract void forwards();
}
