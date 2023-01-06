package nn.radio;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

public class TankiProperty {
    Properties properties = new Properties();

    public void readProperies () {
        try {
            String configFilePath = "config.properties";
            FileInputStream propsInput = new FileInputStream(configFilePath);
            properties.load(propsInput);
            properties.forEach((k, v) -> {
                System.out.println(k + " " + v.toString());
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String get (String key) {
        return properties.getProperty(key);
    }

    public String get (String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public byte[] getAsByteArray (String key) {
        byte[] arr = new byte[4];
        String prop = properties.getProperty(key, "127.0.0.1");

        Object[] obArr = Arrays.stream(prop.split("\\."))
                .map(s -> (byte) Integer.parseInt(s)).toArray();
        
                for(int i = 0; i<4; i++){
                    arr[i] = (byte) obArr[i];
                }
        return arr;
    }
}
