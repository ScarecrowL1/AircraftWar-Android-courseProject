package aircraft;

import static com.example.aircraftwar.Image_Manage.PROP_BULLET_IMAGE;

import com.example.aircraftwar.MainActivity;

public class FireSupply extends Prop {
    public FireSupply(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
        loadImage();
    }

    @Override
    public void loadImage() {
        image = PROP_BULLET_IMAGE;
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
