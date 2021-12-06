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
            if (map[x][y - 1] != 9) {
                PrintMap.print(map);
                System.out.println(command + ": (경고!) 해당 명령을 수행할 수 없습니다!");
                return;
            }
            int tmp = map[x][y];
            map[x][y] = map[x][y - 1];
            map[x][y - 1] = tmp;

            playerPosition.setX(x);
            playerPosition.setY(y - 1);
            PrintMap.print(map);
            System.out.println(command + ": 왼쪽으로 이동합니다.");
            return;
        }

        if (command == 'D') {
            if (map[x][y + 1] != 9) {
                PrintMap.print(map);
                System.out.println(command + ": (경고!) 해당 명령을 수행할 수 없습니다!");
                return;
            }
            int tmp = map[x][y];
            map[x][y] = map[x][y + 1];
            map[x][y + 1] = tmp;

            playerPosition.setX(x);
            playerPosition.setY(y + 1);
            PrintMap.print(map);
            System.out.println(command + ": 오른쪽으로 이동합니다.");
            return;
        }

        if (command == 'W') {
            if (map[x - 1][y] != 9) {
                PrintMap.print(map);
                System.out.println(command + ": (경고!) 해당 명령을 수행할 수 없습니다!");
                return;
            }
            int tmp = map[x][y];
            map[x][y] = map[x - 1][y];
            map[x - 1][y] = tmp;

            playerPosition.setX(x - 1);
            playerPosition.setY(y);
            PrintMap.print(map);
            System.out.println(command + ": 위로 이동합니다.");
            return;
        }

        if (command == 'S') {
            if (map[x + 1][y] != 9) {
                PrintMap.print(map);
                System.out.println(command + ": (경고!) 해당 명령을 수행할 수 없습니다!");
                return;
            }
            int tmp = map[x][y];
            map[x][y] = map[x + 1][y];
            map[x + 1][y] = tmp;

            playerPosition.setX(x + 1);
            playerPosition.setY(y);
            PrintMap.print(map);
            System.out.println(command + ": 아래로 이동합니다.");
            return;
        }

        if (command == 'Q') {
            System.out.println("Bye~");
            System.exit(0);
            return;
        }

        PrintMap.print(map);
        System.out.println(command + ": 해당 명령을 수행할 수 없습니다");



    }

}
