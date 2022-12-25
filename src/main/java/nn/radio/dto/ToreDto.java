package nn.radio.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class ToreDto implements Serializable {
    private static final long serialVersionUID = 6529685098267757691L;
    public String id;

    public float Y;
    public float X;
    public float alpha;
    public java.util.List<ChargeDto> clientChargeList = new ArrayList<>();


}
