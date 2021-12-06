import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapReader {

    private List<String> lines;
    private List<Integer> stageLine = new ArrayList<>();

    private List<Integer> height = new ArrayList<>();
    private List<Integer> width = new ArrayList<>();
    private List<Integer> ballCount = new ArrayList<>();
    private List<Integer> holeCount = new ArrayList<>();

    public List<Integer> getBallCount() {
        return ballCount;
    }

    public List<Integer> getHoleCount() {
        return holeCount;
    }

    MapReader() {
        readMap();
        countStage();
    }

    private void readMap() {
        this.lines = Arrays.asList(StageData.mapdata);
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

    public List<Integer> getHeight() {
        return height;
    }

    public List<Integer> getWidth() {
        return width;
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

    public String[][] getStages(int stageNum) {

        int[] startToLast = setStartToLast(stageNum);
        ballCount.add(0);
        holeCount.add(0);
        int start = startToLast[0];
        int last = startToLast[1];

        int height = last - start;
        int width = lines.get(start).length();
        setHeightAndWidth(height, width);

        String[][] map = new String[height][width];
        map = initMap(map);
        map = setMap(start, last, width, map, stageNum);

        return map;
    }

    private String[][] initMap(String[][] map) {
        for (int i = 0; i < map.length; i++) {
            Arrays.fill(map[i], " ");
        }
        return map;
    }

    private String[][] setMap(int start, int last, int width, String[][] map, int stageNum) {
        int k = 0;
        for (int i = start; i < last; i++) {
            String line = lines.get(i);
            for (int j = 0; j < width; j++) {
                if(line.charAt(j) == '#'){
                    map[k][j] = "0";
                }
                if(line.charAt(j) == 'O'){
                    map[k][j] = "1";
                    holeCount.set(stageNum - 1, holeCount.get(stageNum - 1) + 1);
                }
                if(line.charAt(j) == 'o'){
                    map[k][j] = "2";
                    ballCount.set(stageNum - 1, ballCount.get(stageNum - 1) + 1);
                }
                if(line.charAt(j) == 'P'){
                    map[k][j] = "3";
                    //todo Position
                }
            }
            k++;
        }
        return map;
    }

    private void setHeightAndWidth(int height, int width) {
        this.height.add(height);
        this.width.add(width);
    }


}
