package SocketServe;

import static com.example.aircraftwar.Game.Other_player_score;
import static com.example.aircraftwar.Game.gameOverFlag;
import static com.example.aircraftwar.WaitPreAcitivity.game_is_running;
import static com.example.aircraftwar.WaitPreAcitivity.is_ready;
import static com.example.aircraftwar.WaitPreAcitivity.ismuti;
import static com.example.aircraftwar.WaitPreAcitivity.preFlag;
import static com.example.aircraftwar.WaitPreAcitivity.wait_is_running;

import com.example.aircraftwar.Game;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PostThread extends Thread{
    Socket socket;
    public PostThread(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run(){
        try {
            //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            while(true){
                Thread.sleep(1000);
                //String str = br.readLine();
                if(wait_is_running){
                    if (is_ready) {
                        pw.println("ready");
                        pw.flush();
                    }
                    continue;
                }
                if (gameOverFlag) {
                        pw.println(Game.Score+"");
                        pw.flush();
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