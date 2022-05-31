package com.example.aircraftwar.DataDAO;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * 数据管理接口
 *
 * @author ScarecrowLi
 * @date 2022/04/28
 */
public interface DAO {
    /**
     * 添加本次数据
     *
     * @param score 分数
     * @param time  时间
     */
    void doAdd(int score, double time, String ID);

    /**
     * 保存到本地
     */
    void doSave(int gameMode);

    /**
     * 从本地中读取
     */
    void doRead(int gameMode);

    /**
     * 进行排名
     */
    void doRank();


    /**
     * 做删除
     *通过日期信息的唯一性精确定位
     * @param dateInfo 日期信息
     */
    void doDelete(String dateInfo);

    /**
     * 打印到控制台
     */
    void printToConsole(int gameMode);
}
