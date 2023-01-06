package nn.radio.client.model;

import nn.radio.client.view.Scena;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;

public class ClientCharge {


    public String id;
    public boolean alive = true;
    private BufferedImage imgActive;
    private BufferedImage imgNonActive;
    private BufferedImage img;

    public float Y;
    public float X;
    public static float CHARGE_HEIGHT = 14F;
    public static float CHARGE_WIDTH = 8F;
    public float alpha = 0.0F;
    float deltaAlpha = 0.0F;
    float speedAlpha = 0.3F;

    float deltaX = 0.0F;
    float deltaY = 0.0F;
    public float speed = 1.0F;

    public ClientCharge (String id, float x, float y, float alpha) {
        this.id = id;
        this.X = x;
        this.Y = y;
        this.alpha = alpha;
        URL imgURLActive = getClass().getResource("/images/charge1.png");
        URL imgURLNonActive = getClass().getResource("/images/charge1.png");
        try {
            imgActive = ImageIO.read(imgURLActive);
            imgNonActive = ImageIO.read(imgURLNonActive);
            img = imgNonActive;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw (Graphics g, float torreWidth) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.rotate(Math.toRadians(alpha),  (int) X, (int) Y);
        g.setColor(Color.WHITE);
        g.drawRect((int) (X ), (int) (Y ), (int) 5, (int) 5);
        g.drawImage(img, (int) (X ), (int) (Y ), (int) CHARGE_HEIGHT, (int) CHARGE_WIDTH, null);
        g2d.rotate(Math.toRadians(-alpha),  (int) X, (int) Y);
    }

    public float getX () {
        return X;
    }
    public float getY () {
        return Y;
    }
}
