import java.util.Stack;

public class Classification {

    private final MovePlayer movePlayer;
    private final MapReader mapReader;
    public static final Stack<String> rewind = new Stack<>();
    public static final Stack<String> reRewind = new Stack<>();
    public static final Stack<Boolean> pushBall = new Stack<>();
    private String previousCommand = "";

    Classification(MovePlayer movePlayer, MapReader mapReader) {
        this.movePlayer = movePlayer;
        this.mapReader = mapReader;
    }

    public void performCommands(int[][] map, String[] commands, Position playerPosition, int stage) {

        for (String command : commands) {
            int x = playerPosition.getX();
            int y = playerPosition.getY();
            validateCommand(map, command, x, y, playerPosition, stage);
        }

    }

    private void validateCommand(int[][] map, String command, int x, int y, Position playerPosition,
        int stage) {
        if (!previousCommand.equals("u")) {
            reRewind.clear();
        }
        if (moveCommand(map, command, x, y, playerPosition)) {
            previousCommand = command;
            return;
        }
        if (command.equalsIgnoreCase("Q")) {
            System.out.println("Bye~");
            System.exit(0);
        }
        if (command.equalsIgnoreCase("R")) {
            resetGame(map, playerPosition, stage);
            return;
        }
        if (command.equals("u")) {
            if (!rewind.isEmpty()) {
                String tmp = rewind.pop();
                reverseMoveCommand(map, tmp, x, y, playerPosition);
                reRewind.push(tmp);
                previousCommand = command;
            } else {
                System.out.println("더이상 되돌릴 수 없습니다!!");
            }
            return;
        }
        if (command.equals("U") && previousCommand.equals("u")) {
            if (!reRewind.isEmpty()) {
                moveCommand(map, reRewind.pop(), x, y, playerPosition);
            } else {
                System.out.println("되돌리기의 되돌리기가 불가능합니다!!");
            }
            return;
        }
        if (command.equals("U")) {
            System.out.println("되돌리기의 되돌리기가 불가능합니다!!");
            return;
        }
        if (command.equals("1S") || command.equals("2S") || command.equals("3S") ||
            command.equals("4S") || command.equals("5S")) {
            System.out.println("");
            System.out.println(command.charAt(0) + "세이브 기능 실행");
            System.out.println("세이브 기능을 만들지 못했습니다.");
            return;
        }
        if (command.equals("1L") || command.equals("2L") || command.equals("3L") ||
            command.equals("4L") || command.equals("5L")) {
            System.out.println("");
            System.out.println(command.charAt(0) + "불러오기 기능 실행");
            System.out.println("불러오기기능을 만들지 못했습니다.");
            return;
        }
        Print.printMap(map);
        System.out.println(command + ": 해당 명령어는 존재하지 않습니다.");
    }

    private boolean moveCommand(int[][] map, String command, int x, int y, Position playerPosition) {
        if (command.equalsIgnoreCase("A")) {
            if (movePlayer.moveWASD(map, command, x, y, 0, -1, playerPosition, ": 왼쪽으로 이동합니다.")) {
                Classification.rewind.push("A");
            }
            return true;
        }
        if (command.equalsIgnoreCase("D")) {
            if (movePlayer.moveWASD(map, command, x, y, 0, 1, playerPosition, ": 오른쪽으로 이동합니다.")) {
                Classification.rewind.push("D");
            }
            return true;
        }
        if (command.equalsIgnoreCase("W")) {
            if (movePlayer.moveWASD(map, command, x, y, -1, 0, playerPosition, ": 위로 이동합니다.")) {
                Classification.rewind.push("W");
            }
            return true;
        }
        if (command.equalsIgnoreCase("S")) {
            if (movePlayer.moveWASD(map, command, x, y, 1, 0, playerPosition, ": 아래로 이동합니다.")) {
                Classification.rewind.push("S");
            }
            return true;
        }
        return false;
    }

    private void reverseMoveCommand(int[][] map, String command, int x, int y, Position playerPosition) {
        if (command.equalsIgnoreCase("A")) {
            movePlayer.reverseMoveWASD(map, command, x, y, 0, -1, playerPosition);
            return;
        }
        if (command.equalsIgnoreCase("D")) {
            movePlayer.reverseMoveWASD(map, command, x, y, 0, 1, playerPosition);
            return;
        }
        if (command.equalsIgnoreCase("W")) {
            movePlayer.reverseMoveWASD(map, command, x, y, -1, 0, playerPosition);
            return;
        }
        if (command.equalsIgnoreCase("S")) {
            movePlayer.reverseMoveWASD(map, command, x, y, 1, 0, playerPosition);
        }
    }

    private void resetGame(int[][] map, Position playerPosition, int stage) {
        setCount();
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
        rewind.clear();
        reRewind.clear();
        pushBall.clear();
    }

}
