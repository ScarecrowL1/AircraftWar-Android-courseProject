package factory;

import static com.example.aircraftwar.Game.ratio_of_ability;
import static com.example.aircraftwar.Image_Manage.BOSS_ENEMY_IMAGE;

import aircraft.BossEnemy;

import com.example.aircraftwar.MainActivity;

import static com.example.aircraftwar.MenuActivity.level;

public class BossFactory implements EnemyFactory {

    @Override
    public BossEnemy createEnemy() {
        return new BossEnemy(
                (int) (Math.random() * (MainActivity.width - BOSS_ENEMY_IMAGE.getWidth()/2))*1,
                (int) (Math.random() * MainActivity.height * 0.1)*1,
                5,
                0,
                (int)(level.hp_of_boss()*ratio_of_ability));
    }
}
