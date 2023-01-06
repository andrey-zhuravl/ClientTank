package nn.radio.dto;


import java.io.Serializable;


public class TankDto implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    public String id;
    public String userId;
    public float Y;
    public float X;
    public float alpha;

    public boolean isFocusable = false;
    public boolean isAlive = true;

    public ToreDto toreDto;

}
