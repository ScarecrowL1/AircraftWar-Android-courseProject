package game_difficulty_method;

public class hard_difficulty extends difficulty_method{

    @Override
    public int size_of_total_mobenemy() {
        return 5;
    }

    @Override
    public int limit_of_boss_come_out() {
        return 300;
    }

    @Override
    public int hp_of_enemy() {
        return 30;
    }

    @Override
    public int hp_of_exenmy() {
        return 90;
    }

    @Override
    public int enemy_come_out_time() {
        return 1;
    }

    @Override
    public int hp_of_boss() {
        return 400;
    }

    @Override
    public int size_of_total_exenemy() {
        return 2;
    }

    @Override
    public int size_of_total_boss() {
        return 1;
    }

    @Override
    public double ratio_of_ability_increase() {
        return 0.02;
    }
}
