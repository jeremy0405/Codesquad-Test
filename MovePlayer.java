public class MovePlayer {

    public void move(int[][] map, char[] commands, Position playerPosition) {

        for (char command : commands) {
            int x = playerPosition.getX();
            int y = playerPosition.getY();
            moveOnce(map, command, x, y, playerPosition);
        }

    }

    private void moveOnce(int[][] map, char command, int x, int y, Position playerPosition) {

        if (command == 'A') {
            moveWASD(map, command, x, y, x, y - 1, playerPosition, ": 왼쪽으로 이동합니다.");
            return;
        }
        if (command == 'D') {
            moveWASD(map, command, x, y, x, y + 1, playerPosition, ": 오른쪽으로 이동합니다.");
            return;
        }
        if (command == 'W') {
            moveWASD(map, command, x, y, x - 1, y, playerPosition, ": 위로 이동합니다.");
            return;
        }
        if (command == 'S') {
            moveWASD(map, command, x, y, x + 1, y, playerPosition, ": 아래로 이동합니다.");
            return;
        }
        if (command == 'Q') {
            System.out.println("Bye~");
            System.exit(0);
            return;
        }
        printMapAndCommand(map, command, ": 해당 명령어는 존재하지 않습니다.");
    }

    private void moveWASD(int[][] map, char command, int x, int y, int a,
        int b, Position playerPosition, String s) {

        if (map[a][b] != 9) {
            printMapAndCommand(map, command, ": (경고!) 해당 명령을 수행할 수 없습니다!");
            return;
        }
        realMove(map, x, y, a, b, playerPosition);
        printMapAndCommand(map, command, s);
    }

    private void printMapAndCommand(int[][] map, char command, String s) {
        PrintMap.print(map);
        System.out.println(command + s);
    }

    private void realMove(int[][] map, int x, int y, int a, int b, Position playerPosition) {
        int tmp = map[x][y];
        map[x][y] = map[a][b];
        map[a][b] = tmp;
        playerPosition.setX(a);
        playerPosition.setY(b);
    }

}
