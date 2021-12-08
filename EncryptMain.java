import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EncryptMain {

    public static void main(String[] args) {

        File f = new File("map.txt");
        String data = Encrypt.fileToBinary(f);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("map_enc.txt"))) {
            writer.write(data);
        } catch (IOException e) {
            System.out.println("map_enc.txt 저장시 에러다!!");
        }

    }

}
