public class MovePlayer {

    public boolean moveWASD(int[][] map, char command, int x, int y, int a,
        int b, Position playerPosition, String s) {
        if (map[x + a][y + b] == 9) {
            printMapAndCommand(map, command, ": (경고!) 해당 명령을 수행할 수 없습니다!");
            return false;
        }
        if ((map[x + a][y + b] == 2 || map[x + a][y + b] == 3) &&
            (map[x + 2 * a][y + 2 * b] == 9
                || map[x + 2 * a][y + 2 * b] == 2
                || map[x + 2 * a][y + 2 * b] == 3)) {
            printMapAndCommand(map, command, ": (경고!) 해당 명령을 수행할 수 없습니다!");
            return false;
        }
        if (map[x + a][y + b] == 2 || map[x + a][y + b] == 3) {
            moveBall(map, x, y, a, b, playerPosition);
            printMapAndCommand(map, command, s);
            return true;
        }
        realMove(map, x, y, a, b, playerPosition);
        printMapAndCommand(map, command, s);
        return true;
    }

    private void moveBall(int[][] map, int x, int y, int a, int b, Position playerPosition) {
        map[x][y] -= 4;
        map[x + a][y + b] += 2;
        map[x + 2 * a][y + 2 * b] += 2;
        playerPosition.setXY(x + a, y + b);
    }

    private void realMove(int[][] map, int x, int y, int a, int b, Position playerPosition) {
        map[x][y] -= 4;
        map[x + a][y + b] += 4;
        playerPosition.setXY(x + a, y + b);
    }

    private void printMapAndCommand(int[][] map, char command, String s) {
        Print.printMap(map);
        System.out.println(command + s);
    }
    
    //todo reversemoveWASD 구현
    // moveReverseBall
    // moveReverse

    public boolean reverseMoveWASD(int[][] map, char command, int x, int y, int a,
        int b, Position playerPosition, String s) {
        if (map[x + a][y + b] == 9) {
            printMapAndCommand(map, command, ": (경고!) 해당 명령을 수행할 수 없습니다!");
            return false;
        }
        if ((map[x + a][y + b] == 2 || map[x + a][y + b] == 3) &&
            (map[x + 2 * a][y + 2 * b] == 9
                || map[x + 2 * a][y + 2 * b] == 2
                || map[x + 2 * a][y + 2 * b] == 3)) {
            printMapAndCommand(map, command, ": (경고!) 해당 명령을 수행할 수 없습니다!");
            return false;
        }
        if (map[x + a][y + b] == 2 || map[x + a][y + b] == 3) {
            moveBall(map, x, y, a, b, playerPosition);
            printMapAndCommand(map, command, s);
            return true;
        }
        realMove(map, x, y, a, b, playerPosition);
        printMapAndCommand(map, command, s);
        return true;
    }

}
