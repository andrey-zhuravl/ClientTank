package nn.radio.client.connection;

import nn.radio.client.TankListener;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class FullClientConnection extends Thread {
    private static final int KEY_EVENT_OUT_PORT = 14472;
    private static final int TANK_IN_PORT = 14473;
    private static final String IP = "192.168.1.10";
    private static final String LOCAL_IP = "127.0.0.1";
    public EventClientConnection eventClientConnection  = new EventClientConnection();
    public TankClientConnection tankClientConnection = new TankClientConnection();
    Socket eventClientSocket = null;
    Socket tankClientSocket = null;
    private boolean isConnected = false;
    private boolean isTankConnected = false;
    private boolean isEventConnected = false;
    private ObjectOutputStream objectOutputStreamSender;

    public FullClientConnection (){
    }

    private void startConnection(){
        while (!isConnected) {
            System.out.println("TankClientConnection startServerSocket " + TANK_IN_PORT);
            byte[] add= new byte[4];
            add[0] = (byte) 192;
            add[1] = (byte) 168;
            add[2] = (byte) 1;
            add[3] = (byte) 10;
            try {
//                eventClientSocket = new Socket(InetAddress.getByAddress(add), KEY_EVENT_OUT_PORT);
//                tankClientSocket = new Socket(InetAddress.getByAddress(add), TANK_IN_PORT);
                eventClientSocket = new Socket(InetAddress.getByName(LOCAL_IP), KEY_EVENT_OUT_PORT);
                tankClientSocket = new Socket(InetAddress.getByName(LOCAL_IP), TANK_IN_PORT);
                isConnected = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void creatEventConnection () {
        while (!isEventConnected) {
            try {
                System.out.println("TankClientConnection startServerSocket clientSocket" + eventClientSocket == null);
                objectOutputStreamSender = new ObjectOutputStream(eventClientSocket.getOutputStream());
                isEventConnected = true;
                System.out.println("TankClientConnection startServerSocket reciever");
                eventClientConnection.setObjectOutputStreamSender(objectOutputStreamSender);
            } catch (IOException e) {
                System.out.println("TankClientConnection ObjectInputStream error");
            }
        }
    }

    private void createTankConnection () {
        while (!isTankConnected) {
            try {
                System.out.println("TankClientConnection startServerSocket clientSocket" + tankClientSocket == null);
                ObjectInputStream reciever = new ObjectInputStream(tankClientSocket.getInputStream());
                System.out.println("TankClientConnection startServerSocket reciever");
                tankClientConnection.setReciever(reciever);
                tankClientConnection.start();
                isTankConnected = true;
            } catch (IOException e) {
                System.out.println("TankClientConnection ObjectInputStream error");
            }
        }
    }

    @Override
    public void run(){
        startConnection();
        creatEventConnection();
        createTankConnection();
        System.out.println("TankClientConnection run end");
    }

    public void setTankListener(TankListener listener){
        this.tankClientConnection.setTankListener(listener);
    }
}
