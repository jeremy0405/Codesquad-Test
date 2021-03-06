public class Print {

    public static void printMap(int[][] map) {
        System.out.println("");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                printAfterClassify(map, i, j);
            }
            System.out.println("");
        }
        System.out.println("");
    }

    private static void printAfterClassify(int[][] map, int i, int j) {
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
            case 4: case 5:
                System.out.print("P");
                break;
            case 9:
                System.out.print("#");
                break;
            default:
                System.out.print(" ");
        }
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
        System.out.println("=================================================================");
        System.out.println("**************************소코반 규칙*****************************");
        System.out.println("1. P(플레이어)를 w a s d 입력하여 움직일 수 있습니다.");
        System.out.println("2. P(플레이어)는 o(공)을 밀 수 있습니다.");
        System.out.println("   단! 공을 미는 방향에 #(벽) 또는 다른 공이 있으면 밀 수 없습니다.");
        System.out.println("3. o(공)을 모두 O(구멍)에 밀어 넣으면 Stage 클리어! (총 5 Stage)");
        System.out.println("4. r 입력 시 Stage를 초기화 할 수 있습니다.");
        System.out.println("5. q 입력 시 게임을 종료할 수 있습니다.");
        System.out.println("6. u 입력 시 이전 입력을 되돌릴 수 있습니다.");
        System.out.println("7. U 입력 시 u(이전 입력)을 되돌릴 수 있습니다.");
        System.out.println("   단! U외에 이전 입력이 u일 경우만 가능합니다. (w a s d 이동시 U 불가)");
        System.out.println("    사용 예시 ex) uuuu UU");
        System.out.println("8. 1S, 2S, ..., 5S 입력시 1 ~ 5번 슬롯에 저장이 가능합니다.");
        System.out.println("9. 1L, 2L, ..., 5L 입력시 1 ~ 5번 슬롯의 데이터를 불러옵니다.");
        System.out.println("※8,9 번은 구현을 다 하지 못했습니다. 입력에 따른 명령분류만 해놨습니다.");
        System.out.println("=================================================================");

    }

    public static void gameEndPrint() {

        System.out.println("");
        System.out.println("전체 게임을 클리어 하셨습니다!");
        System.out.println("축하드립니다!!");

    }

}
