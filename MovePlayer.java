public class MovePlayer {

    public void move(int[][] map, char[] commands, Position playerPosition) {

        for (char command : commands) {
            int x = playerPosition.getX();
            int y = playerPosition.getY();
            moveOnce(map, command, x, y, playerPosition);
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

    private void moveOnce(int[][] map, char command, int x, int y, Position playerPosition) {

        if (command == 'A') {
            moveWASD(map, command, x, y, 0, -1, playerPosition, ": 왼쪽으로 이동합니다.");
            return;
        }
        if (command == 'D') {
            moveWASD(map, command, x, y, 0, 1, playerPosition, ": 오른쪽으로 이동합니다.");
            return;
        }
        if (command == 'W') {
            moveWASD(map, command, x, y, -1, 0, playerPosition, ": 위로 이동합니다.");
            return;
        }
        if (command == 'S') {
            moveWASD(map, command, x, y, 1, 0, playerPosition, ": 아래로 이동합니다.");
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

        String warning = ": (경고!) 해당 명령을 수행할 수 없습니다!";

        if (map[x + a][y + b] == 9) {
            printMapAndCommand(map, command, warning);
            return;
        }
        if ((map[x + a][y + b] == 2 || map[x + a][y + b] == 3) &&
            (map[x + 2 * a][y + 2 * b] == 9
                || map[x + 2 * a][y + 2 * b] == 2
                || map[x + 2 * a][y + 2 * b] == 3)) {
            printMapAndCommand(map, command, warning);
            return;
        }

        if (map[x + a][y + b] == 2 || map[x + a][y + b] == 3) {
            MoveBall(map, x, y, a, b, playerPosition);
            printMapAndCommand(map, command, s);
            return;
        }

        realMove(map, x, y, a, b, playerPosition);
        printMapAndCommand(map, command, s);
    }

    private void MoveBall(int[][] map, int x, int y, int a, int b, Position playerPosition) {
        map[x][y] -= 4;
        map[x + a][y + b] += 2;
        map[x + 2 * a][y + 2 * b] += 2;
        playerPosition.setXY(x + a, y + b);
    }

    private void printMapAndCommand(int[][] map, char command, String s) {
        PrintMap.print(map);
        System.out.println(command + s);
    }

    private void realMove(int[][] map, int x, int y, int a, int b, Position playerPosition) {
        map[x][y] -= 4;
        map[x + a][y + b] += 4;
        playerPosition.setXY(x + a, y + b);
    }

}
