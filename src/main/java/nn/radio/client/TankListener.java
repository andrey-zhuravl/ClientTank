package nn.radio.client;

import nn.radio.dto.TankDto;

import java.util.Map;

public interface TankListener {
    void updateTankMapWithDto (Map<String, TankDto> map);
}
