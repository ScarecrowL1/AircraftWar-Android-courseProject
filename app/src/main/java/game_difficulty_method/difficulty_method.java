package game_difficulty_method;

public abstract class difficulty_method {
    public abstract int size_of_total_mobenemy(); //不随时间改变
    public abstract int limit_of_boss_come_out(); //不随时间改变
    public abstract int hp_of_enemy();  //初始值设定后随时间改变
    public abstract int hp_of_exenmy();
    public abstract int hp_of_boss();   //初始值设定后每次出现改变
    public abstract int size_of_total_exenemy();    //不随时间改变
    public abstract int size_of_total_boss(); //不随时间改变
    public abstract double ratio_of_ability_increase(); //增加属性的倍率
    public abstract int enemy_come_out_time();  //不随时间改变,单位：秒
}
