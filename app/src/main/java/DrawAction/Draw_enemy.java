package DrawAction;

import static com.example.aircraftwar.Image_Manage.BOSS_ENEMY_IMAGE;
import static com.example.aircraftwar.Image_Manage.ELITE_ENEMY_IMAGE;
import static com.example.aircraftwar.Image_Manage.MOBENEMY_IMAGE;

import android.graphics.Canvas;

import java.util.List;

import aircraft.BossEnemy;
import aircraft.EliteEnemy;
import aircraft.Enemy;
import aircraft.MobEnemy;

public class Draw_enemy {
    public static void Draw_Mobenemy(List<MobEnemy> Mob_Enemy_List, Canvas canvas){
        for(MobEnemy mobEnemy:Mob_Enemy_List){
            canvas.drawBitmap(MOBENEMY_IMAGE,mobEnemy.getLocationX()-MOBENEMY_IMAGE.getWidth()/2,mobEnemy.getLocationY()-MOBENEMY_IMAGE.getHeight()/2,null);
        }
    }
    public static void Draw_Eliteenemy(List<EliteEnemy> Elite_Enemy_List,Canvas canvas){
        for(EliteEnemy eliteEnemy:Elite_Enemy_List){
            canvas.drawBitmap(ELITE_ENEMY_IMAGE,eliteEnemy.getLocationX()-ELITE_ENEMY_IMAGE.getWidth()/2,eliteEnemy.getLocationY()-ELITE_ENEMY_IMAGE.getHeight()/2,null);
        }
    }
    public static void Draw_Bossenemy(List<BossEnemy> Boss_Enemy_List,Canvas canvas){
        for(BossEnemy bossEnemy:Boss_Enemy_List){
            canvas.drawBitmap(bossEnemy.getImage(),bossEnemy.getLocationX()-BOSS_ENEMY_IMAGE.getWidth()/2,bossEnemy.getLocationY()-BOSS_ENEMY_IMAGE.getHeight()/2,null);
        }
    }
}
