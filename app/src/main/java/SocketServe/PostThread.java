package SocketServe;

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
                pw.println(Game.Score+"");
                pw.flush();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}