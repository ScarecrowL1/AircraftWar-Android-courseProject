package factory;

import static com.example.aircraftwar.Game.ratio_of_ability;
import static com.example.aircraftwar.Image_Manage.MOBENEMY_IMAGE;
import static com.example.aircraftwar.MenuActivity.level;

import aircraft.MobEnemy;

import com.example.aircraftwar.MainActivity;

public class MobEnemyFactory implements EnemyFactory {
    @Override
    public MobEnemy createEnemy() {
        /*return new MobEnemy(
                (int) ( Math.random() * (MainActivity.width - MOBENEMY_IMAGE.getWidth()))*1,
                (int) (Math.random() * MainActivity.height * 0.2)*1,
                0,
                10,
                (int)(method.hp_of_enemy()*ratio_of_ability));*/
        return new MobEnemy(
                (int) (Math.random() * (MainActivity.width - MOBENEMY_IMAGE.getWidth()/2)) * 1,
                (int) (Math.random() * MainActivity.height * 0.1) * 1,
                0,
                10,
                (int) (level.hp_of_enemy()*ratio_of_ability));

    }
}