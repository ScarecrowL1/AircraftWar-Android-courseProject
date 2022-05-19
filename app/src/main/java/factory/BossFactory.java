package factory;

import static com.example.aircraftwar.Image_Manage.BOSS_ENEMY_IMAGE;

import aircraft.BossEnemy;

import com.example.aircraftwar.MainActivity;

public class BossFactory implements EnemyFactory {

    @Override
    public BossEnemy createEnemy() {
        return new BossEnemy(
                (int) (Math.random() * (MainActivity.width - BOSS_ENEMY_IMAGE.getWidth()/2))*1,
                (int) (Math.random() * MainActivity.height * 0.1)*1,
                5,
                0,
                (int)(300));
    }
}
