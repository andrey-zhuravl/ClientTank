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

public class EventClientConnection extends Thread implements KeyEventListener,
                                                        MouseClickedListener{
    private static final int KEY_EVENT_OUT_PORT = 4447;sdf
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

    public EventClientConnection (){
        System.out.println("EventClientConnection");
        isAlive = true;
    }

    private void startClientSocket () {
        while (!isClientConnected) {
            try {
                System.out.println("EventClientConnection Socket");
                clientSocket = new Socket("localhost", KEY_EVENT_OUT_PORT);
                System.out.println("EventClientConnection Socket " + KEY_EVENT_OUT_PORT);
            } catch (Exception e) {
                System.out.println("EventClientConnection Socket error");
            }

            try {
                System.out.println("EventClientConnection ObjectOutputStream");
                objectOutputStreamSender = new ObjectOutputStream(clientSocket.getOutputStream());
                System.out.println("EventClientConnection ObjectOutputStream OK");
                isClientConnected = true;
            } catch (Exception e) {
                System.out.println("EventClientConnection ObjectOutputStream error");
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
            System.out.println("EventClientConnection run");
            startClientSocket();
            System.out.println("EventClientConnection startServerSocket");
    }

    @Override
    public void keyPressed (KeyEventDto e) {
        try {
            objectOutputStreamSender.writeObject(e);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public void keyReleased (KeyEventDto e) {
        try {
            objectOutputStreamSender.writeObject(e);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public void mouseClicked (MouseEventDto e) {
        try {
            objectOutputStreamSender.writeObject(e);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
