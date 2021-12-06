import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapReader {

    private List<String> lines;
    private final List<Integer> stageLine = new ArrayList<>();

    private final List<Integer> height = new ArrayList<>();
    private final List<Integer> width = new ArrayList<>();
    private final List<Integer> ballCount = new ArrayList<>();
    private final List<Integer> holeCount = new ArrayList<>();
    private final List<Position> playerLocation = new ArrayList<>();


    MapReader() {
        readMap();
        countStage();
    }

    private void readMap() {
        this.lines = Arrays.asList(StageData.mapdata.split("\n"));
    }

    private void countStage() {
        stageLine.add(0);
        int size = lines.size();
        for (int i = 0; i < size; i++) {
            if (lines.get(i).contains("=")) {
                stageLine.add(i + 1);
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
        setHeightAndWidth(intHeight, intWidth);

        int[][] map = new int[intHeight][intWidth];
        initMap(map);
        setMap(start, last, intWidth, map, stageNum);

        return map;
    }

    private int[] setStartToLast(int stageNum) {
        int[] startToLast = new int[2];
        if (stageNum == stageLine.size()) {
            startToLast[0] = stageLine.get(stageNum - 1) + 1;
            startToLast[1] = lines.size();
        } else {
            startToLast[0] = stageLine.get(stageNum - 1) + 1;
            startToLast[1] = stageLine.get(stageNum) - 1;
        }
        return startToLast;
    }

    private void setHeightAndWidth(int height, int width) {
        this.height.add(height);
        this.width.add(width);
    }

    private void initMap(int[][] map) {
        for (int[] ints : map) {
            Arrays.fill(ints, 9);
        }
    }

    private int[][] setMap(int start, int last, int width, int[][] map, int stageNum) {
        int k = 0;
        ballCount.add(0);
        holeCount.add(0);
        for (int i = start; i < last; i++) {
            String line = lines.get(i);
            for (int j = 0; j < width; j++) {
                if (line.charAt(j) == '#') {
                    map[k][j] = 0;
                }
                if (line.charAt(j) == 'O') {
                    map[k][j] = 1;
                    holeCount.set(stageNum - 1, holeCount.get(stageNum - 1) + 1);
                }
                if (line.charAt(j) == 'o') {
                    map[k][j] = 2;
                    ballCount.set(stageNum - 1, ballCount.get(stageNum - 1) + 1);
                }
                if (line.charAt(j) == 'P') {
                    map[k][j] = 3;
                    playerLocation.add(new Position(k, j));
                }
            }
            k++;
        }
        return map;
    }

    public List<Integer> getHeight() {
        return height;
    }

    public List<Integer> getWidth() {
        return width;
    }

    public List<Integer> getBallCount() {
        return ballCount;
    }

    public List<Integer> getHoleCount() {
        return holeCount;
    }

    public List<Position> getPlayerLocation() {
        return playerLocation;
    }


}
