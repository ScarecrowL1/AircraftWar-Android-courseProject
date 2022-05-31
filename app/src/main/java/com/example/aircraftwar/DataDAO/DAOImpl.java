package com.example.aircraftwar.DataDAO;

import android.content.Context;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class DAOImpl implements DAO {

    public static List<UserData> userDataList = new LinkedList<>();
    String division = "     ";
    //统一分隔

    @Override
    public void doAdd(int score, double time, String ID) {
        UserData userData = new UserData(score, time, ID);
        //生成当次添加时的日期
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        userData.setDateInfo(df.format(date));
        userDataList.add(userData);
    }

    @Override
    public void doSave(int gameMode){
        String dataFile = findUserData(gameMode);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(dataFile));
            bw.write("*********************   排行榜    *************************");
            bw.newLine();
            bw.write("*********                                        *********");
            bw.newLine();
            bw.write("***** name ************ score *** time ******* date ******");
            bw.newLine();
            bw.write("____________________________________________________________");
            bw.newLine();
            int rank = 0;
            //rank排名，写入时依次赋予
            for (UserData ud : userDataList) {
                StringBuilder sb = new StringBuilder();
                //division为四个空格
                sb.append(++rank).append(division)
                        .append(ud.getID()).append(division)
                        .append(ud.getScore()).append(division)
                        .append(ud.getTime()).append(division)
                        .append(ud.getDateInfo()).append(division);
                bw.write(sb.toString());
                bw.newLine();
                bw.flush();
            }
            bw.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void doRead(int gameMode) {
        String dataFile = findUserData(gameMode);
        try {
            File file = new File(dataFile);
            if (!file.isFile()) {
                file.createNewFile();
            }
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            int linenumber = 0;
            while ((line=br.readLine())!=null){
                //跳过前四行
                if(linenumber < 4){
                    linenumber++;
                    continue;
                }
                String[] strArray = line.split(division);
                String ID = strArray[1];
                int score = Integer.parseInt(strArray[2]);
                double time = Double.parseDouble(strArray[3]);
                String dateInfo = strArray[4];
                UserData ud = new UserData(score, time, ID);
                //给之前的数据赋上之前的数据
                ud.setDateInfo(dateInfo);

                //将构建好的userData加入到表中
                userDataList.add(ud);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 根据游戏模式的输入返回正确的本地文件路径
     *
     * @param gameMode 游戏模式
     * @return {@link String}
     */
    private String findUserData(int gameMode) {
        String dataFile;
        switch (gameMode){
            //普通模式文件
            case 1 : dataFile = "/data/data/com.example.aircraftwar/userdata_c.txt";
                break;
            //困难模式文件
            case 2 : dataFile = "/data/data/com.example.aircraftwar/userdata_h.txt";
                break;
            case 0 :
                //简单模式文件
            default: dataFile = "/data/data/com.example.aircraftwar/userdata.txt";
        }
        return dataFile;
    }

    @Override
    public void doRank() {
        Collections.sort(userDataList);
    }

    @Override
    public void printToConsole(int gameMode) {
        String dataFile = findUserData(gameMode);
        try {
            FileReader fileReader = new FileReader(dataFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line =bufferedReader.readLine();

            while (line!=null){
                System.out.println(line);
                line = bufferedReader.readLine();
            }

            bufferedReader.close();
            fileReader.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public List<UserData> getUserDataList() {
        return userDataList;
    }

    @Override
    public void doDelete(String dateInfo) {
        Iterator<UserData> iterator = userDataList.iterator();
        while (iterator.hasNext()) {
            UserData next = iterator.next();
            String dateInfo1 = next.getDateInfo();
            if (dateInfo1.equals(dateInfo)) {
                iterator.remove();
            }
        }
    }
}
