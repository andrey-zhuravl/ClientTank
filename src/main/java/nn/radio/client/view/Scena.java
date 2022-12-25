package nn.radio.client.view;

import nn.radio.client.KeyEventListener;
import nn.radio.client.MouseClickedListener;
import nn.radio.client.TankListener;
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

    private KeyEventListener keyEventListener;
    private MouseClickedListener mouseClickedListener;

    public Scena () {
        super();
        this.setFocusable(true);
        this.requestFocusInWindow();
        grabFocus();
        addMouseListener(this);
        addKeyListener(this);
    }

    @Override
    public void updateTankMapWithDto(Map<String, TankDto> map){
        map.values().forEach(dto->{
            ClientTank tank = tankMap.get(dto.id);
            if(tank == null){
                tank = TankMapper.fromDto(dto);
                tankMap.put(tank.id, tank);
            }
            tank.update(dto);
        });
    }

    @Override
    public void paintComponent (Graphics g) {
        tankMap.values().forEach(t -> t.draw(g));
        repaint();
    }

//===================================================================================

    @Override
    public void keyPressed (KeyEvent e) {
        keyEventListener.keyPressed(KeyEventMapper.fromKeyPressedEvent(e));
    }

    @Override
    public void keyReleased (KeyEvent e) {
        keyEventListener.keyReleased(KeyEventMapper.fromKeyReleasedEvent(e));
    }

    @Override
    public void keyTyped (KeyEvent e) {
    }


//===================================================================================

    @Override
    public void mouseClicked (MouseEvent e) {
        mouseClickedListener.mouseClicked(MouseEventMapper.fromMouseEvent(e));
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

    public void setKeyEventListener(KeyEventListener listener){
        this.keyEventListener = listener;
    }

    public void setMouseClickedListener(MouseClickedListener listener){
        this.mouseClickedListener = listener;
    }
//====================================================================================
}
