package nn.radio.client.connection;

import nn.radio.TankiProperty;
import nn.radio.client.listener.KeyEventListener;
import nn.radio.client.listener.MouseClickedListener;
import nn.radio.client.listener.TankListener;
import nn.radio.dto.AuthDto;
import nn.radio.dto.KeyEventDto;
import nn.radio.dto.MouseEventDto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class FullClientConnection extends Thread implements KeyEventListener,
        MouseClickedListener {
    private static final String IP = "192.168.1.10";
    private static final String LOCAL_IP = "127.0.0.1";
    public EventClientConnection eventClientConnection = new EventClientConnection();
    public TankClientConnection tankClientConnection = new TankClientConnection();
    Socket eventClientSocket = null;
    Socket tankClientSocket = null;
    private boolean isConnected = false;
    private boolean isTankConnected = false;
    private boolean isEventConnected = false;
    private ObjectOutputStream eventObjectOutputStreamSender;
    private ObjectInputStream eventObjectInutStreamReceiver;
    TankiProperty property;

    public FullClientConnection (TankiProperty property) {
        this.property = property;
    }

    private void startConnection () {
        while (!isConnected) {
            System.out.println("TankClientConnection startServerSocket " + property.get("SERVER_PORT"));
            try {
                InetAddress eventClientInetAddress = InetAddress.getByAddress(property.getAsByteArray("SERVER_URL"));
                InetAddress tankClientInetAddress = InetAddress.getByAddress(property.getAsByteArray("SERVER_URL"));
//                InetAddress eventClientInetAddress = InetAddress.getByName(property.get("SERVER_URL", "localhost"));
//                InetAddress tankClientInetAddress = InetAddress.getByName(property.get("SERVER_URL", "localhost"));

                Integer eventPort = Integer.valueOf(property.get("SERVER_EVENT_PORT", "14473"));
                Integer tankPort = Integer.valueOf(property.get("SERVER_TANK_PORT", "14472"));

                eventClientSocket = new Socket(eventClientInetAddress,eventPort);
                tankClientSocket = new Socket(tankClientInetAddress,tankPort);
                isConnected = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void creatEventConnection () {
        while (!isEventConnected) {
            try {
                System.out.println("EventClientConnection startServerSocket clientSocket" + eventClientSocket == null);
                eventObjectOutputStreamSender = new ObjectOutputStream(eventClientSocket.getOutputStream());
                eventObjectInutStreamReceiver = new ObjectInputStream(eventClientSocket.getInputStream());
                isEventConnected = true;
                System.out.println("EventClientConnection objectOutputStreamSender");
                eventClientConnection.setObjectOutputStreamSender(eventObjectOutputStreamSender);
                eventClientConnection.setEventObjectInutStreamReceiver(eventObjectInutStreamReceiver);
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
    public void run () {
        startConnection();
        creatEventConnection();
        createTankConnection();
        System.out.println("TankClientConnection run end");
        AuthDto authDto = new AuthDto(property.get("USER_ID"), property.get("USER_PWD"));
        authSend(authDto);
    }

    public void setTankListener (TankListener listener) {
        this.tankClientConnection.setTankListener(listener);
    }

    public void authSend(AuthDto dto){
        eventClientConnection.authSend(dto);
    }

    @Override
    public void keyPressed (KeyEventDto e) {
        eventClientConnection.keyPressed(e);
    }

    @Override
    public void keyReleased (KeyEventDto e) {
        eventClientConnection.keyReleased(e);
    }

    @Override
    public void mouseClicked (MouseEventDto e) {
        eventClientConnection.mouseClicked(e);
    }
}
