package nn.radio.client.connection;

import nn.radio.client.listener.TankListener;
import nn.radio.dto.TankDto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

public class TankClientConnection extends Thread {
    ObjectInputStream reciever;
    private boolean isAlive = true;
    private TankListener tankListener = null;
    private boolean isServerConnected = false;

    public TankClientConnection (){
    }
    public void setReciever (ObjectInputStream reciever){
        System.out.println("TankClientConnection");
        isAlive = true;
        this.reciever = reciever;
    }



    @Override
    public void run(){
        while (isAlive){
            try {
                if(reciever != null && tankListener != null) {
                    Map<String, TankDto> map = (HashMap<String, TankDto>) reciever.readObject();
                    tankListener.updateTankMapWithDto(map);
                }
            } catch (Exception ioException) {
                System.out.println("TankClientConnection updateTankMapWithDto error");
            }
        }

        try {
            if(reciever != null) {
                reciever.close();
            }
            System.out.println("TankClientConnection close");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("TankClientConnection run end");
    }

    public void setTankListener(TankListener listener){
        this.tankListener = listener;
    }

    public void stopConnection(){
        isAlive = false;
    }
}
