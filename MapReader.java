import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapReader {

    private List<String> lines;
    private List<Integer> stageLine = new ArrayList<>();

    private List<Integer> height = new ArrayList<>();
    private List<Integer> width = new ArrayList<>();

    MapReader() {
        readMap();
        countStage();
    }

    private void readMap() {
        // this.lines = 클래스에서 얻어 온 정보.
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

    public String[][] getStages(int stageNum) {
        int start;
        int last;

        if (stageNum == stageLine.size()) {
            start = stageLine.get(stageNum - 1);
            last = lines.size();
        } else {
            start = stageLine.get(stageNum - 1);
            last = stageLine.get(stageNum) - 1;
        }

        int height = last - (start + 1);
        int width = lines.get(start + 1).length();
        this.height.add(height);
        this.width.add(width);
        String[][] map = new String[height][width];
        for (int i = 0; i < map.length; i++) {
            Arrays.fill(map[i], " ");
        }

        int k = 0;
        for (int i = start + 1; i < last; i++) {
            String line = lines.get(i);
            for (int j = 0; j < width; j++) {
                if(line.charAt(j) == '#'){
                    map[k][j] = "0";
                }
                if(line.charAt(j) == 'O'){
                    map[k][j] = "1";
                }
                if(line.charAt(j) == 'o'){
                    map[k][j] = "2";
                }
                if(line.charAt(j) == 'P'){
                    map[k][j] = "3";
                }
            }
            k++;
//            System.out.println(line);
        }

        return map;

    }

}
