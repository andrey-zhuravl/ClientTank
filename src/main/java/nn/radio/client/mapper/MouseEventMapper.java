package nn.radio.client.mapper;

import nn.radio.dto.MouseEventDto;

import java.awt.event.MouseEvent;

public class MouseEventMapper {

    public static MouseEventDto fromMouseEvent(MouseEvent e, String userId){
        MouseEventDto dto = new MouseEventDto();
        dto.x = e.getX();
        dto.y = e.getY();
        dto.userId = userId;
        dto.time = System.nanoTime();
        return dto;
    }
}
