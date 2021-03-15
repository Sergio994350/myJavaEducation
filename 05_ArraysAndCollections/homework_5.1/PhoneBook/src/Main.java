import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class Main {

    static boolean detectPhone;
    static boolean detectName;

    public static void main(String[] args) {

        String enterText = "";
        String listCom = "LIST";
        Scanner sc = new Scanner(System.in);
        TreeMap<String, String> phoneBook = new TreeMap<>();

        // Помещаем элементы в телефонную книгу
        phoneBook.put("Sveta", "79313334498");
        phoneBook.put("Vasya", "79101234567");
        phoneBook.put("Petya", "79201112030");
        phoneBook.put("Anya", "79260112535");
        phoneBook.put("Olya", "79259998877");
        phoneBook.put("Mihail", "79167776644");

        // ввод с консоли
        while (!enterText.equals("0")) {
            System.out.println("Пожалуйста, введите имя или номер телефона");
            System.out.println("LIST - для вывода списка имён и номеров");
            System.out.println("0 -для выхода из программы");
            enterText = new Scanner(System.in).nextLine();

            String phone = detectPhoneNumber(enterText);
            String name = detectNameInBook(enterText);

            detectName = false;
            detectPhone = false;

            if (enterText.equals("0")) // проверка на 0 - выход из программы
                break;

            // проверка ввода LIST
            if (enterText.toLowerCase().contains(listCom.toLowerCase())) {
                printMap(phoneBook);
                System.out.println();
                continue;
            }
            if (phone.equals("ERROR") && name.equals("ERROR")) {
                System.out.println("Введена ошибочная строка. Повторите ввод");
            } else if (!phone.equals("ERROR")) {
                doByPhone(phoneBook, phone, sc);
            } else {
                doByName(phoneBook, name, sc);
            }
        }
        System.out.println("Программа закончила работу");
    }

    // метод для печати телефонной книги
    private static void printMap(Map<String, String> map) {
        if (map.size() > 0) {
            for (String key : map.keySet()) {
                System.out.println("Имя: " + key + ", телефон: " + map.get(key));
            }
        } else {
            System.out.println("В телефонной книге нет записей");
        }
    }

    // метод для поиска телефона в книге
    public static void doByPhone(TreeMap<String, String> phoneBook, String phone, Scanner sc) {
        String name;
        String input;
        detectPhone = alreadyExist(phoneBook, phone);
        if (!detectPhone) {
            System.out.println("Введите имя:");
            input = sc.nextLine();
            name = detectNameInBook(input);
            for (String key : phoneBook.keySet()) {
                if (name.equals(key)) {
                    System.out.println("Имя: " + key + ", телефон: " + phoneBook.get(key) + "\n");
                    detectName = true;
                    break;
                }
            }
            if (!detectName) {
                phoneBook.put(name, phone);
            }
        }
    }

    // метод для поиска имени в книге
    public static void doByName(TreeMap<String, String> phoneBook, String name, Scanner sc) {
        String phone;
        String input;
        for (String key : phoneBook.keySet()) {
            if (name.equals(key)) {
                System.out.println("Имя: " + key + ", телефон: " + phoneBook.get(key) + "\n");
                detectName = true;
                break;
            }
        }
        if (!detectName) {
            System.out.println("Для добавления в книгу введите номер телефона:");
            input = sc.nextLine();
            phone = detectPhoneNumber(input);
            detectPhone = alreadyExist(phoneBook, input);
            if (!detectPhone) {
                phoneBook.put(name, phone);
            }
        }
    }

    // метод проверки есть ли такая запись в телефонной книге
    public static boolean alreadyExist(TreeMap<String, String> phoneBook, String phone) {
        boolean detected = false;
        for (String key : phoneBook.keySet()) {
            if (phoneBook.get(key).equals(phone)) {
                System.out.println("Имя: " + key + ", телефон: " + phoneBook.get(key) + "\n");
                detected = true;
                break;
            }
        }
        return detected;
    }

    // метод проверки правильности внесения телефона (формат номера)
    public static String detectPhoneNumber(String input) {
        boolean isValid = true;
        if (Pattern.matches("^((7|8|9|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$",
                input)) {
            String phoneNumber = input.replaceAll("[+\\s()-]+", "");
            if (phoneNumber.charAt(0) == '8' && phoneNumber.length() == 11) {
                phoneNumber = phoneNumber.replace('8', '7');
            } else if (phoneNumber.length() == 10 && phoneNumber.charAt(0) == '9') {
                phoneNumber = "7" + phoneNumber;
            } else if (phoneNumber.length() == 11 && (phoneNumber.charAt(0) != '8'
                    && phoneNumber.charAt(0) != '7')) {
                isValid = false;
            } else if (phoneNumber.length() != 11) {
                isValid = false;
            }
            if (isValid) {
                return phoneNumber;
            } else {
                return "ERROR";
            }
        } else {
            return "ERROR";
        }
    }

    public static String detectNameInBook(String input) {
        if (Pattern.matches("^(([a-zA-Z-]{1,30})|([а-яА-ЯЁё-]{1,30}))$", input)) {
            return input;
        } else {
            return "ERROR";
        }
    }
}