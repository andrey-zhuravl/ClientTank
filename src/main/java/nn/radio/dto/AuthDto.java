package nn.radio.dto;

import java.io.Serializable;

public class AuthDto implements Serializable {
    private static final long serialVersionUID = 6528685098267757694L;

    public AuthDto (String id, String pwd) {
        this.id = id;
        this.pwd = pwd;
    }

    public String id;
    public String pwd;
}
