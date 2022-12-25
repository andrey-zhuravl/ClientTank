package nn.radio.client.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ClientTorre {
    public volatile List<ClientCharge> clientChargeList = new ArrayList<>();
    public Map<String, ClientCharge> chargeMap = new ConcurrentHashMap<>();
    private BufferedImage imgActive;
    private BufferedImage imgNonActive;
    private BufferedImage img;

    public String id;
    public float Y;
    public  float X;
    public static float TORRE_HEIGHT = ClientTank.TANK_HEIGHT/3;
    public static float TORRE_WIDTH = ClientTank.TANK_WIDTH/3;
    public  float alpha = 0.0F;
    public boolean isFocusable;

    public ClientTorre (float x, float y) {
        this.X = x;
        this.Y = y;

        setFocusable(false);
        URL imgURLActive = getClass().getResource("/torre1.png");
        URL imgURLNonActive = getClass().getResource("/torreNotActive.png");
        try {
            imgActive = ImageIO.read(imgURLActive);
            imgNonActive = ImageIO.read(imgURLNonActive);
            img = imgActive;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw (Graphics g, float baseX, float baseY, float baseAlpha) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.rotate(Math.toRadians(alpha), (X + TORRE_HEIGHT/4), (Y + TORRE_WIDTH/2));
        g.drawImage(img, (int) (X ), (int) (Y ), (int) TORRE_HEIGHT, (int) TORRE_WIDTH, null);
        g2d.rotate(Math.toRadians(-(alpha)), (X + TORRE_HEIGHT/4), (Y + TORRE_WIDTH/2));
    }

    public void drawCharges(Graphics g){
        System.out.println("chargeMap=" + chargeMap);
        chargeMap.values().forEach(clientCharge -> {
            clientCharge.draw(g, chargeMap.values());
        });
    }

    public void setFocusable (boolean b) {
        isFocusable = b;
    }

    public boolean isFocusable () {
        return isFocusable;
    }

    public Map<String, ClientCharge> getChargeMap () {
       return chargeMap;
    }


}
