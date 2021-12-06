public class Game {

    MapReader mapReader;
    UserInput userInput;
    int[][] map;

    Game() {
        init();
    }

    private void init() {
        mapReader = new MapReader();
        map = mapReader.getStages(2);
        userInput = new UserInput();
    }

    public void run() {
        char[] command = userInput.userInput();

        for (var a: command
        ) {
            System.out.println(a);
        }

        //todo command 에 따라 move 구현 및
        // command 한번 할 때마다 이동 출력
        // 이동 출력은 PrintMap.print(map) 이용
        PrintMap.print(map);

    }

}
