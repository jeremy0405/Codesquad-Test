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

        if (map[x + a][y + b] == 0) {
            printMapAndCommand(map, command, ": (경고!) 해당 명령을 수행할 수 없습니다!");
            return;
        }
        if (map[x + a][y + b] == 2 && (map[x + 2 * a][y + 2 * b] == 0
            || map[x + 2 * a][y + 2 * b] == 2
            || map[x + 2 * a][y + 2 * b] == 3)) {
            printMapAndCommand(map, command, ": (경고!) 해당 명령을 수행할 수 없습니다!");
            return;
        }
        if (map[x + a][y + b] == 3 && (map[x + 2 * a][y + 2 * b] == 0
            || map[x + 2 * a][y + 2 * b] == 2
            || map[x + 2 * a][y + 2 * b] == 3)) {
            printMapAndCommand(map, command, ": (경고!) 해당 명령을 수행할 수 없습니다!");
            return;
        }
        if (map[x + a][y + b] == 2 && map[x + 2 * a][y + 2 * b] == 9) {
            map[x + 2 * a][y + 2 * b] = map[x + a][y + b];
            map[x + a][y + b] = map[x][y];
            map[x][y] = 9;
            playerPosition.setX(x + a);
            playerPosition.setY(y + b);
            printMapAndCommand(map, command, s);
            return;
        }
        if (map[x + a][y + b] == 2 || map[x + a][y + b] == 3) {
            MoveBall(map, x, y, a, b, playerPosition);
            printMapAndCommand(map, command, s);
            return;
        }

        if (map[x + a][y + b] == 1 && map[x][y] == 5) {
            map[x + a][y + b] += 4;
            map[x][y] -= 4;
            playerPosition.setX(x + a);
            playerPosition.setY(y + b);
            printMapAndCommand(map, command, s);
            return;
        }

        realMove(map, x, y, a, b, playerPosition);
        printMapAndCommand(map, command, s);
    }

    private void MoveBall(int[][] map, int x, int y, int a, int b, Position playerPosition) {
        if (map[x + 2 * a][y + 2 * b] == 9) {
            if (map[x + a][y + b] == 3) {
                map[x + 2 * a][y + 2 * b] =
                    (map[x + 2 * a][y + 2 * b] + map[x + a][y + b]) % 10; // 9 = 2
                map[x + a][y + b] += 2;
                map[x][y] = 9;
                playerPosition.setX(x + a);
                playerPosition.setY(y + b);
                return;
            }
            map[x + 2 * a][y + 2 * b] += (map[x + a][y + b] + 1) % 10; // 9 += 3 + 1 + 9 / 10
            map[x + a][y + b] += 2;
            map[x][y] = 9;
            playerPosition.setX(x + a);
            playerPosition.setY(y + b);
            return;
        }
        if (map[x + 2 * a][y + 2 * b] == 1) {
            map[x + 2 * a][y + 2 * b] += 2; // 3
            map[x + a][y + b] += 2; //  ab 2    4   ab 3  5
            map[x][y] = 9;
            playerPosition.setX(x + a);
            playerPosition.setY(y + b);
            return;
        }
    }

    private void printMapAndCommand(int[][] map, char command, String s) {
        PrintMap.print(map);
        System.out.println(command + s);
    }

    private void realMove(int[][] map, int x, int y, int a, int b, Position playerPosition) {

        // 이거 건드리면
        // Stage2 오류남

        if (map[x + a][y + b] == 9) {
            map[x + a][y + b] = 4;
        } else {
            map[x + a][y + b] += map[x][y]; // 1 += 4; 5
        }

        if (map[x][y] - 4 == 0) {
            map[x][y] = 9;
        } else {
            map[x][y] -= 4;
        }

        playerPosition.setX(x + a);
        playerPosition.setY(y + b);

    }

}
