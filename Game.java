public class Game {

    public static int nowStage;
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
        classification = new Classification(movePlayer, mapReader);
    }

    public void run() {
        int totalStage = mapReader.getStageSize();

        for (int stage = 1; stage <= totalStage; stage++) {
            int[][] map = initSetting(stage);
            boolean isKeep = true;
            playGame(map, isKeep, stage);

            Print.stageEndPrint(stage, classification);
        }
        //todo 메서드 안에 비어있음 작성해
        Print.gameEndPrint();
        userInput.close();
    }

    private int[][] initSetting(int stage) {
        int[][] map = mapReader.getStages(stage);
        playerPosition = mapReader.getPlayerPosition().get(stage - 1);
        classification.setCount(0);
        System.out.println("Stage :" + stage);
        nowStage = stage;
        CopyMap.copyInitialMap(map);
        Print.printMap(map);
        return map;
    }

    private void playGame(int[][] map, boolean isKeep, int stage) {
        while (isKeep) {
            char[] commands = userInput.userInput();
            classification.performCommands(map, commands, playerPosition, stage);
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

}
