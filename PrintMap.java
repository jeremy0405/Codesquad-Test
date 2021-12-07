public class PrintMap {

    public static void print(int[][] map) {
        System.out.println("");
        for (int j = 0; j < map.length; j++) {
            for (int k = 0; k < map[j].length; k++) {
                switch (map[j][k]) {
                    case 1:
                        System.out.print("O");
                        break;
                    case 2:
                        System.out.print("o");
                        break;
                    case 3:
                        System.out.print("0");
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

}
