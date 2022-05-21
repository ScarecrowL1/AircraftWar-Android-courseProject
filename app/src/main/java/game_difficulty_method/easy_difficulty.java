package game_difficulty_method;

public class easy_difficulty extends difficulty_method{

    @Override
    public int size_of_total_mobenemy() {
        return 3;
    }

    @Override
    public int limit_of_boss_come_out() {
        return 10000000;
    }

    @Override
    public int hp_of_enemy() {
        return 30;
    }

    @Override
    public int hp_of_exenmy() { return 90;}

    @Override
    public int enemy_come_out_time() {
        return 3;
    }

    @Override
    public int hp_of_boss() {
        return 200;
    }

    @Override
    public int size_of_total_exenemy(){
        return 1;
    }

    @Override
    public int size_of_total_boss(){
        return 0;
    }

    @Override
    public double ratio_of_ability_increase() {
        return 0;
    }
}
