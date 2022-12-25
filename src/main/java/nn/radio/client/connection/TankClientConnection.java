package nn.radio.client.connection;

import nn.radio.client.KeyEventListener;
import nn.radio.client.MouseClickedListener;
import nn.radio.client.TankListener;
import nn.radio.dto.KeyEventDto;
import nn.radio.dto.MouseEventDto;
import nn.radio.dto.TankDto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class TankClientConnection extends Thread {
    private static final int TANK_IN_PORT = 4446;
    Socket clientSocket;
    ServerSocket serverSocket;
    Socket socket;
    ObjectOutputStream objectOutputStreamSender;
    ObjectInputStream reciever;
    private boolean isAlive;
    private TankListener tankListener;
    private boolean isConnected = false;
    private boolean isClientConnected = false;
    private boolean isServerConnected = false;

    public TankClientConnection (){
        System.out.println("TankClientConnection");
        isAlive = true;
    }

    private void startServerSocket () {
        while (!isServerConnected) {
            try {
                System.out.println("TankClientConnection startServerSocket " + TANK_IN_PORT);
                serverSocket = new ServerSocket(TANK_IN_PORT);
            } catch (Exception e) {
                System.out.println("TankClientConnection startServerSocket error");
            }

            try {
                socket = serverSocket.accept();
            } catch (Exception e) {
                System.out.println("TankClientConnection" +
                        "TankClientConnection serverSocket.accept() error");
            }

            try {
                reciever = new ObjectInputStream(socket.getInputStream());
                isServerConnected = true;
            } catch (IOException e) {
                System.out.println("TankClientConnection" +
                        "TankClientConnection ObjectInputStream error");
            }

            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run(){
            System.out.println("TankClientConnection" +
                    "TankClientConnection run");
            startServerSocket();
            System.out.println("TankClientConnection" +
                    "TankClientConnection startServerSocket");

        while (isAlive){
            try {
                Map<String, TankDto> map = (HashMap<String, TankDto>) reciever.readObject();
                tankListener.updateTankMapWithDto(map);
                continue;
            } catch (Exception ioException) {
                System.out.println("TankClientConnection" +
                        "TankClientConnection updateTankMapWithDto error");
            }
        }
    }

    public void setTankListener(TankListener listener){
        this.tankListener = listener;
    }
}
