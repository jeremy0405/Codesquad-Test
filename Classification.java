import java.util.Stack;

public class Classification {

    private final MovePlayer movePlayer;
    private final MapReader mapReader;
    public static final Stack<Character> rewind = new Stack<>();
    public static final Stack<Character> reRewind = new Stack<>();

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

    private void validateCommand(int[][] map, char command, int x, int y, Position playerPosition,
        int stage) {
        if (moveCommand(map, command, x, y, playerPosition)) {
            return;
        }
        if (command == 'Q' || command == 'q') {
            System.out.println("Bye~");
            System.exit(0);
        }
        if (command == 'R' || command == 'r') {
            resetGame(map, playerPosition, stage);
            return;
        }
        if (command == 'u') {
            //todo 되돌리기
            if (!rewind.isEmpty()) {
                char tmp = rewind.pop();
                //reverseMoveCommand
//            reverseMoveCommand(map, tmp, x, y, playerPosition, stage);
                reRewind.push(tmp);
            } else {
                System.out.println("초기 상태입니다, 더이상 되돌릴 수 없습니다!!");
            }
            return;
        }
        if (command == 'U') {
            if (!reRewind.isEmpty()) {
                justMoveCommand(map, reRewind.pop(), x, y, playerPosition);
            } else {
                System.out.println("더이상 저장된 되돌리기 값이 없습니다!!");
            }
            return;
        }
        Print.printMap(map);
        System.out.println(command + ": 해당 명령어는 존재하지 않습니다.");
    }

    private boolean moveCommand(int[][] map, char command, int x, int y, Position playerPosition) {
        if (command == 'A' || command == 'a') {
            if (movePlayer.moveWASD(map, command, x, y, 0, -1, playerPosition, ": 왼쪽으로 이동합니다.")) {
                Classification.rewind.push('A');
            }
            return true;
        }
        if (command == 'D' || command == 'd') {
            if (movePlayer.moveWASD(map, command, x, y, 0, 1, playerPosition, ": 오른쪽으로 이동합니다.")) {
                Classification.rewind.push('D');
            }
            return true;
        }
        if (command == 'W' || command == 'w') {
            if (movePlayer.moveWASD(map, command, x, y, -1, 0, playerPosition, ": 위로 이동합니다.")) {
                Classification.rewind.push('W');
            }
            return true;
        }
        if (command == 'S' || command == 's') {
            if (movePlayer.moveWASD(map, command, x, y, 1, 0, playerPosition, ": 아래로 이동합니다.")) {
                Classification.rewind.push('S');
            }
            return true;
        }
        return false;
    }

    private void justMoveCommand(int[][] map, char command, int x, int y, Position playerPosition) {
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
        if (command == 'S' || command == 's') {
            movePlayer.moveWASD(map, command, x, y, 1, 0, playerPosition, ": 아래로 이동합니다.");
        }
    }

    private void resetGame(int[][] map, Position playerPosition, int stage) {
        this.rewind.clear();
        Position initPosition = mapReader.getInitPosition().get(stage - 1);
        playerPosition.setXY(initPosition.getX(), initPosition.getY());
        CopyMap.copyMap(map);
        System.out.println("초기화 되었습니다!!!!!!");
        Print.printMap(map);
    }

    public int getCount() {
        return rewind.size();
    }

    public void setCount() {
        this.rewind.clear();
        this.reRewind.clear();
    }
}
