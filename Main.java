import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        MapReader mapReader = new MapReader();
        int stageNum = mapReader.getStageSize();

        for (int i = 1; i <= stageNum; i++) {
            String[][] map = mapReader.getStages(i);

            //todo print하는 클래스 호출

            for (int j = 0; j < map.length; j++) {
                for (int k = 0; k < map[j].length; k++) {
                    System.out.print(map[j][k]);
                }
                System.out.println("");
            }
            System.out.println("가로 크기" + mapReader.getWidth().get(i - 1));
            System.out.println("세로 크기" + mapReader.getHeight().get(i - 1));

        }


    }

}
