import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MapReader {

    private List<String> lines;

    //stageLine에 Stage1 or Stage2 or Stage3 가 입력되어 있는 라인의 column을 저장
    private List<Integer> stageLine = new ArrayList<>();

    // 맵 읽는 생성자
    // 맵을 읽은 후 stage 개수를 체크함
    MapReader() throws IOException {
        readMapFile();
        countStage();
    }

    private void readMapFile() throws IOException {
        this.lines = Files.readAllLines(Paths.get("map.txt"));
    }

    private void readMap() {
        // this.lines = 클래스에서 얻어 온 정보.
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

    public void getStages(int stageNum) {

        //todo map을 생성해줘야 함.

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
        int[][] map = new int[height][width];

        for (int i = start; i < last; i++) {
            String line = lines.get(i);

            for (int j = 0; j < width; j++) {
                if (line.charAt(j) == '#') {
                    map[i][j] = 0;
                }
                if (line.charAt(j) == 'O') {
                    map[i][j] = 1;
                }
                if (line.charAt(j) == 'o') {
                    map[i][j] = 2;
                }
                if (line.charAt(j) == 'P') {
                    map[i][j] = 3;
                }
            }

            System.out.println(line);
        }

        System.out.println("height :" + height);
        System.out.println("width :" + width);
        System.out.println("");

    }

}
