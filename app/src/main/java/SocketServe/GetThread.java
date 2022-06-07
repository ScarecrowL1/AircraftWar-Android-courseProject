package SocketServe;

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
                String str = br.readLine();
                Log.e("Score",str);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}