package nn.radio.dto;

import java.io.Serializable;

public class KeyEventDto implements Serializable {
    public int keyCode;
    public String paramString;
    public int getKeyCode () {
        return keyCode;
    }


    public void setKeyCode (int keyCode) {
        this.keyCode = keyCode;
    }
}
