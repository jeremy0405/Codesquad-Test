import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        MapReader mapReader = new MapReader();
//        int[][] stagemap = mapReader.getStages();
        mapReader.getStages(1);
        mapReader.getStages(2);

    }

}
