public class Cross {
    public static void main(String[] args) {
        int[][] cross = new int[7][7];
        // заполняем массив 1-ми, 0 - по умолчанию
        for (int i = 0; i < cross.length; i++) {
            cross[i][i] = 1;
            cross[i][cross.length - 1 - i] = 1;
        }
        // выводим фигуру на печать
        for (int i = 0; i < cross.length; i++) {
            for (int j = 0; j < cross.length; j++) {
                if (cross[i][j] == 1) {
                    System.out.print("X");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}