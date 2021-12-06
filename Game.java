public class Game {

    public void run() {
        MapReader mapReader = new MapReader();
        UserInput userInput = new UserInput();
        MovePlayer movePlayer = new MovePlayer();
        int stageNum = mapReader.getStageSize();

        for (int i = 1; i <= stageNum; i++) {
            int[][] map = mapReader.getStages(i);
            Position playerPosition = mapReader.getPlayerPosition().get(i - 1);

            PrintMap.print(map);
            int j = 0;
            while (j < 3) {
                char[] commands = userInput.userInput();
                movePlayer.move(map, commands, playerPosition);
                j++;
            }
        }




    }

}
