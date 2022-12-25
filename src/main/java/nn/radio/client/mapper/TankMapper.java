package nn.radio.client.mapper;

import nn.radio.client.model.ClientTank;
import nn.radio.dto.TankDto;

public class TankMapper {

    public static ClientTank fromDto (TankDto t) {
        ClientTank clientTank = new ClientTank(t.id, t.X, t.Y);
        clientTank.id = t.id;
        clientTank.X = t.X;
        clientTank.Y = t.Y;
        clientTank.alpha = t.alpha;
        clientTank.tore = ToreMapper.fromTore(t.toreDto);
        return clientTank;
    }

    public static TankDto fromTank (ClientTank t) {
        TankDto dto = new TankDto();
        dto.id = t.id;
        dto.X = t.X;
        dto.Y = t.Y;
        dto.alpha = t.alpha;
        dto.isFocusable = t.isFocusable();
        dto.toreDto = ToreMapper.fromClientTore(t.tore);
        dto.isAlive = t.isAlive;
        return dto;
    }
}
