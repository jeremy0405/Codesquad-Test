public class Print {

    public static void printMap(int[][] map) {
        System.out.println("");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                switch (map[i][j]) {
                    case 1:
                        System.out.print("O");
                        break;
                    case 2:
                        System.out.print("o");
                        break;
                    case 3:
                        System.out.print("⊙");
                        break;
                    case 4:
                        System.out.print("P");
                        break;
                    case 5:
                        System.out.print("P");
                        break;
                    case 9:
                        System.out.print("#");
                        break;
                    default:
                        System.out.print(" ");
                }
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public static void stageEndPrint(int stage, Classification classification) {

        System.out.println("");
        System.out.println("******************");
        System.out.println("*Stage " + stage + " Clear!!!*");
        System.out.printf("*명령 입력 횟수: %2d*%n", classification.getCount());
        System.out.println("******************");
        System.out.println("");

    }

    public static void gameStartMessage() {

        System.out.println("제리의 소코반에 오신 것을 환영합니다!!");
        System.out.println("===============================================================");
        System.out.println("**************************소코반 규칙****************************");
        System.out.println("1. P(플레이어)를 w a s d 입력하여 움직일 수 있습니다.");
        System.out.println("2. P(플레이어)는 o(공)을 밀 수 있습니다.");
        System.out.println("   단! 공을 미는 방향에 #(벽) 또는 다른 공이 있으면 밀 수 없습니다.");
        System.out.println("3. o(공)을 모두 O(구멍)에 밀어 넣으면 Stage 클리어!");
        System.out.println("4. r 입력 시 Stage를 초기화 할 수 있습니다.");
        System.out.println("5. q 입력 시 게임을 종료할 수 있습니다.");
        System.out.println("===============================================================");

    }

    public static void gameEndPrint() {

        System.out.println("");
        System.out.println("전체 게임을 클리어 하셨습니다!");
        System.out.println("축하드립니다!!");

    }

}
