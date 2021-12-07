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

        for (int i = 1; i <= totalStage; i++) {
            int[][] map = mapReader.getStages(i);
            playerPosition = mapReader.getPlayerPosition().get(i - 1);
            System.out.println("Stage :" + i);
            CopyMap.copyInitialMap(map);
            PrintMap.print(map);
            boolean isKeep = true;

            playGame(map, isKeep);
        }
//            playGame(userInput, movePlayer, map, playerPosition, isKeep);

        userInput.close();
    }

    private void playGame(int[][] map, boolean isKeep) {
        while (isKeep) {
            char[] commands = userInput.userInput();
            classification.performCommands(map, commands, playerPosition, movePlayer);
            isKeep = checkEndGame(map);
        }
    }

    public boolean checkEndGame(int[][] map) {
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
