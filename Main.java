public class Main {

    public static void main(String[] args) {
        MapReader mapReader = new MapReader();
        int stageNum = mapReader.getStageSize();

        for (int i = 1; i <= stageNum; i++) {
            int[][] map = mapReader.getStages(i);
            PrintMap.print(map, i, mapReader);
        }

    }

}
