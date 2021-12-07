public class MovePlayer {

    public void moveWASD(int[][] map, char command, int x, int y, int a,
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
