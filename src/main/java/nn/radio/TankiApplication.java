package nn.radio;

import nn.radio.client.connection.EventClientConnection;
import nn.radio.client.connection.TankClientConnection;
import nn.radio.client.view.Scena;

import javax.swing.*;
import java.awt.*;

import static nn.radio.Constants.*;

public class TankiApplication {
    public static void main(String[] args) throws InterruptedException {

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        GraphicsDevice gd0 = gs[0];

        TankClientConnection tankClientConnection = new TankClientConnection();
        EventClientConnection eventClientConnection = new EventClientConnection();
        Scena scena0 = new Scena();

        tankClientConnection.setTankListener(scena0);
        scena0.setKeyEventListener(eventClientConnection);
        scena0.setMouseClickedListener(eventClientConnection);


        JFrame frame0 = new JFrame(gd0.getDefaultConfiguration());
        frame0.setTitle("TANKS MODEL0");
        frame0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame0.setSize(SCENA_WIDTH + SCENA_BORDER * 2, SCENA_HEIGTH + SCENA_BORDER * 2);
        frame0.setBackground(BACKGROUND_COLOR);
        frame0.setResizable(true);
        frame0.add(scena0);
        frame0.setVisible(true);
        eventClientConnection.start();
        tankClientConnection.start();
    }
}
