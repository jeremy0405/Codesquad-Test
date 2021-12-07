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

    private void validateCommand(int[][] map, char command, int x, int y, Position playerPosition, int stage) {

        if (command == 'A') {
            movePlayer.moveWASD(map, command, x, y, 0, -1, playerPosition, ": 왼쪽으로 이동합니다.");
            count++;
            return;
        }
        if (command == 'D') {
            movePlayer.moveWASD(map, command, x, y, 0, 1, playerPosition, ": 오른쪽으로 이동합니다.");
            count++;
            return;
        }
        if (command == 'W') {
            movePlayer.moveWASD(map, command, x, y, -1, 0, playerPosition, ": 위로 이동합니다.");
            count++;
            return;
        }
        if (command == 'S') {
            movePlayer.moveWASD(map, command, x, y, 1, 0, playerPosition, ": 아래로 이동합니다.");
            count++;
            return;
        }
        if (command == 'Q') {
            System.out.println("Bye~");
            System.exit(0);
            return;
        }
        if (command == 'R') {
            resetGame(map, playerPosition, stage);
            return;
        }
        Print.printMap(map);
        System.out.println(command + ": 해당 명령어는 존재하지 않습니다.");
    }

    private void resetGame(int[][] map, Position playerPosition, int stage) {
        this.count = 0;
        Position initPosition = mapReader.getInitPosition().get(stage - 1);
        playerPosition.setXY(initPosition.getX(), initPosition.getY());
        CopyMap.copymap(map);
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
