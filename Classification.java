public class Classification {

    private MovePlayer movePlayer;
    private int count;

    Classification(MovePlayer movePlayer) {
        this.movePlayer = movePlayer;
    }

    public void performCommands(int[][] map, char[] commands, Position playerPosition) {

        for (char command : commands) {
            int x = playerPosition.getX();
            int y = playerPosition.getY();
            validateCommad(map, command, x, y, playerPosition);
        }

    }

    private void validateCommad(int[][] map, char command, int x, int y, Position playerPosition) {

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
            //todo Restart
            // 카운트 0
            // 입력 받아서 Stage 정보를 저장
            //  저장된 Stage??
            this.count = 0;

            return;
        }
        Print.printMap(map);
        System.out.println(command + ": 해당 명령어는 존재하지 않습니다.");
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
