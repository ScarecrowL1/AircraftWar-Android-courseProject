package SocketServe;

import static com.example.aircraftwar.Game.Other_player_score;
import static com.example.aircraftwar.Game.gameOverFlag;
import static com.example.aircraftwar.MenuActivity.level;
import static com.example.aircraftwar.WaitPreAcitivity.game_is_running;
import static com.example.aircraftwar.WaitPreAcitivity.is_ready;
import static com.example.aircraftwar.WaitPreAcitivity.other_ready;
import static com.example.aircraftwar.WaitPreAcitivity.preFlag;
import static com.example.aircraftwar.WaitPreAcitivity.wait_is_running;

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
            while(true){
                Thread.sleep(1000);
                if(wait_is_running){
                    if (is_ready) {
                        String str = br.readLine();
                        other_ready = str.equals("ready");
                    }
                    continue;
                }
                if (!gameOverFlag) {
                        String str = br.readLine();
                        Other_player_score = str;
                        continue;
                }
                break;
            }
            socket.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}