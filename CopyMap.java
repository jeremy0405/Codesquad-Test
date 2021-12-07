public class CopyMap {

    private static final int[][][] stageMap = new int[5][][];
    private static int stage = 0;

    public static void copyInitialMap(int[][] map) {
        stageMap[stage] = new int[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.arraycopy(map[i], 0, stageMap[stage][i], 0, map[i].length);
            }
        }
        stage++;
    }

    public static void copymap(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.arraycopy(stageMap[Game.nowStage - 1][i], 0, map[i], 0, map[i].length);
            }
        }
    }

}
