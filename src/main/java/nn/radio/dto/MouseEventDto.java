package nn.radio.dto;

import java.awt.*;
import java.io.Serializable;

public class MouseEventDto implements Serializable {
    public int x;
    public int y;

    public Point getPoint() {
        return new Point(x, y);
    }

}
