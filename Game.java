public class Game {

    private int nowStage;
    private MapReader mapReader;
    private UserInput userInput;
    private MovePlayer movePlayer;
    private Position playerPosition;
    private Classification classification;

    Game() {
        init();
    }

    private void init() {
        mapReader = new MapReader();
        userInput = new UserInput();
        movePlayer = new MovePlayer();
        classification = new Classification();
    }

    public void run() {
        int totalStage = mapReader.getStageSize();

        for (int stage = 1; stage <= totalStage; stage++) {
            int[][] map = initSetting(stage);
            boolean isKeep = true;
            playGame(map, isKeep);
        }
        userInput.close();
    }

    private int[][] initSetting(int stage) {
        int[][] map = mapReader.getStages(stage);
        playerPosition = mapReader.getPlayerPosition().get(stage - 1);
        System.out.println("Stage :" + stage);
        nowStage = stage;
        CopyMap.copyInitialMap(map);
        PrintMap.print(map);
        return map;
    }

    private void playGame(int[][] map, boolean isKeep) {
        while (isKeep) {
            char[] commands = userInput.userInput();
            classification.performCommands(map, commands, playerPosition, movePlayer);
            isKeep = checkEndGame(map);
        }
    }

    private boolean checkEndGame(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 1 || map[i][j] == 2) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getNowStage() {
        return nowStage;
    }
}
