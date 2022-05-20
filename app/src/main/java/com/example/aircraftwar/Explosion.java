package com.example.aircraftwar;

import static com.example.aircraftwar.Game.Score;

import aircraft.EliteEnemy;
import aircraft.MobEnemy;
import basic.AbstractFlyingObject;
import java.util.ArrayList;
import java.util.List;


public class Explosion {
    public void notifyAll(List<AbstractFlyingObject> explosionList){
        for (AbstractFlyingObject abstractFlyingObject:explosionList){
            abstractFlyingObject.vanish();
            if(abstractFlyingObject instanceof MobEnemy){
                Score += 10;
            }
            else if(abstractFlyingObject instanceof EliteEnemy){
                Score += 30;
            }
        }
    }

    public void explosion_happend(List<AbstractFlyingObject> explosionList){
        notifyAll(explosionList);
    }
}
