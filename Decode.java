import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Base64.Decoder;

public class Decode {

    public static String binaryFileToString(String data){
        Decoder decoder = Base64.getDecoder();
        byte[] byteData = decoder.decode(data);

        return new String(byteData, StandardCharsets.UTF_8);
    }

}
