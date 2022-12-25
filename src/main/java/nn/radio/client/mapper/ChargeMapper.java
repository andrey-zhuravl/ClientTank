package nn.radio.client.mapper;

import nn.radio.client.model.ClientCharge;
import nn.radio.dto.ChargeDto;

public class ChargeMapper {

    public static ChargeDto fromClientCharge (ClientCharge t) {
        ChargeDto dto = new ChargeDto();
        dto.id = t.id;
        dto.X = t.X;
        dto.Y = t.Y;
        dto.alpha = t.alpha;
        dto.alive = t.alive;
        return dto;
    }

    public static ClientCharge fromServerCharge (ChargeDto t) {
        ClientCharge clientCharge = new ClientCharge(t.id, t.X, t.Y, t.alpha);
        clientCharge.id = t.id;
        clientCharge.X = t.X;
        clientCharge.Y = t.Y;
        clientCharge.alpha = t.alpha;
        clientCharge.alive = t.alive;
        return clientCharge;
    }
}
