public class Game {

    private UserInput userInput;
    private Position playerPosition;
    int[][] map;

    Game() {
        init();
    }

    private void init() {
        MapReader mapReader = new MapReader();
        map = mapReader.getStages(2);
        userInput = new UserInput();
        playerPosition = mapReader.getPlayerPosition().get(0);
    }

    public void run() {
        MovePlayer movePlayer = new MovePlayer();
        PrintMap.print(map);

        while (true) {
            char[] commands = userInput.userInput();
            movePlayer.move(this.map, commands, this.playerPosition);
        }

    }

}
