package com.example.aircraftwar.DataDAO;


/**
 * 用户数据
 *
 * @author ScarecrowLi
 * @date 2022/04/28
 */
public class UserData implements Comparable<UserData>{
    public UserData(int score, double time) {
        setScore(score);
        setTime(time);
    }
    public UserData(int score, double time, String ID) {
        setScore(score);
        setTime(time);
        setID(ID);
    }
    private String ID = "testUser";
    private int score;
    private double time;
    private String dateInfo;

    public int getScore() {
        return score;
    }

    public double getTime() {
        return time;
    }

    public String getID() {
        return ID;
    }


    public void setScore(int score) {
        this.score = score;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * 按照分数比较
     *当分数不同时，比较时间，时间小者排前
     * @return int
     */
    @Override
    public int compareTo(UserData ud) {
        if(ud.getScore() == score){
            //扩大计算精度，由于此类的time的单位为秒，所以不做此操作会产生误差
            return (int) (time*1000 - ud.getTime()*1000);
        }
        return (int) (ud.getScore() - score);
    }

    public String getDateInfo() {
        return dateInfo;
    }

    public void setDateInfo(String dateInfo) {
        this.dateInfo = dateInfo;
    }
}
