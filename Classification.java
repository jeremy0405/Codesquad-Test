public class Classification {

    private final MovePlayer movePlayer;
    private final MapReader mapReader;
    private int count;

    Classification(MovePlayer movePlayer, MapReader mapReader) {
        this.movePlayer = movePlayer;
        this.mapReader = mapReader;
    }

    public void performCommands(int[][] map, char[] commands, Position playerPosition, int stage) {

        for (char command : commands) {
            int x = playerPosition.getX();
            int y = playerPosition.getY();
            validateCommand(map, command, x, y, playerPosition, stage);
        }

    }

    private void validateCommand(int[][] map, char command, int x, int y, Position playerPosition,
        int stage) {
        if (moveCommand(map, command, x, y, playerPosition)) {
            return;
        }
        if (command == 'Q') {
            System.out.println("Bye~");
            System.exit(0);
        }
        if (command == 'R') {
            resetGame(map, playerPosition, stage);
            return;
        }
        Print.printMap(map);
        System.out.println(command + ": 해당 명령어는 존재하지 않습니다.");
    }

    private boolean moveCommand(int[][] map, char command, int x, int y, Position playerPosition) {
        if (command == 'A') {
            moveAndCount(map, command, x, y, playerPosition, 0, -1, ": 왼쪽으로 이동합니다.");
            return true;
        }
        if (command == 'D') {
            moveAndCount(map, command, x, y, playerPosition, 0, 1, ": 오른쪽으로 이동합니다.");
            return true;
        }
        if (command == 'W') {
            moveAndCount(map, command, x, y, playerPosition, -1, 0, ": 위로 이동합니다.");
            return true;
        }
        if (command == 'S') {
            moveAndCount(map, command, x, y, playerPosition, 1, 0, ": 아래로 이동합니다.");
            return true;
        }
        return false;
    }

    private void moveAndCount(int[][] map, char command, int x, int y, Position playerPosition,
        int a, int b, String s) {
        movePlayer.moveWASD(map, command, x, y, a, b, playerPosition, s);
        count++;
    }

    private void resetGame(int[][] map, Position playerPosition, int stage) {
        this.count = 0;
        Position initPosition = mapReader.getInitPosition().get(stage - 1);
        playerPosition.setXY(initPosition.getX(), initPosition.getY());
        CopyMap.copyMap(map);
        System.out.println("초기화 되었습니다!!!!!!");
        Print.printMap(map);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
