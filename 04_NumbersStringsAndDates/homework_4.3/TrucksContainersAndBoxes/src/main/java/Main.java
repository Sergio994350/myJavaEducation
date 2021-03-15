import java.util.Scanner;

// Единственное, в задании с грузовиками прошу реализовать решение одним циклом.
public class Main {
    public static void main(String[] args) {
        final int BOXES_IN_CONTAINER = 27;   // константа - сколько ящиков помещается в 1 контейнер
        final int CONTAINERS_IN_TRUCK = 12;  // константа - сколько контейнеров помещается в 1 грузовик

        int container = 1;  // номер 1го контейнера
        int truck = 1;  // номер 1го грузовика

        System.out.println("Сколько ящиков нужно доставить? Введите целое число: ");  // ввод с консоли - сколько всего ящиков
        Scanner scanner = new Scanner(System.in);
        String boxStr = scanner.nextLine();
        int boxes = Integer.parseInt(boxStr);
        // можно сделать так:   int boxes = new Scanner(System.in).nextInt();
        if (boxes < 0) {
            System.out.println("Ошибочный ввод");  // проверка на отрицательное кол-во ящиков
        } else if (boxes == 0) {
            System.out.println("Доставка не требуется"); // проверка на 0 ящиков
        } else {

            // вычисляем кол-во грузовиков, контейнеров и ящиков
            if (boxes != 0) {
                container = (boxes % BOXES_IN_CONTAINER == 0) ? (boxes / BOXES_IN_CONTAINER)
                        : (boxes / BOXES_IN_CONTAINER) + 1;
            }
            if (container != 0) {
                truck = (container % CONTAINERS_IN_TRUCK == 0) ? (container / CONTAINERS_IN_TRUCK)
                        : (container / CONTAINERS_IN_TRUCK) + 1;
            }

            // делаем в 1 цикл - перематываем ящики
            int tr1 = 1;  // вводим по 2 вида временных переменных для смены грузовиков и контейнеров
            int tr2 = 0;
            int cn1 = 1;
            int cn2 = 0;
            int bx1 = 1;
            System.out.println("Грузовик: " + tr1);
            System.out.println("\tКонтейнер: " + cn1);
            for (int i = bx1; i <= boxes; i++) {
                if (cn2 == 1) {
                    cn2 = 0;
                    cn1++;
                    System.out.println("\tКонтейнер: " + cn1);
                }
                if (tr2 == 1) {
                    tr2 = 0;
                    tr1++;
                    System.out.println("Грузовик: " + tr1);
                }
                System.out.println("\t\tЯщик: " + i);
                if (i % 27 == 0) {
                    cn2 = 1;
                }
                if (i % (12 * 27) == 0) {
                    tr2 = 1;
                }
            }
            System.out.println("Для перевозки необходимо: ");
            System.out.println("Грузовиков: " + truck + "шт.");
            System.out.println("Контейнеров: " + container + "шт.");
        }
    }
}
