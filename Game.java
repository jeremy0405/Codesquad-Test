public class Game {

    public void run() {
        MapReader mapReader = new MapReader();
        UserInput userInput = new UserInput();
        MovePlayer movePlayer = new MovePlayer();
        int[][] map = mapReader.getStages(2);
        Position playerPosition = mapReader.getPlayerPosition().get(0);

        PrintMap.print(map);

        while (true) {
            char[] commands = userInput.userInput();
            movePlayer.move(map, commands, playerPosition);
        }

    }

}
