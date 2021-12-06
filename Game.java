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

        //todo command 에 따라 move 구현 및
        // command 한번 할 때마다 이동 출력
        // 이동 출력은 PrintMap.print(map) 이용

        while (true) {
            char[] commands = userInput.userInput();
            movePlayer.move(this.map, commands, this.playerPosition);
        }

    }

}
