package nn.radio.client.mapper;

import nn.radio.dto.KeyEventDto;

import java.awt.event.KeyEvent;

public class KeyEventMapper {

    public static KeyEventDto fromKeyPressedEvent(KeyEvent e, String userId){
        KeyEventDto dto = new KeyEventDto();
        dto.setKeyCode(e.getKeyCode());
        dto.userId = userId;
        dto.paramString = "KEY_PRESSED";
        dto.time = System.nanoTime();
        return dto;
    }

    public static KeyEventDto fromKeyReleasedEvent(KeyEvent e, String userId){
        KeyEventDto dto = new KeyEventDto();
        dto.setKeyCode(e.getKeyCode());
        dto.userId = userId;
        dto.paramString = "KEY_RELEASED";
        dto.time = System.nanoTime();
        return dto;
    }
}
