package nn.radio.client.model;

import nn.radio.dto.TankDto;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import static nn.radio.Constants.BACKGROUND_COLOR;


public class ClientTank {

    public volatile java.util.List<ClientCharge> clientChargeList1;
    public java.util.Map<String, ClientCharge> chargeMap;
    private BufferedImage imgActive;
    private BufferedImage imgNonActive;
    private BufferedImage imgDaed;
    private BufferedImage img;

    public float Y;
    public float X;
    public float alpha = 0.0F;

    public static float TANK_HEIGHT = 109F;
    public static float TANK_WIDTH = 82F;
    public static int BG_BORDER = 3;

    public boolean isFocusable = false;
    public boolean isAlive = true;
    public ClientTorre tore;
    public ClientUser clientUser;
    public String id;

    public ClientTank (String id, float x, float y) {

        this.id = id;
        this.X = x;
        this.Y = y;
        tore = new ClientTorre(x, y);
        chargeMap = tore.getChargeMap();
        setFocusable(false);
        URL imgURLActive = getClass().getResource("/tankActive2.png");
        URL imgURLNonActive = getClass().getResource("/tankNotActive2.png");
        URL imgURLDaed = getClass().getResource("/tankDead.png");
        try {
            imgActive = ImageIO.read(imgURLActive);
            imgNonActive = ImageIO.read(imgURLNonActive);
            imgDaed = ImageIO.read(imgURLDaed);
            img = imgNonActive;
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("idTank = " + id);
    }

    public void draw (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(BACKGROUND_COLOR);
        g2d.rotate(Math.toRadians(alpha), (int) (X + TANK_HEIGHT / 2), (int) (Y + TANK_WIDTH / 2));
        g.fillRect((int) X - BG_BORDER, (int) Y - BG_BORDER, (int) TANK_HEIGHT + BG_BORDER, (int) TANK_WIDTH + BG_BORDER);
        g2d.rotate(Math.toRadians(-alpha), (int) (X + TANK_HEIGHT / 2), (int) (Y + TANK_WIDTH / 2));

        g2d.rotate(Math.toRadians(alpha), (int) (X + TANK_HEIGHT / 2), (int) (Y + TANK_WIDTH / 2));
        g.drawImage(img, (int) X, (int) Y, (int) TANK_HEIGHT, (int) TANK_WIDTH, null);
        tore.draw(g, (X + TANK_HEIGHT / 2.4F), (Y + TANK_WIDTH / 3), alpha);
        g2d.rotate(Math.toRadians(-alpha), (int) (X + TANK_HEIGHT / 2), (int) (Y + TANK_WIDTH / 2));
        tore.drawCharges(g);
    }

    public void setFocusable (boolean b) {
        isFocusable = b;
    }

    public boolean isFocusable () {
        return isFocusable;
    }

    public String getId () {
        return id;
    }

    public void update (TankDto t) {
        X = t.X;
        Y = t.Y;
        alpha = t.alpha;
        isFocusable = t.isFocusable;
        isAlive = t.isAlive;
        tore.X = t.toreDto.X;
        tore.Y = t.toreDto.Y;
        tore.alpha = t.toreDto.alpha;
        tore.chargeMap.clear();
        t.toreDto.clientChargeList.forEach(charge -> {
            ClientCharge c = tore.chargeMap.get(charge.id);
            if (c == null) {
                tore.chargeMap.put(charge.id, new ClientCharge(charge.id, charge.X, charge.Y, charge.alpha));
            } else {
                c.X = charge.X;
                c.Y = charge.Y;
                c.alive = charge.alive;
            }
        });

        if (isFocusable && isAlive) {
            img = imgActive;
        } else if (isAlive) {
            img = imgNonActive;
        } else {
            img = imgDaed;
        }
    }
}
