package aircraft;

import static com.example.aircraftwar.Image_Manage.PROP_BLOOD_IMAGE;

import com.example.aircraftwar.MainActivity;
import aircraft.Prop;

public class Hpup extends Prop {
    public Hpup(int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
        loadImage();
    }

    @Override
    public void loadImage() {
        image = PROP_BLOOD_IMAGE;
    }

    @Override
    public void forwards() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= MainActivity.height) {
            vanish();
        }
    }
}
