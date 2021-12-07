public class Game {

    public void run() {
        MapReader mapReader = new MapReader();
        UserInput userInput = new UserInput();
        MovePlayer movePlayer = new MovePlayer();
        Classification classification = new Classification();
        int stageNum = mapReader.getStageSize();

        for (int i = 1; i <= stageNum; i++) {
            int[][] map = mapReader.getStages(i);
            Position playerPosition = mapReader.getPlayerPosition().get(i - 1);
            System.out.println("Stage :" + i);
            CopyMap.copyInitialMap(map);
            PrintMap.print(map);
            boolean isKeep = true;

            playGame(userInput, movePlayer, map, playerPosition, isKeep, classification);
        }
//            playGame(userInput, movePlayer, map, playerPosition, isKeep);

        userInput.close();
    }



    private void playGame(UserInput userInput, MovePlayer movePlayer, int[][] map,
        Position playerPosition, boolean isKeep, Classification classification) {
        while (isKeep) {
            char[] commands = userInput.userInput();
            classification.performCommands(map, commands, playerPosition, movePlayer);
            isKeep = classification.checkEndGame(map);
        }
    }

}
