package nn.radio.client.connection;

import nn.radio.client.KeyEventListener;
import nn.radio.client.MouseClickedListener;
import nn.radio.dto.KeyEventDto;
import nn.radio.dto.MouseEventDto;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class EventClientConnection implements KeyEventListener,
        MouseClickedListener {
    ObjectOutputStream objectOutputStreamSender = null;

    public EventClientConnection (){

    }

    public void setObjectOutputStreamSender (ObjectOutputStream objectOutputStreamSender) {
        System.out.println("EventClientConnection");
        this.objectOutputStreamSender = objectOutputStreamSender;
    }

    @Override
    public void keyPressed (KeyEventDto e) {
        if (objectOutputStreamSender != null) {
            try {
                objectOutputStreamSender.writeObject(e);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    @Override
    public void keyReleased (KeyEventDto e) {
        if (objectOutputStreamSender != null) {
            try {
                objectOutputStreamSender.writeObject(e);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    @Override
    public void mouseClicked (MouseEventDto e) {
        if (objectOutputStreamSender != null) {
            try {
                objectOutputStreamSender.writeObject(e);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
