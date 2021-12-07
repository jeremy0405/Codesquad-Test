public class Classification {

    public void performCommands(int[][] map, char[] commands, Position playerPosition,
        MovePlayer movePlayer) {

        for (char command : commands) {
            int x = playerPosition.getX();
            int y = playerPosition.getY();
            validateCommad(map, command, x, y, playerPosition, movePlayer);
        }

    }

    private void validateCommad(int[][] map, char command, int x, int y, Position playerPosition,
        MovePlayer movePlayer) {

        if (command == 'A') {
            movePlayer.moveWASD(map, command, x, y, 0, -1, playerPosition, ": 왼쪽으로 이동합니다.");
            return;
        }
        if (command == 'D') {
            movePlayer.moveWASD(map, command, x, y, 0, 1, playerPosition, ": 오른쪽으로 이동합니다.");
            return;
        }
        if (command == 'W') {
            movePlayer.moveWASD(map, command, x, y, -1, 0, playerPosition, ": 위로 이동합니다.");
            return;
        }
        if (command == 'S') {
            movePlayer.moveWASD(map, command, x, y, 1, 0, playerPosition, ": 아래로 이동합니다.");
            return;
        }
        if (command == 'Q') {
            System.out.println("Bye~");
            System.exit(0);
            return;
        }
        if (command == 'R') {
            //todo Restart
            return;
        }
        PrintMap.print(map);
        System.out.println(command + ": 해당 명령어는 존재하지 않습니다.");
    }

}
