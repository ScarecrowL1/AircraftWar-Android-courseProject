package SocketServe;

import static com.example.aircraftwar.Game.Other_player_score;
import static com.example.aircraftwar.Game.gameOverFlag;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class GetThread extends Thread{
    Socket socket;
    public GetThread(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run(){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(true && gameOverFlag == false){
                Thread.sleep(1000);
                String str = br.readLine();
                Other_player_score = str;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}