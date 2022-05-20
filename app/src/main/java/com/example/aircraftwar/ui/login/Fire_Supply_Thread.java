package com.example.aircraftwar.ui.login;

import ShootStrategy.divergentShoot;
import ShootStrategy.straightShoot;
import aircraft.HeroAircraft;
public class Fire_Supply_Thread extends Thread{
    private HeroAircraft heroAircraft;
    private void Continue() throws InterruptedException {
        heroAircraft = HeroAircraft.getInstance();
        heroAircraft.setShootNum(5);
        heroAircraft.setShootStrategy(new divergentShoot());
        Thread.sleep(10000);
        heroAircraft.setShootStrategy(new straightShoot());
        heroAircraft.setShootNum(1);
    }
    @Override
    public void run(){
        try {
            Continue();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
