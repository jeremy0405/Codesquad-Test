public class MovePlayer {

    public void move(int[][] map, char[] commands, Position playerPosition) {

        for (char command : commands) {
            int x = playerPosition.getX();
            int y = playerPosition.getY();
            moveOnce(map, command, x, y, playerPosition);
            PrintMap.print(map);
        }

    }

    private void moveOnce(int[][] map, char command, int x, int y, Position playerPosition) {

        if (command == 'A') {
            if (map[x][y - 1] != 9) {
                return;
            }
            int tmp = map[x][y];
            map[x][y] = map[x][y - 1];
            map[x][y - 1] = tmp;

            playerPosition.setX(x);
            playerPosition.setY(y - 1);
        }

        if (command == 'D') {
            if (map[x][y + 1] != 9) {
                return;
            }
            int tmp = map[x][y];
            map[x][y] = map[x][y + 1];
            map[x][y + 1] = tmp;

            playerPosition.setX(x);
            playerPosition.setY(y + 1);
        }

        if (command == 'W') {
            if (map[x - 1][y] != 9) {
                return;
            }
            int tmp = map[x][y];
            map[x][y] = map[x - 1][y];
            map[x - 1][y] = tmp;

            playerPosition.setX(x - 1);
            playerPosition.setY(y);
        }

        if (command == 'S') {
            if (map[x + 1][y] != 9) {
                return;
            }
            int tmp = map[x][y];
            map[x][y] = map[x + 1][y];
            map[x + 1][y] = tmp;

            playerPosition.setX(x + 1);
            playerPosition.setY(y);
        }

        if (command == 'Q') {
            System.out.println("게임 종료! Bye~");
            System.exit(0);
        }


    }

}
