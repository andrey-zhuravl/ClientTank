package nn.radio.client.mapper;

import nn.radio.dto.KeyEventDto;

import java.awt.event.KeyEvent;

public class KeyEventMapper {

    public static KeyEventDto fromKeyPressedEvent(KeyEvent e){
        KeyEventDto dto = new KeyEventDto();
        dto.setKeyCode(e.getKeyCode());
        dto.paramString = "KEY_PRESSED";
        return dto;
    }

    public static KeyEventDto fromKeyReleasedEvent(KeyEvent e){
        KeyEventDto dto = new KeyEventDto();
        dto.setKeyCode(e.getKeyCode());
        dto.paramString = "KEY_RELEASED";
        return dto;
    }
}
