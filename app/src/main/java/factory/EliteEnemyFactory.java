package factory;

import static com.example.aircraftwar.Game.ratio_of_ability;
import static com.example.aircraftwar.Image_Manage.ELITE_ENEMY_IMAGE;
import static com.example.aircraftwar.MenuActivity.level;

import aircraft.EliteEnemy;


import com.example.aircraftwar.MainActivity;

public class EliteEnemyFactory implements EnemyFactory {

    @Override
    public EliteEnemy createEnemy() {
        /*return new EiliteEnemy(
                (int) ( Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()))*1,
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2)*1,
                1,
                2,
                (int)(method.hp_of_exenmy()*ratio_of_ability)
        );*/
        return new EliteEnemy(
                (int) (Math.random() * (MainActivity.width - ELITE_ENEMY_IMAGE.getWidth()/2)) * 1,
                (int) (Math.random() * MainActivity.height * 0.1) * 1,
                0,
                8,
                (int) (level.hp_of_exenmy()*ratio_of_ability));
        /*return new EliteEnemy(
                MainActivity.width/2,
                (int) (Math.random() * MainActivity.height * 0.1) * 1,
                0,
                5,
                (int) 60);*/
    }
}
