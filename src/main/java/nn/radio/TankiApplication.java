package nn.radio;

import nn.radio.client.connection.FullClientConnection;
import nn.radio.client.view.Scena;

import javax.swing.*;
import java.awt.*;

import static nn.radio.Constants.*;

public class TankiApplication {
    static TankiProperty tankiProperty = new TankiProperty();

    public static void main (String[] args) throws InterruptedException {

        tankiProperty.readProperies();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        GraphicsDevice gd0 = gs[0];

        FullClientConnection fullClientConnection = new FullClientConnection(tankiProperty);
        Scena scena0 = new Scena(tankiProperty);

        fullClientConnection.setTankListener(scena0);
        scena0.setKeyEventListener(fullClientConnection);
        scena0.setMouseClickedListener(fullClientConnection);


        JFrame frame0 = new JFrame(gd0.getDefaultConfiguration());
        scena0.setFrame(frame0);
        frame0.setTitle("TANKS MODEL0");
        frame0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame0.setSize(SCENA_WIDTH + SCENA_BORDER * 2, SCENA_HEIGTH + SCENA_BORDER * 2);
        frame0.setBackground(BACKGROUND_COLOR);
        frame0.setResizable(true);
        frame0.add(scena0);
        frame0.setVisible(true);
        fullClientConnection.start();
    }
}
