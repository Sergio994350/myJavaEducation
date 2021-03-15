import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // read source data
        String text = "Каждый охотник желает знать, где сидит фазан";
        String[] colors = text.split(",?\\s+");
        System.out.println(Arrays.toString(colors)); // выводим "прямой" массив на экран

        //processing - в цикле зеркально меняем местами данные
        for (int i = 0; i < colors.length / 2; i++) {
            String temp = colors[i];
            colors[i] = colors[colors.length - i - 1];
            colors[colors.length - i - 1] = temp;
        }

        //display results
        System.out.println(Arrays.toString(colors)); // выводим измененный массив на экран
    }
}
