package nn.radio.client.listener;

import nn.radio.dto.TankDto;

import java.util.Map;

public interface TankListener {
    void updateTankMapWithDto (Map<String, TankDto> map);
}
