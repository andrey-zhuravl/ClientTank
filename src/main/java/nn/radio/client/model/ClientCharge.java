package nn.radio.client.model;

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
    public static float CHARGE_HEIGHT = 100F;
    public static float CHARGE_WIDTH = 20F;
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
        URL imgURLActive = getClass().getResource("/charge1.png");
        URL imgURLNonActive = getClass().getResource("/charge1.png");
        try {
            imgActive = ImageIO.read(imgURLActive);
            imgNonActive = ImageIO.read(imgURLNonActive);
            img = imgNonActive;
        } catch (IOException e) {
            e.printStackTrace();
        }
        deltaX = (float) (Math.cos(Math.toRadians(alpha))*speed);
        deltaY = (float) (Math.sin(Math.toRadians(alpha))*speed);
    }

    public void draw (Graphics g, Collection<ClientCharge> list) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.rotate(Math.toRadians(alpha),  (int) X, (int) Y);
        g.drawImage(img, (int) X, (int) Y, (int) CHARGE_HEIGHT, (int) CHARGE_WIDTH, null);
        g2d.rotate(Math.toRadians(-alpha),  (int) X, (int) Y);
    }

    public float getX () {
        return X;
    }
    public float getY () {
        return Y;
    }
}
