package nn.radio.client.model;

import nn.radio.dto.TankDto;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;


public class ClientTank {

    private final Graphics2D g2dbf;
    public volatile java.util.List<ClientCharge> clientChargeList1;
    public java.util.Map<String, ClientCharge> chargeMap;
    private BufferedImage imgActive;
    private BufferedImage imgNonActive;
    private BufferedImage imgDaed;
    private BufferedImage img;
    BufferedImage bgImage;
    AffineTransform identityTrans = new AffineTransform();

    AffineTransform af = new AffineTransform();

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
    public String userId;
    public String clientUserId;


    public ClientTank (String id, float x, float y) {
        this.id = id;
        this.X = x;
        this.Y = y;
        tore = new ClientTorre(x, y);
        chargeMap = tore.getChargeMap();
        setFocusable(false);
        URL imgURLActive = getClass().getResource("/images/tankActive2.png");
        URL imgURLNonActive = getClass().getResource("/images/tankNotActive2.png");
        URL imgURLDaed = getClass().getResource("/images/tankDead.png");
        try {
            imgActive = ImageIO.read(imgURLActive);
            imgNonActive = ImageIO.read(imgURLNonActive);
            imgDaed = ImageIO.read(imgURLDaed);
            img = imgNonActive;
        } catch (IOException e) {
            e.printStackTrace();
        }

        bgImage = new BufferedImage((int) (TANK_HEIGHT), (int) (TANK_WIDTH), BufferedImage.TYPE_INT_RGB);
        g2dbf = img.createGraphics();
        System.out.println("idTank = " + id);
    }

    public void draw (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        //TODO разобраться с экономным поворотом картинки только внутри буфера
//        g2dbf.setTransform(identityTrans);
//        af.setToIdentity();
//        af.translate((int) X, (int) Y);
//        af.rotate(Math.toRadians(alpha), (int) TANK_HEIGHT, (int) TANK_WIDTH);
//        g2dbf.rotate(Math.toRadians(alpha), (int) (X + TANK_HEIGHT / 2), (int) (Y + TANK_WIDTH / 2));
//        g2dbf.drawImage(img,af,scena);
//        g2dbf.rotate(Math.toRadians(-alpha), (int) (X + TANK_HEIGHT / 2), (int) (Y + TANK_WIDTH / 2));
//        g2d.drawImage(bgImage,(int) X, (int) Y,scena);
            g2d.rotate(Math.toRadians(alpha), (int) (X + TANK_HEIGHT / 2), (int) (Y + TANK_WIDTH / 2));
            g2d.drawImage(img, (int) X, (int) Y, (int) TANK_HEIGHT, (int) TANK_WIDTH, null);
            tore.draw(g2d, (X + TANK_HEIGHT / 2.4F), (Y + TANK_WIDTH / 3), alpha);
            g2d.rotate(Math.toRadians(-alpha), (int) (X + TANK_HEIGHT / 2), (int) (Y + TANK_WIDTH / 2));
            tore.drawCharges(g2d);
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
            isFocusable = t.isFocusable && t.userId.equals(clientUserId);
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
