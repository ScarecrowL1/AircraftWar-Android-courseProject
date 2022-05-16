package DrawAction;

import static com.example.aircraftwar.Image_Manage.MOBENEMY_IMAGE;

import android.graphics.Canvas;

import java.util.List;

import aircraft.Enemy;
import aircraft.MobEnemy;

public class Draw_enemy {
    public static void Draw_Mobenemy(List<MobEnemy> Mob_Enemy_List, Canvas canvas){
        for(MobEnemy mobEnemy:Mob_Enemy_List){
            canvas.drawBitmap(MOBENEMY_IMAGE,mobEnemy.getLocationX(),mobEnemy.getLocationY(),null);
        }
    }
}
