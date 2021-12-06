public class PrintMap {

    public static void print(int[][] map, int i, MapReader mapReader) {
        System.out.println("Stage " + i);
        System.out.println("");
        for (int j = 0; j < map.length; j++) {
            for (int k = 0; k < map[j].length; k++) {
                switch (map[j][k]) {
                    case 0:
                        System.out.print("#");
                        break;
                    case 1:
                        System.out.print("O");
                        break;
                    case 2:
                        System.out.print("o");
                        break;
                    case 3:
                        System.out.print("P");
                        break;
                    default:
                        System.out.print(" ");
                }
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("가로 크기: " + mapReader.getWidth().get(i - 1));
        System.out.println("세로 크기: " + mapReader.getHeight().get(i - 1));
        System.out.println("구멍의 수: " + mapReader.getHoleCount().get(i - 1));
        System.out.println("공의 수: " + mapReader.getBallCount().get(i - 1));
        System.out.println("플레이어 위치 (" + (mapReader.getPlayerLocation().get(i - 1).getX() + 1) + ", "
            + (mapReader.getPlayerLocation().get(i - 1).getY() + 1) + ")");
        System.out.println("");
    }

}
