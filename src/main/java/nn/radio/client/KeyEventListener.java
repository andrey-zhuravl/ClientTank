package nn.radio.client;

import nn.radio.dto.KeyEventDto;

public interface KeyEventListener {
    void keyPressed (KeyEventDto e);
    void keyReleased (KeyEventDto e);
}
