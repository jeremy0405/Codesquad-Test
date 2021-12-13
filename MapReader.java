import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapReader {

    private List<String> lines;
    private final List<Integer> stageLine = new ArrayList<>();
    private final List<Position> playerPosition = new ArrayList<>();
    private final List<Position> initPosition = new ArrayList<>();

    MapReader() {
        readMapFile();
        countStage();
    }

    private void readMapFile() {
        String data = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("map_enc.txt"))) {
            data = reader.readLine();
        } catch (IOException e) {
            System.out.println("map_enc.txt 파일 읽기 오류 발생");
        }
        this.lines = Arrays.asList(Decode.binaryFileToString(data).split("\n"));
    }

    private void countStage() {
        stageLine.add(1);
        int size = lines.size();
        for (int i = 0; i < size; i++) {
            if (lines.get(i).contains("=")) {
                stageLine.add(i + 2);
            }
        }
    }

    public int getStageSize() {
        return stageLine.size();
    }

    public int[][] getStages(int stageNum) {

        int[] startToLast = setStartToLast(stageNum);
        int start = startToLast[0];
        int last = startToLast[1];

        int intHeight = last - start;
        int intWidth = lines.get(start).length();

        int[][] map = new int[intHeight][intWidth];
        setMap(start, last, intWidth, map);

        return map;
    }

    private int[] setStartToLast(int stageNum) {
        int[] startToLast = new int[2];
        if (stageNum == stageLine.size()) {
            startToLast[0] = stageLine.get(stageNum - 1);
            startToLast[1] = lines.size();
        } else {
            startToLast[0] = stageLine.get(stageNum - 1);
            startToLast[1] = stageLine.get(stageNum) - 2;
        }
        return startToLast;
    }

    private void setMap(int start, int last, int width, int[][] map) {
        int k = 0;
        for (int i = start; i < last; i++) {
            String line = lines.get(i);
            for (int j = 0; j < width; j++) {
                classifyMap(map, k, j, line);
            }
            k++;
        }
    }

    private void classifyMap(int[][] map, int k, int j, String line) {
        if (line.charAt(j) == '#') {
            map[k][j] = 9;
        }
        if (line.charAt(j) == 'O') {
            map[k][j] = 1;
        }
        if (line.charAt(j) == 'o') {
            map[k][j] = 2;
        }
        if (line.charAt(j) == '0') {
            map[k][j] = 3;
        }
        if (line.charAt(j) == 'P') {
            map[k][j] = 4;
            playerPosition.add(new Position(k, j));
            initPosition.add(new Position(k, j));
        }
    }

    public List<Position> getPlayerPosition() {
        return playerPosition;
    }

    public List<Position> getInitPosition() {
        return initPosition;
    }

}
