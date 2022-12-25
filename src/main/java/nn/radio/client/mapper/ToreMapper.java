package nn.radio.client.mapper;

import nn.radio.client.model.ClientTorre;
import nn.radio.dto.ToreDto;

import java.util.stream.Collectors;

public class ToreMapper {

    public static ToreDto fromClientTore (ClientTorre t) {
        ToreDto dto = new ToreDto();
        dto.id = t.id;
        dto.X = t.X;
        dto.Y = t.Y;
        dto.alpha = t.alpha;
        dto.clientChargeList = t.getChargeMap().values().stream()
                .map(ch -> ChargeMapper.fromClientCharge(ch))
                .collect(Collectors.toList());
        return dto;
    }

    public static ClientTorre fromTore (ToreDto t) {
        ClientTorre dto = new ClientTorre(t.X, t.Y);
        dto.id = t.id;
        dto.X = t.X;
        dto.Y = t.Y;
        dto.alpha = t.alpha;
        dto.clientChargeList = t.clientChargeList.stream()
                .map(ch -> ChargeMapper.fromServerCharge(ch))
                .collect(Collectors.toList());
        return dto;
    }
}
