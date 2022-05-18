package factory;

import static com.example.aircraftwar.Image_Manage.ELITE_ENEMY_IMAGE;

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
        /*return new EliteEnemy(
                (int) (Math.random() * (MainActivity.width - ELITE_ENEMY_IMAGE.getWidth()/2)) * 1,
                (int) (Math.random() * MainActivity.height * 0.1) * 1,
                0,
                20,
                (int) 60);*/
        return new EliteEnemy(
                MainActivity.width/2,
                (int) (Math.random() * MainActivity.height * 0.1) * 1,
                0,
                5,
                (int) 60);
    }
}
