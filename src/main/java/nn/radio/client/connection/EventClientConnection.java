package nn.radio.client.connection;

import nn.radio.dto.AuthDto;
import nn.radio.dto.KeyEventDto;
import nn.radio.dto.MouseEventDto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class EventClientConnection {
    ObjectOutputStream objectOutputStreamSender = null;
    private ObjectInputStream eventObjectInutStreamReceiver = null;

    public EventClientConnection (){
    }

    public void setObjectOutputStreamSender (ObjectOutputStream objectOutputStreamSender) {
        System.out.println("EventClientConnection");
        this.objectOutputStreamSender = objectOutputStreamSender;
    }

    public void setEventObjectInutStreamReceiver (ObjectInputStream eventObjectInutStreamReceiver) {
        System.out.println("EventClientConnection");
        this.eventObjectInutStreamReceiver = eventObjectInutStreamReceiver;
    }

    public void authSend (AuthDto e) {
        if (objectOutputStreamSender != null) {
            try {
                objectOutputStreamSender.writeObject(e);
                AuthDto e1 = (AuthDto) eventObjectInutStreamReceiver.readObject();
                System.out.println(e1.id + " " + e1.pwd);
            } catch (Exception ioException) {
                ioException.printStackTrace();
                throw new RuntimeException("Can't send authantication");
            }
        }
    }

    public void keyPressed (KeyEventDto e) {
        if (objectOutputStreamSender != null) {
            try {
                objectOutputStreamSender.writeObject(e);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public void keyReleased (KeyEventDto e) {
        if (objectOutputStreamSender != null) {
            try {
                objectOutputStreamSender.writeObject(e);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

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
