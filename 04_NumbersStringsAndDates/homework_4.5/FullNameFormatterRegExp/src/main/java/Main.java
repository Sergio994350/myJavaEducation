import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //кириллица все:       1040 - 1103
        //кириллица заглавные: 1040 - 1071
        // пробел 32, дефис 45
        // цифры 0123456789  =  от 48 до 57

        System.out.println("Пожалуйста, введите ФИО в формате: Иванов Иван Иванович");
//    String input = "Иванов Иван Иванович";
//    String input = "Салтыков-Щедрин Михаил Евграфович";
//    String input = "Иван Иван Иванович вв2ввв";
//    String input = "1111 2222 3333";
//    String input = "Иван Иван";
//    String input = "0";

    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();

        // проверка на разрешенные символы или 0
        boolean format = true;
        char temp = ' ';
        int spaceCount = 0; // счетчик пробелов
        for (int i = 0; i < input.length(); i++) {
            temp = input.charAt(i);
            if ((int) input.charAt(i) == 32) {
                spaceCount++;
            }
            if (((int) temp >= 1040 && (int) temp <= 1103) && !Character.isDigit(temp) || (input.equals("0"))
                    || ((int) input.charAt(i) == 45 && ((int) input.charAt(i + 1) >= 1040 && (int) input.charAt(i + 1) <= 1071))
                    || ((int) input.charAt(i) == 32 && ((int) input.charAt(i + 1) >= 1040 && (int) input.charAt(i + 1) <= 1071))) {
                format = true;
            } else {
                format = false;
                break;
            }
        }
        // если проверка пройдена, делим строку на фамилию имя отчество
        if (format && spaceCount == 2) {
            String fio = input;
            String familyName = fio.split(" ")[0]; // регулярные выражения
            String firstName = fio.split(" ")[1];
            String secondName = fio.split(" ")[2];
            System.out.println("Фамилия: " + familyName + "\nИмя: " + firstName + "\nОтчество: " + secondName);
        } else {
            System.out.println("Введенная строка не является ФИО, \nлибо использовано неверное форматирование");
        }
    }
}
