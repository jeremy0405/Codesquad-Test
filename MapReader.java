import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MapReader {

    private List<String> lines;
    private List<Integer> stageLine = new ArrayList<>();

    // 맵 읽는 생성자
    // 맵을 읽은 후 stage 개수를 체크함
    public void MapReader() throws IOException {
        readMapFile();
        stageLine.add(1);
        countStage();
    }

    private void readMapFile() throws IOException {
        this.lines = Files.readAllLines(Paths.get("map.txt"));
    }

    private void countStage() {
        int size = lines.size();
        for (int i = 0; i < size; i++) {
            if (lines.get(i).contains("=")) {
                setStageLine(i, size);
            }
        }
    }

    private void setStageLine(int i, int size) {
        if (i + 1 == size) {
            return;
        }
        stageLine.add(i + 1);
    }

    public int[][] getStages() {

        //todo map을 생성해줘야 함.


        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).contains("=")) {
                continue;
            }
            String line = lines.get(i);

            //todo 배열 값에 맞춰서 저장하는 메서드

            System.out.println(lines.get(i));
        }


    }

}
