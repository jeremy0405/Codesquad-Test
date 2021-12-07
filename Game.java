public class Game {

    public void run() {
        MapReader mapReader = new MapReader();
        UserInput userInput = new UserInput();
        MovePlayer movePlayer = new MovePlayer();
        int stageNum = mapReader.getStageSize();

        for (int i = 1; i <= stageNum; i++) {
            int[][] map = mapReader.getStages(i);
            Position playerPosition = mapReader.getPlayerPosition().get(i - 1);
            System.out.println("Stage :" + i);
            CopyMap.copyInitialMap(map);
            PrintMap.print(map);
            boolean isKeep = true;
            while (isKeep) {
                char[] commands = userInput.userInput();
                movePlayer.move(map, commands, playerPosition);
                isKeep = movePlayer.checkEndGame(map);
            }
        }

    }

}
