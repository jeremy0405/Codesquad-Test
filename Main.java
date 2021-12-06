import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        MapReader mapReader = new MapReader();
        int stageNum = mapReader.getStageSize();

        for (int i = 1; i <= stageNum; i++) {
            String[][] map = mapReader.getStages(i);

            PrintMap.print(map, i, mapReader);

        }


    }

}
