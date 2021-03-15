import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.TreeSet;

//Используйте правила генерации номеров для получения более 2 млн номеров:
//XYZ — различные буквы, N — цифры, R — регион (от 01 до 199);
//XNNNYZR — пример, A111BC197, Y777HC66,
//методы поиска элементов в коллекциях:
//    прямым перебором по ArrayList,
//    бинарным поиском по сортированному java.util.ArrayList,
//    поиском в HashSet,
//    поиском в TreeSet .
public class Main {
    public static void main(String[] args) {

        // зададим элемент, который будем искать в списках
        String numberToDetect = "F555FF150";

        // создадим и заполним ArrayList с номерами машин
        ArrayList<String> nums1 = new ArrayList<>();
        for (int i = 0; i < 2000000; i++) {
            String randomNumber = numberGenerator();
            nums1.add(randomNumber);
        }

        // создадим и заполним отсортированный ArrayList с номерами машин
        ArrayList<String> nums2 = new ArrayList<>();
        for (int i = 0; i < 2000000; i++) {
            String randomNumber = numberGenerator();
            nums2.add(randomNumber);
        }
        Collections.sort(nums2);

        // создадим и заполним HashSet с номерами машин
        HashSet<String> nums3 = new HashSet<>();
        for (int i = 0; i < 2000000; i++) {
            String randomNumber = numberGenerator();
            nums3.add(randomNumber);
        }

        // создадим и заполним TreeSet с номерами машин
        TreeSet<String> nums4 = new TreeSet<>();
        for (int i = 0; i < 2000000; i++) {
            String randomNumber = numberGenerator();
            nums4.add(randomNumber);
        }

        // проверим скорость поиска разными методами:
        long start, finish, timeToWork;

        // 1й метод - перебором
        System.out.print("Поиск перебором в ArrayList:\t\t\t\t");
        start = System.nanoTime();
        searchInArrayList(nums1, numberToDetect);
        finish = System.nanoTime();
        timeToWork = finish - start;
        System.out.print("\tПрошло времени, мс: " + (timeToWork / 1000 + "\n"));

        // 2й метод - бинарный поиск в сортированном массиве
        System.out.print("Бинарный поиск в сортированном ArrayList:\t");
        start = System.nanoTime();
        searchInSortedArrayList(nums2, numberToDetect);
        finish = System.nanoTime();
        timeToWork = finish - start;
        System.out.print("\tПрошло времени, мс: " + (timeToWork / 1000 + "\n"));

        // 3й метод - поиск в HashSet
        System.out.print("Поиск в HashSet:\t\t\t\t\t\t\t");
        start = System.nanoTime();
        searchInHashSet(nums3, numberToDetect);
        finish = System.nanoTime();
        timeToWork = finish - start;
        System.out.print("\tПрошло времени, мс: " + (timeToWork / 1000 + "\n"));

        // 4й метод - поиск в TreeSet
        System.out.print("Поиск в TreeSet:\t\t\t\t\t\t\t");
        start = System.nanoTime();
        searchInTreeSet(nums4, numberToDetect);
        finish = System.nanoTime();
        timeToWork = finish - start;
        System.out.print("\tПрошло времени, мс: " + (timeToWork / 1000 + "\n"));
    }

    // создадим метод для генерации красивых номеров
    public static String numberGenerator() {
        String number = "";
        int innerNumberElement = (int) (Math.random() * 10);
        if (innerNumberElement == 0) {
            innerNumberElement = 1;
        }
        int innerNumber = 100 * innerNumberElement + 10 * innerNumberElement + innerNumberElement;
        char firstLetter = (char) ((Math.random() * 25) + 65);
        char secondLetter = (char) ((Math.random() * 25) + 65);
        char thirdLetter = (char) ((Math.random() * 25) + 65);
        String regionNumberString;
        int regionNumber = (int) ((Math.random() * 199) + 1);
        if (regionNumber < 10) {
            regionNumberString = ("0" + regionNumber);
        } else {
            regionNumberString = ("" + regionNumber);
        }
        number = ("" + firstLetter + innerNumber + secondLetter + thirdLetter + regionNumberString);
        return number;
    }


    // создадим метод для поиска прямым перебором по ArrayList
    public static void searchInArrayList(ArrayList<String> list, String whatDetect) {
        if (list.contains(whatDetect)) {
            System.out.print("Элемент найден");
        } else {
            System.out.print("Элемент не найден");
        }
    }

    // создадим метод для поиска бинарным поиском по сортированному java.util.ArrayList
    public static void searchInSortedArrayList(ArrayList<String> list, String whatDetect) {
        int index = Collections.binarySearch(list, whatDetect);
        if (index >= 0) {
            System.out.print("Элемент найден");
        } else {
            System.out.print("Элемент не найден");
        }
    }

    // создадим метод для поиска в HashSet
    public static void searchInHashSet(HashSet<String> list, String whatDetect) {
        boolean exist = list.contains(whatDetect);
        if (exist) {
            System.out.print("Элемент найден");
        } else {
            System.out.print("Элемент не найден");
        }
    }

    // создадим метод для поиска в TreeSet
    public static void searchInTreeSet(TreeSet<String> list, String whatDetect) {
        boolean exist = list.contains(whatDetect);
        if (exist) {
            System.out.print("Элемент найден");
        } else {
            System.out.print("Элемент не найден");
        }
    }
}