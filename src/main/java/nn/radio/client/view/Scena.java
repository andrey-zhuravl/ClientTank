package nn.radio.client.view;

import nn.radio.TankiProperty;
import nn.radio.client.listener.KeyEventListener;
import nn.radio.client.listener.MouseClickedListener;
import nn.radio.client.listener.TankListener;
import nn.radio.client.mapper.KeyEventMapper;
import nn.radio.client.mapper.MouseEventMapper;
import nn.radio.client.mapper.TankMapper;
import nn.radio.client.model.ClientTank;
import nn.radio.client.model.ClientUser;
import nn.radio.dto.TankDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Scena extends JPanel implements ActionListener, MouseListener, KeyListener, TankListener {

    Map<String, ClientTank> tankMap = new ConcurrentHashMap<>();
    Map<String, ClientUser> userMap = new HashMap<>();
    private Timer timer;
    private KeyEventListener keyEventListener;
    private MouseClickedListener mouseClickedListener;
    private String clientUserId;
    private Object monitor = new Object();
    private JFrame frame;

    public Scena (TankiProperty tankiProperty) {
        super();
        this.setFocusable(true);
        this.requestFocusInWindow();
        grabFocus();
        addMouseListener(this);
        addKeyListener(this);
        this.clientUserId = tankiProperty.get("USER_ID");
        timer = new Timer(15, new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                frame.repaint();
            }
        });
        timer.start();
    }

    @Override
    public void updateTankMapWithDto (Map<String, TankDto> map) {
        map.values().forEach(dto -> {
            ClientTank tank = tankMap.get(dto.id);
            if (tank == null) {
                tank = TankMapper.fromDto(dto, clientUserId);
                tankMap.put(tank.id, tank);
            }
            synchronized (monitor) {
                tank.update(dto);
            }
        });
    }

    @Override
    public void paintComponent (Graphics g) {
        tankMap.values().forEach(t -> {
            synchronized (monitor) {
                t.draw(g);
            }
        });
    }

//===================================================================================

    @Override
    public void keyPressed (KeyEvent e) {
        keyEventListener.keyPressed(KeyEventMapper.fromKeyPressedEvent(e, clientUserId));
    }

    @Override
    public void keyReleased (KeyEvent e) {
        keyEventListener.keyReleased(KeyEventMapper.fromKeyReleasedEvent(e, clientUserId));
    }

    @Override
    public void keyTyped (KeyEvent e) {
    }


//===================================================================================

    @Override
    public void mouseClicked (MouseEvent e) {
        mouseClickedListener.mouseClicked(MouseEventMapper.fromMouseEvent(e, clientUserId));
    }

    @Override
    public void actionPerformed (ActionEvent e) {
    }

    @Override
    public void mousePressed (MouseEvent e) {
    }

    @Override
    public void mouseReleased (MouseEvent e) {
    }

    @Override
    public void mouseEntered (MouseEvent e) {
    }

    @Override
    public void mouseExited (MouseEvent e) {
    }

    public void setKeyEventListener (KeyEventListener listener) {
        this.keyEventListener = listener;
    }

    public void setMouseClickedListener (MouseClickedListener listener) {
        this.mouseClickedListener = listener;
    }

    public void setFrame (JFrame frame0) {
        this.frame = frame0;
    }
//====================================================================================
}
